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

<<<<<<< HEAD
import android.app.AlertDialog;
import android.content.DialogInterface;
=======
import android.content.Context;
>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import database.SQLHelper;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
=======

import database.SQLHelper;

public class MainActivity extends AppCompatActivity {
>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a

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

<<<<<<< HEAD
        //startActivity(new Intent(MainActivity.this, MapsActivity.class));

        if(mDatabase.isUserInfo()) {
            Log.i("Database Status", "Database  exists");
        }else {
            Intent intent = new Intent(MainActivity.this, StartUp.class);
            intent.putExtra("edit", false);
            startActivity(intent);
            finish();
        }

        //---Declaracion y Asociasion de botones con XML
        Button btn_mapa;   btn_mapa = (Button) findViewById(R.id.btnmap);
        Button btn_user;   btn_user = (Button) findViewById(R.id.btnperfil);
        Button btn_SOS;    btn_SOS = (Button) findViewById(R.id.btnSOS);
        Button btn_contactos;   btn_contactos = (Button) findViewById(R.id.btncontactos);
        Button btn_fa;  btn_fa = (Button) findViewById(R.id.btnfa);
        Button btn_emergencyNum = (Button) findViewById(R.id.emergencyNum);

        btn_contactos.setOnClickListener(this);
        btn_mapa.setOnClickListener(this);
        btn_user.setOnClickListener(this);
        btn_SOS.setOnClickListener(this);
        btn_emergencyNum.setOnClickListener(this);

        btn_SOS.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_LONG).show();
                alertMessage();
                return true;
            }
        });
        btn_fa.setOnClickListener(this);





    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncontactos:

                break;
            case R.id.btnperfil:
                Intent perfil = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(perfil);
                break;

            case R.id.btnmap:
                Intent mapa = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapa);

                break;
            case R.id.btnSOS:
                Toast.makeText(MainActivity.this, "Deje Presionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnfa:
                Intent fa = new Intent(MainActivity.this, FA.class);
                startActivity(fa);
                break;
            case R.id.emergencyNum:
                Intent phoneNumber = new Intent(MainActivity.this, Phones.class);
                try{
                    startActivity(phoneNumber);
                }
                catch (Exception ex){
                    Log.v("ERROR", ex.getMessage());
                }

                break;


        }
    }

    public boolean alertMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Llamada de Emergencia");
        builder.setMessage("Hola");
        builder.setNegativeButton("No llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

        return true;
=======
        startActivity(new Intent(MainActivity.this, ScrollingActivity.class));

//        if(mDatabase.isUserInfo()) {
//            Log.i("Database Status", "Database  exists");
//        }else {
//            startActivity(new Intent(MainActivity.this, StartUp.class));
//            finish();
//
//        }


>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a
    }
}
