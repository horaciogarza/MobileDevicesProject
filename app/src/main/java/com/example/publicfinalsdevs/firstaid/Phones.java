package com.example.publicfinalsdevs.firstaid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phones extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phones);

        ListView lv;
        Context context;
        ArrayList prgmName;
        int [] prgmImages={R.drawable.transport1,R.drawable.medical1,R.drawable.technology,R.drawable.transport,
                            R.drawable.sign, R.drawable.transport5};

        String [] prgmNameList={getResources().getString(R.string.epn_ambulance),
                getResources().getString(R.string.epn_firstAid),getResources().getString(R.string.epn_firemen),
                getResources().getString(R.string.epn_civil), getResources().getString(R.string.epn_oneNineNine),
                getResources().getString(R.string.epn_police)};
        context = this;

        lv = (ListView) findViewById(R.id.phonesListView);
        CustomAdapter phonesCustomAdapter = new CustomAdapter(this, prgmNameList, prgmImages);
        /*lv.setAdapter(phonesCustomAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                //Code goes here

            }
        });*/

    }
}


