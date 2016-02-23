package com.example.publicfinalsdevs.firstaid;

/**
 * * LAST UPDATE OF RULES: 28/01/2016 BY: HORACIO
 * *
 * NOTES:
 * <NOTES FOR THE NEXT AUTHOR WHO WILL UPDATE HIS FILE GOES HERE>
 * <p/>
 * Rules of this Project:
 * <p/>
 * 1. Do not abuse from pushing into the GitHub Repo, only when you do more than 10 changes (or lees if it is necessary)
 * 2. When you push it into GitHub always use an Author
 * <p/>
 * Horacio ->  hgx95
 * Omar ->     FILL HERE
 * Diego ->    FILL HERE
 * <p/>
 * 3. Be careful what you move in the code, remember if you open it again, try to update it always, someone might made changes
 * before
 * <p/>
 * 4. BE CAREFUL WITH THE GRADLE AND MAVEN!
 **/

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import database.SQLHelper;

public class MainActivity extends AppCompatActivity {

    private SQLHelper mDatabase;

    /**
     * <b>onCreate</b> method creates the main activity but it will check if a database exists, if not it will launch StartUp.java activity
     * which is going to recollect all the necessary info
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = new SQLHelper(this);

        startActivity(new Intent(MainActivity.this, MapsActivity.class));

//        if(mDatabase.isUserInfo()) {
//            Log.i("Database Status", "Database  exists");
//        }else {
//            startActivity(new Intent(MainActivity.this, StartUp.class));
//            finish();
//
//        }


    }
}
