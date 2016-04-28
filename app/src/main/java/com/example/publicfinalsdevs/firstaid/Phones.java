package com.example.publicfinalsdevs.firstaid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phones extends AppCompatActivity {

    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_phones);

            ListView lv;
            Context context;

            context = this;

            lv = (ListView) findViewById(R.id.phonesListView);
            CustomAdapter phonesCustomAdapter = new CustomAdapter(this, prgmNameList, prgmImages);
            try{
                lv.setAdapter(phonesCustomAdapter);
            }
            catch(Exception ex){

                Log.v("Custom Adapter", ex.getMessage());
            }


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Code goes here

                }
            });
        String androidListViewStrings[] = {"Android ListView Example", "Android Custom ListView Example", "Custom ListView Example",
                "Android List Adapter", "Custom Adapter ListView", "ListView Tutorial",
                "ListView with Image and Text", "Custom ListView Text and Image", "ListView Custom Tutorial"};

        Integer image_id[] = {R.drawable.transport, R.drawable.transport, R.drawable.transport,
                R.drawable.transport, R.drawable.transport, R.drawable.transport,
                R.drawable.transport, R.drawable.transport, R.drawable.transport};*/


        Integer prgmImages[] = {R.drawable.transport1, R.drawable.medical1, R.drawable.technology,
                R.drawable.transport, R.drawable.sign, R.drawable.police};

        String  prgmNameList[] = {"Ambulancia","Primeros Auxilios","Bomberos",
                "Proteccion Civil", "911", "Policia"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_phones);
            try{
                CustomAdapter androidListAdapter = new CustomAdapter(this, prgmImages, prgmNameList);
                ListView androidListView = (ListView) findViewById(R.id.phonesListView);
                androidListView.setAdapter(androidListAdapter);

            }
            catch(Exception ex){
                Log.v("Error_phones.java", ex.getMessage());
            }


    }
}


