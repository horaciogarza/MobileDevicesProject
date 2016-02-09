package com.example.publicfinalsdevs.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.SQLHelper;
import database.UserDataDBSchema;

/**
 * Created by diego on 08/02/2016.
 */
public class StartUp extends AppCompatActivity{

    SQLHelper mDataBase;

    EditText editName,editAddress,editCity, editEmail,editZipCode,editMoblie,editHomePhone,editMartial;
    Spinner spinnerTypeBlood, spinnerAge;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up);
        mDataBase = new SQLHelper(this);

        editName = (EditText)findViewById(R.id.editText);
        editAddress = (EditText)findViewById(R.id.editText2);
        editCity = (EditText)findViewById(R.id.editText3);
        editEmail = (EditText)findViewById(R.id.editText4);
        editZipCode = (EditText)findViewById(R.id.editText5);
        editMoblie = (EditText)findViewById(R.id.editText6);
        editHomePhone = (EditText)findViewById(R.id.editText7);
        editMartial = (EditText)findViewById(R.id.editText8);

        spinnerAge = (Spinner)findViewById(R.id.spinner);

        List<String> age = new ArrayList<String>();
        for (int i = 1; i < 80; i++){
            age.add(""+i);
        }

        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,age);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapterAge);

        spinnerTypeBlood = (Spinner)findViewById(R.id.spinner2);

        //Items on Spinner TypeBlood
        List<String> typeBlood = new ArrayList<String>();
        typeBlood.add("AB+");typeBlood.add("AB-");
        typeBlood.add("A+");typeBlood.add("A-");
        typeBlood.add("B+");typeBlood.add("B-");
        typeBlood.add("O+");typeBlood.add("O-");

        ArrayAdapter<String> adapterTypeBlood = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,typeBlood);
        adapterTypeBlood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeBlood.setAdapter(adapterTypeBlood);

        btnSend = (Button) findViewById(R.id.button);
        addData();

    }

    public void addData(){
        btnSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean result = mDataBase.insertData(UserDataDBSchema.UserDataTable.NAME, new String[] {editName.getText().toString(),
                                editAddress.getText().toString(), spinnerTypeBlood.getSelectedItem().toString(), editCity.getText().toString(),
                                spinnerAge.getSelectedItem().toString(), editEmail.getText().toString(), editZipCode.getText().toString(), editMoblie.getText().toString(),
                                editHomePhone.getText().toString(), editMartial.getText().toString()});
                        if(result == true){
                            Toast.makeText(StartUp.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(StartUp.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(StartUp.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
