package com.example.publicfinalsdevs.firstaid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import database.PersonalInfoDBSchema;
import database.SQLHelper;

/**
 * Created by diego on 11/04/2016.
 */
public class ProfileActivity extends AppCompatActivity{
    TextView nombreText, edadText, bloodTypeText, extraText;
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        nombreText = (TextView) findViewById(R.id.nombreText);
        edadText = (TextView) findViewById(R.id.edadText);
        bloodTypeText = (TextView) findViewById(R.id.bloodTypeText);
        extraText = (TextView) findViewById(R.id.extraText);

        data = new SQLHelper(this).getData(PersonalInfoDBSchema.UserDataTable.NAME);

        if(data.getCount() == 0){
            return;
        }

        //StringBuffer stringBuffer = new StringBuffer();
        while(data.moveToNext()){
            //stringBuffer.append()
            nombreText.setText(data.getString(0));
            edadText.setText(data.getString(1));
            bloodTypeText.setText(data.getString(2));
            extraText.setText(data.getString(3));
        }


        /*nombreText.setText(data.getString(data.getColumnIndex("name")));
        edadText.setText(data.getString(data.getColumnIndex("age")));
        bloodTypeText.setText(data.getString(data.getColumnIndex("bloodtype")));
        extraText.setText(data.getString(data.getColumnIndex("addInfo")));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                /*Toast.makeText(this, "Opciones", Toast.LENGTH_SHORT)
                        .show();*/
                //Bundle bundleData = new Bundle();
                Intent intent = new Intent(ProfileActivity.this, StartUp.class);
                intent.putExtra("edit", true);
                intent.putExtra("name", nombreText.getText().toString());
                intent.putExtra("age", edadText.getText().toString());
                intent.putExtra("bloodType", bloodTypeText.getText().toString());
                intent.putExtra("extraInfo", extraText.getText().toString());
                /*bundleData.putString("name", nombreText.getText().toString());
                bundleData.putCharSequence("age", edadText.getText().toString());
                bundleData.putCharSequence("bloodType", bloodTypeText.getText().toString());
                bundleData.putString("extraInfo", extraText.getText().toString());*/
                //startActivity(new Intent(ProfileActivity.this, StartUp.class, bundleData));
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }

}
