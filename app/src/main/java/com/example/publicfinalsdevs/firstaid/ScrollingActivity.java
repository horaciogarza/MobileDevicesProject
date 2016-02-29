package com.example.publicfinalsdevs.firstaid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.rey.material.widget.Spinner;

import com.rey.material.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ScrollingActivity extends AppCompatActivity {

    private static HashMap<String, Integer> layoutViewsHashmap = new HashMap<>();
    Spinner spinnerTypeBlood, spinnerMaritalStatus;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        putDataFromLayoutIntoVars();
        setTextSizeOnViews();
        putDataOnSpinners();


//
//        fab.setOnClickListener(new View.OnCli                                                         ckListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

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
}
