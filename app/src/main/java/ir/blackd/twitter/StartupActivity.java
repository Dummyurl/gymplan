package ir.blackd.twitter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartupActivity extends AppCompatActivity {
    public static boolean connected = true;
    TextView txtError;
    Button btnAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);
        ActivityCompat.requestPermissions(StartupActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 12);
        txtError= (TextView) findViewById(R.id.txtAlert);
        btnAgain= (Button) findViewById(R.id.btnAgain);
       /* if (connected) {*/
           startApp();
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
    }
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
       // G.getDb();
    }

    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = StartupActivity.this;
    }
}
