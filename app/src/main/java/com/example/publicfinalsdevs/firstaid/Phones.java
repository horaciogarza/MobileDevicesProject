package com.example.publicfinalsdevs.firstaid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Phones extends AppCompatActivity {


    Integer prgmImages[] = {R.drawable.doctor_phones, R.drawable.fireman_phones,
            R.drawable.animal_phones, R.drawable.nine_phones, R.drawable.police_phones};

    String prgmNameList[] = {"Ambulancia", "Bomberos",
            "Proteccion Civil", "911", "Policia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phones);

        CustomAdapter androidListAdapter = new CustomAdapter(this, prgmImages, prgmNameList);
        ListView androidListView = (ListView) findViewById(R.id.phonesListView);
        androidListView.setAdapter(androidListAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                       // TODO Auto-generated method stub

                       ++position;
                       switch (position) {
                           case 1:
                               call("83499153");
                               break;

                           case 2:
                               call("01 81 4040 0022");
                               break;

                           case 3:
                               call("018183439530");
                               break;

                           case 4:
                               call("911");
                               break;

                           case 5:
                               call("81355900");
                               break;



                        }
                    }
                }
            );







    }

    public void call(String phoneNumber){

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));

        try{
            startActivity(callIntent);
        }
        catch(Exception ex){
            Log.v("PHONES", ex.getMessage());
        }

    }
}


