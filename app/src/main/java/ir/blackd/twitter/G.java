package ir.blackd.twitter;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Diamond Android on 12/19/2016.
 */
public class G extends Application {
    public static Context context;
    public static Typeface typeface;
    public static Activity currentActivity;
    public static Handler handler = new Handler();
    public static List<Movie> movieList = new ArrayList<>();
    public static HashMap<String, String> categoryList = new HashMap<>();
    public static SQLiteDatabase database;
    public static final String DIR_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DIR_APP = DIR_SDCARD + "/Tweetack";
    public static final String DIR_TEMP = DIR_APP + "/temp";
    public static final String DIR_FINAL = DIR_APP + "/final";
    private static String location;
    private static String screen_name;
    private static String followers_count;
    private static String friends_count;
    private static String statuses_count;
    private static String time_zone;
    private static String DB_PATH = G.DIR_APP;
    private static String DB_NAME = "varzesh.db";
    private static final int DB_VERSION = 1;

    public static final String   DIR_DATABASE = DIR_SDCARD + "/database-test/";
    private String TAG="TAG";

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        context = getApplicationContext();
        typeface = Typeface.createFromAsset(getAssets(), "Vazir.ttf");


        makeSdPath();
      //  ReportHelper.manageDatabase();

        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(DIR_DATABASE).mkdirs();

        database = SQLiteDatabase.openOrCreateDatabase( DIR_FINAL+"/varzesh.db", null);
      /*  database.execSQL("CREATE  TABLE  IF NOT EXISTS person (" +
                "person_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                "person_name TEXT, " +
                "person_family TEXT, " +
                "person_age INTEGER" +
                ")");
*/
        /*
        for (int i = 0; i < 20; i++) {
            database.execSQL("INSERT INTO person (person_name,person_family,person_age) VALUES ('Name#" + i + "','Family#" + i + "'," + i + ")");
        }
        */


      /*  database.execSQL("DELETE FROM person WHERE person_age>10");
        database.execSQL("UPDATE person SET person_name='test' WHERE person_age>7");*/


