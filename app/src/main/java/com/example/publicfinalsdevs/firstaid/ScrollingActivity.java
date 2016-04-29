package com.example.publicfinalsdevs.firstaid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rey.material.widget.EditText;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<EditText> fieldString = new ArrayList<EditText>(20);

        EditText nameField = (EditText) findViewById(R.id.nameField);
        EditText addressField = (EditText) findViewById(R.id.addressField);
        EditText cityField = (EditText) findViewById(R.id.cityField);
        EditText emailField = (EditText) findViewById(R.id.emailField);
        EditText homePhoneField = (EditText) findViewById(R.id.homePhoneField);
        EditText zipCodeField = (EditText) findViewById(R.id.zipCodeField);
        EditText mobileField = (EditText) findViewById(R.id.mobileField);





        nameField.setTextSize(20);



//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}
