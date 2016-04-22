package com.example.publicfinalsdevs.firstaid;

import android.os.Bundle;
<<<<<<< HEAD
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
=======
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.rey.material.widget.Spinner;
>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a

import com.rey.material.widget.EditText;

import java.util.ArrayList;
<<<<<<< HEAD

public class ScrollingActivity extends AppCompatActivity {

=======
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ScrollingActivity extends AppCompatActivity {

    private static HashMap<String, Integer> layoutViewsHashmap = new HashMap<>();
    Spinner spinnerTypeBlood, spinnerMaritalStatus;
    Toolbar toolbar;


>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
<<<<<<< HEAD
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
=======


        putDataFromLayoutIntoVars();
        setTextSizeOnViews();
        putDataOnSpinners();


//
//        fab.setOnClickListener(new View.OnCli                                                         ckListener() {
>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
<<<<<<< HEAD
=======

    private void putDataFromLayoutIntoVars() {

        //The single ones
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerTypeBlood = (Spinner) findViewById(R.id.spinner_blood_type);
        spinnerMaritalStatus = (Spinner) findViewById(R.id.spinner_marital_status);

        //The group ones
        layoutViewsHashmap.put("name", R.id.nameField);
        layoutViewsHashmap.put("adress", R.id.addressField);
        layoutViewsHashmap.put("city", R.id.cityField);
        layoutViewsHashmap.put("email", R.id.emailField);
        layoutViewsHashmap.put("homePhone", R.id.homePhoneField);
        layoutViewsHashmap.put("zipCode", R.id.zipCodeField);
        layoutViewsHashmap.put("mobileField", R.id.mobileField);

    }


    private void putDataOnSpinners() {

        List<String> typeBlood = new ArrayList<String>();
        typeBlood.add("AB+");
        typeBlood.add("AB-");
        typeBlood.add("A+");
        typeBlood.add("A-");
        typeBlood.add("B+");
        typeBlood.add("B-");
        typeBlood.add("O+");
        typeBlood.add("O-");

        ArrayAdapter<String> adapterTypeBlood = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typeBlood);
        adapterTypeBlood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeBlood.setAdapter(adapterTypeBlood);


        List<String> maritalStatus = new ArrayList<String>();

        maritalStatus.add(getString(R.string.marital_status_single));
        maritalStatus.add(getString(R.string.marital_status_single));

        ArrayAdapter<String> adapterMaritalStatus = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typeBlood);
        adapterMaritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaritalStatus.setAdapter(adapterMaritalStatus);


    }

    private void setTextSizeOnViews() {

        Set<String> keys = layoutViewsHashmap.keySet();

        for (String key : keys) {

            try {

                ((EditText) findViewById(layoutViewsHashmap.get(key))).setTextSize(17);
            } catch (Exception ex) {
                Log.e("ERROR: ", "Error trying to change the Views, please check " +
                        "Hashmap of the ScrollingActivity.java");
            }

        }
    }
>>>>>>> f9bf67acfff3fa6803241a5981bb6ba326a1695a
}
