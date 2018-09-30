package ir.blackd.twitter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.blackd.twitter.adapter.MovieAdapter;
import ir.blackd.twitter.fragment.OneFragment;
import ir.blackd.twitter.fragment.TwoFragment;

public class ListActivity extends AppCompatActivity{
    public RecyclerView recyclerView;
    public static MovieAdapter mAdapter;
    public static ProgressDialog progressDialog;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.tw,
            R.drawable.search,
            R.drawable.cat,
            R.drawable.favorite
    };
    private String TAG="TAG";

    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = ListActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag);
         Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.bi);
        //showDiag();
      //  isStoragePermissionGranted();
        //First checking if the app is already having the permission
        if(!isReadStorageAllowed()){
            //If permission is already having then showing the toast
            Toast.makeText(ListActivity.this,"You already have the permission",Toast.LENGTH_LONG).show();
            //Existing the method with return
        }else {
            //If the app has not the permission then asking for the permission
            requestStoragePermission();
        }



        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is just for test", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
*/

        Intent intent = getIntent();
        int value = intent.getIntExtra("key",1);
        G.getDb(value);
        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new MovieAdapter(G.workoutList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(G.context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);





    }







    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.INTERNET)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},23);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == 23){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
               // Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
              //  Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }

    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ListActivity.this, permission)) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }
}
