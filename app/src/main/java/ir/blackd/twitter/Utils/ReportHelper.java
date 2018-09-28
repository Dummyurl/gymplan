package ir.blackd.twitter.Utils;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import ir.blackd.twitter.G;

/**
 * Created by Armin on 03/03/2017.
 */

public class ReportHelper {

    private static SQLiteDatabase database;

    public static void writeStream(String data){
        File file =new File(G.DIR_FINAL,"Report"+UUID.randomUUID()+".txt");
        Date date =new Date();
        try {

            file.createNewFile();
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.append(data+System.getProperty("line.separator"));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void manageDatabase() {
        database = SQLiteDatabase.openOrCreateDatabase(G.DIR_APP + "/database.sqlite", null);

        database.execSQL("CREATE  TABLE  IF NOT EXISTS tw " +
                "(tw_id TEXT," +
                "tw_name TEXT, tw_date TEXT, tw_text TEXT, tw_usre_location TEXT, tw_user_description TEXT,tw_user_time_zone TEXT,tw_user_statuses_count TEXT,tw_user_followers,tw_user_friends TEXT)");


    }

    public static void insertData(String id,String name,String date,String text,String location,String description,String tirmzone,String followers,String friends,String statuses_count,String screen_name) {
        if (name==null)
            name = "ok";
        database.execSQL("INSERT INTO tw (tw_id,tw_name,tw_date,tw_text,tw_usre_location,tw_user_description," +
                "tw_user_time_zone,tw_user_statuses_count," +
                "tw_user_followers,tw_user_friends) VALUES ('"+ id +"','"+name.replaceAll("[\'\"]","")+"','"+date+"','"+text.replaceAll("[\'\"]","")+"','"
                +location.replaceAll("[\'\"]","")+"','"+description.replaceAll("[\'\"]","")+"','"+tirmzone+"','"+statuses_count+"','"+followers+"','"+friends+"');");



    }




}

