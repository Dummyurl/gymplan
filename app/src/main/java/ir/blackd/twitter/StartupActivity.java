package ir.blackd.twitter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartupActivity extends AppCompatActivity {
    public static boolean connected = true;
    TextView txtError;
    Button btnAgain;
    private String TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        txtError= findViewById(R.id.txtAlert);
        btnAgain= findViewById(R.id.btnAgain);
       /* if (connected) {*/
      isStoragePermissionGranted();
      startApp();
        //   ActivityCompat.requestPermissions(StartupActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 12);
         //  isStoragePermissionGranted();

       }
/*        }else {
            txtError.setVisibility(View.VISIBLE);
            btnAgain.setVisibility(View.VISIBLE);
            btnAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isNetworkConnected()){
                        startApp();
                    }else {
                        Toast.makeText(StartupActivity.this,"Still not Connected!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }*/

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    private void startApp() {
        G.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartupActivity.this, MainActivity.class));
            }
        }, 1000);
       // G.readFromNet();
        G.prepareMovieData();
        G.getDb(2);
        G.prepareProgramData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = StartupActivity.this;
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
              //  startApp();
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                //Toast.makeText(G.context,"Permission is revoked",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            startApp();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    startApp();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(StartupActivity.this, "خروج از برنامه به علت دسترسی به حافظه منع شد", Toast.LENGTH_SHORT).show();
               StartupActivity.this.finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
