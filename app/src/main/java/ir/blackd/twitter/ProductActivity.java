package ir.blackd.twitter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.eye,
            R.drawable.favorite,
            R.drawable.cat,
            R.drawable.search
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_single_page);
        Toolbar toolbar = findViewById(R.id.htab_toolbar);
        TextView txtDesc = findViewById(R.id.txtDesc);
        TextView txtName = findViewById(R.id.txtName);
        ImageView headImage = findViewById(R.id.htab_header);

        txtDesc.setTypeface(G.typeface);
        txtName.setTypeface(G.typeface);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String type = intent.getStringExtra("type");
        String desc = intent.getStringExtra("desc");
        String main = intent.getStringExtra("main");
        String help = intent.getStringExtra("help");
        String pic = intent.getStringExtra("pic");
        txtName.setText(" نام تمرین : " + title+ "\n"+
                " نوع تمرین : " + type+ "\n"+
                " عضلات اصلی : " + main+ "\n"+
                " عضلات کمکی : "+ help+ "\n"
        );
        txtDesc.setText(desc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
      //  int id = getResources().getIdentifier("ir.blackd.twitter:assets/workout" + "zz", null, null);
       // Picasso.with(ProductActivity.this).load(id).into(headImage);

        try
        {
            // get input stream
            InputStream ims = getAssets().open("workout/"+pic+".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            headImage.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            return;
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
}
