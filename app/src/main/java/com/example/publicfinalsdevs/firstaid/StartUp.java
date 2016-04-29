package com.example.publicfinalsdevs.firstaid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.SQLHelper;
import database.PersonalInfoDBSchema;

/**
 * Created by diego on 08/02/2016.
 */
public class StartUp extends AppCompatActivity{

    SQLHelper mDataBase;

    EditText editName,editAddress,editCity, editEmail,editZipCode,editMoblie,editHomePhone,editMartial, editExtraInfo;
    Spinner spinnerTypeBlood, spinnerAge;
    //Button btnSend;

    String oldName;
    boolean edit= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_up);
        mDataBase = new SQLHelper(this);

        editName = (EditText) findViewById(R.id.nameText);
        editExtraInfo = (EditText) findViewById(R.id.extraInfoText);

        spinnerAge = (Spinner) findViewById(R.id.ageSpinner);

        List<String> age = new ArrayList<String>();
        for (int i = 1; i < 80; i++) {
            age.add("" + i);
        }

        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, age);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapterAge);
        spinnerAge.setPrompt("Edad");

        spinnerTypeBlood = (Spinner) findViewById(R.id.bloodTypeSpinner);

        //Items on Spinner TypeBlood
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
        spinnerTypeBlood.setPrompt("Tipo de Sangre");

        //btnSend = (Button) findViewById(R.id.saveUserDataButton);

        Bundle extra = getIntent().getExtras();

        if(extra.getBoolean("edit")){
            oldName = extra.getString("name");
            //editData(oldName);
            editName.setText(extra.getString("name"));
            if(extra.getString("extraInfo").compareTo("Ninguna") == 0){
            }else {
                editExtraInfo.setText(extra.getString("extraInfo"));
            }
            int spinnerpos;
            spinnerpos = adapterAge.getPosition(extra.getString("age"));
            spinnerAge.setSelection(spinnerpos);
            spinnerpos = adapterTypeBlood.getPosition(extra.getString("bloodType"));
            spinnerTypeBlood.setSelection(spinnerpos);
            edit = true;
        }else {
            //saveData();
        }

        /*editName = (EditText)findViewById(R.id.editText);
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
                        boolean result = mDataBase.insertData(PersonalInfoDBSchema.UserDataTable.NAME, new String[] {editName.getText().toString(),
                                editAddress.getText().toString(), spinnerTypeBlood.getSelectedItem().toString(), editCity.getText().toString(),
                                spinnerAge.getSelectedItem().toString(), editEmail.getText().toString(), editZipCode.getText().toString(), editMoblie.getText().toString(),
                                editHomePhone.getText().toString(), editMartial.getText().toString()});
                        if(result == true){
                            Toast.makeText(StartUp.this, "Thank you!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(StartUp.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(StartUp.this, "empty", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );*/

    }

    /*public void saveData() {
        btnSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*boolean result = mDataBase.insertData(PersonalInfoDBSchema.UserDataTable.NAME, new String[]{editName.getText().toString(),
                                editAddress.getText().toString(), spinnerTypeBlood.getSelectedItem().toString(), editCity.getText().toString(),
                                spinnerAge.getSelectedItem().toString(), editEmail.getText().toString(), editZipCode.getText().toString(), editMoblie.getText().toString(),
                                editHomePhone.getText().toString(), editMartial.getText().toString()});*/
                        /*extraInfoStatus();
                        boolean result = mDataBase.insertData(PersonalInfoDBSchema.UserDataTable.NAME,
                                new String[]{editName.getText().toString(), spinnerAge.getSelectedItem().toString(),
                                        spinnerTypeBlood.getSelectedItem().toString(), editExtraInfo.getText().toString()});
                        if (result == true) {
                            Toast.makeText(StartUp.this, "Thank you!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(StartUp.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(StartUp.this, "empty", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void editData(final String oldName){
        btnSend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*boolean result = mDataBase.insertData(PersonalInfoDBSchema.UserDataTable.NAME, new String[]{editName.getText().toString(),
                                editAddress.getText().toString(), spinnerTypeBlood.getSelectedItem().toString(), editCity.getText().toString(),
                                spinnerAge.getSelectedItem().toString(), editEmail.getText().toString(), editZipCode.getText().toString(), editMoblie.getText().toString(),
                                editHomePhone.getText().toString(), editMartial.getText().toString()});*/
                        /*extraInfoStatus();
                        boolean result = mDataBase.updateData(PersonalInfoDBSchema.UserDataTable.NAME,
                                oldName
                                , new String[]{editName.getText().toString(), spinnerAge.getSelectedItem().toString(),
                                        spinnerTypeBlood.getSelectedItem().toString(), editExtraInfo.getText().toString()});
                        if (result == true) {
                            Toast.makeText(StartUp.this, "Thank you!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(StartUp.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(StartUp.this, "empty", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }*/

    public void extraInfoStatus(){
        if(editExtraInfo.getText().toString().isEmpty()){
            editExtraInfo.setText("Ninguna");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_start_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_send:
                if(edit){
                    extraInfoStatus();
                    boolean result = mDataBase.updateData(PersonalInfoDBSchema.UserDataTable.NAME,
                            oldName
                            , new String[]{editName.getText().toString(), spinnerAge.getSelectedItem().toString(),
                                    spinnerTypeBlood.getSelectedItem().toString(), editExtraInfo.getText().toString()});
                    if (result == true) {
                        Toast.makeText(StartUp.this, "Thank you!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(StartUp.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(StartUp.this, "empty", Toast.LENGTH_LONG).show();
                    }
                }else{
                    extraInfoStatus();
                    boolean result = mDataBase.insertData(PersonalInfoDBSchema.UserDataTable.NAME,
                            new String[]{editName.getText().toString(), spinnerAge.getSelectedItem().toString(),
                                    spinnerTypeBlood.getSelectedItem().toString(), editExtraInfo.getText().toString()});
                    if (result == true) {
                        Toast.makeText(StartUp.this, "Thank you!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(StartUp.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(StartUp.this, "empty", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
        return true;
    }

    public boolean helpMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Ayuda");
        builder.setMessage("Informacion");
        builder.setIcon(R.drawable.info);
        builder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

        return true;
    }
}