        if (isNetworkConnected()) {
          //  readFromNet();
           // prepareMovieData();

        } else {
            StartupActivity.connected = false;
        }

    }


    public void copyDataBase() throws IOException {
        InputStream mInput =  context.getAssets().open(DB_NAME);
        String outfileName = DIR_FINAL+"/varzesh.db";
        OutputStream mOutput = new FileOutputStream(outfileName);
        byte[] buffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(buffer))>0) {
            mOutput.write(buffer, 0, mLength);
        }
        mOutput.flush();
        mInput.close();
        mOutput.close();
    }






    public static void makeSdPath(){
    File file =  new File(Environment.getExternalStorageDirectory(),"twitak");
    boolean sc =file.mkdirs();
    if(sc){
        Log.i("LOG","Craeted");
    }else {
        Log.i("LOG","Not Craeted");
    }
        boolean s3=   new File(DIR_TEMP).mkdirs();
        boolean s2= new File(DIR_FINAL).mkdirs();

    }



   /* public static void readFromNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpHandler.makeServiceCall("http://blackd.ir/tw");
                Log.i("Kala", json + "Is the json..........");
                //Log.i("MOL",+"uuuu");


                //   JsonObject jsonObject  = new JsonParser().parse(HttpHandler.makeServiceCall("http://192.168.1.33/server/api/products")).getAsJsonObject();
                JSONArray jsonarray = null;
                String name = null, text,id, cDate,des, picture = null;
                ReportHelper.writeStream(json);

               *//* try {

                    jsonarray = new JSONArray(json);
                    //Log.i("MOL", json+"Is the json..........");
                    // Log.i("MOL", jsonarray.length()+"Is the size..........");
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        text = jsonobject.getString("text");
                        id = jsonobject.getString("id");
                        cDate = jsonobject.getString("created_at");
                        JSONObject entities = jsonobject.getJSONObject("entities");
                        JSONArray mention = entities.getJSONArray("user_mentions");
                        for (int j = 0; j < mention.length(); j++) {
                            JSONObject jS = mention.getJSONObject(j);
                            name = jS.getString("name");
                            //  Log.i("MOL", jS.getString("name"));
                        }

                        picture = jsonobject.getString("id");
                        Movie movie = new Movie(name, text, cDate, picture);
                        //ReportHelper.insertData(id,name,cDate,text);

                        G.movieList.add(movie);
                        Log.i("MOL", "" + name + "|||||||" + text);
                        Log.i("MOL", "" + i);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                OneFragment.mAdapter.notifyDataSetChanged();
                            }
                        });

                        MainActivity.progressDialog.dismiss();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*//*

                try {
                    json=json.substring(12,json.length());
                    Log.i("MOL", json+"Is the json..........");
                    jsonarray = new JSONArray(json);

                    // Log.i("MOL", jsonarray.length()+"Is the size..........");
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        text = jsonobject.getString("text");
                        id = jsonobject.getString("id");
                        cDate = jsonobject.getString("created_at");
                        JSONObject entities = jsonobject.getJSONObject("entities");
                        JSONArray mention = entities.getJSONArray("user_mentions");
                        JSONObject profile = jsonobject.getJSONObject("user");
                        des=profile.getString("description");
                        location=profile.getString("location");
                        screen_name=profile.getString("screen_name");
                        followers_count=profile.getString("followers_count");
                        friends_count=profile.getString("friends_count");
                        statuses_count=profile.getString("statuses_count");
                        time_zone=profile.getString("time_zone");
                       // time_zone=profile.getString("time_zone");
                        for (int j = 0; j < mention.length(); j++) {
                            JSONObject jS = mention.getJSONObject(j);
                            name = jS.getString("name");
                            //  Log.i("MOL", jS.getString("name"));
                        }

                        picture = jsonobject.getString("id");
                        Movie movie = new Movie(name, text, statuses_count, picture);
                        ReportHelper.insertData(id,name,cDate,text,location,des,time_zone,followers_count,friends_count,statuses_count,name);

                        G.movieList.add(movie);
                        Log.i("MOL", "" + name + "|||||||" + text);
                        Log.i("MOL", "" + i);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                OneFragment.mAdapter.notifyDataSetChanged();
                            }
                        });

                        MainActivity.progressDialog.dismiss();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
*/
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static void getDb(){
        Cursor cursor = database.rawQuery("SELECT * FROM workouts", null);
        G.movieList.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String family = cursor.getString(cursor.getColumnIndex("type"));
            String mainM = cursor.getString(cursor.getColumnIndex("desc"));
            int age = cursor.getInt(cursor.getColumnIndex("id"));
            Log.i("LOG", "Record: " + name + " " + family + ", " + age+", "+mainM);

            Movie movie;


            movie = new Movie(name, family, "1986","");
            G.movieList.add(movie);
        }

        cursor.close();
    }


    public static void prepareMovieData() {
        G.movieList.clear();
        Movie movie;


        movie = new Movie("حرکات جلو بازو", "Science Fiction", "1986","");
        G.movieList.add(movie);

        movie = new Movie("حرکات پشت بازو", "Animation", "2000","");
        G.movieList.add(movie);

        movie = new Movie(" حرکات ساعد", "Science Fiction", "1985","");
        G.movieList.add(movie);

        movie = new Movie("حرکات سرشانه", "Action & Adventure", "1981","");
        G.movieList.add(movie);

        movie = new Movie("حرکات جلو پا", "Action & Adventure", "1965","");
        G.movieList.add(movie);

        movie = new Movie("حرکات شکم", "Science Fiction & Fantasy", "2014","");
        G.movieList.add(movie);
        movie = new Movie("حرکات پشت", "Science Fiction & Fantasy", "2014","");
        G.movieList.add(movie);

    }



}
