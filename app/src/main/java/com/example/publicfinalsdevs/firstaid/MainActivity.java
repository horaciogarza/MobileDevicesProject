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

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import database.PersonalInfoDBSchema;
import database.SQLHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private SQLHelper mDatabase;
    protected GoogleApiClient googleApiClient;
    private Location lastLocation;
    private LatLng lastLatLng;
    private final String TAG = MainActivity.class.getName();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildGoogleApiClient();
        mDatabase = new SQLHelper(this);

        //startActivity(new Intent(MainActivity.this, MapsActivity.class));

        if (mDatabase.isUserInfo()) {
            Log.i("Database Status", "Database  exists");
        } else {
            Intent intent = new Intent(MainActivity.this, StartUp.class);
            intent.putExtra("edit", false);
            startActivity(intent);
            finish();
        }

        //---Declaracion y Asociasion de botones con XML
        Button btn_mapa;
        btn_mapa = (Button) findViewById(R.id.btnmap);
        Button btn_user;
        btn_user = (Button) findViewById(R.id.btnperfil);
        Button btn_SOS;
        btn_SOS = (Button) findViewById(R.id.btnSOS);
        Button btn_contactos;
        btn_contactos = (Button) findViewById(R.id.btncontactos);
        Button btn_fa;
        btn_fa = (Button) findViewById(R.id.btnfa);

        Button btnPhones = (Button) findViewById(R.id.btnPhones);

        btnPhones.setOnClickListener(this);
        btn_contactos.setOnClickListener(this);
        btn_mapa.setOnClickListener(this);
        btn_user.setOnClickListener(this);
        btn_SOS.setOnClickListener(this);

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

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastLocation != null) {
            lastLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncontactos:
                Toast.makeText(MainActivity.this, "Contactos", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnperfil:
                Intent perfil = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(perfil);
                break;
            case R.id.btnmap:
                Intent mapa = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(mapa);
                break;
            case R.id.btnSOS:
                Toast.makeText(MainActivity.this, "Deje Presionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnfa:
                Intent fa = new Intent(MainActivity.this, FA.class);
                startActivity(fa);
                break;
            case R.id.btnPhones:
                Toast.makeText(MainActivity.this, "Phones", Toast.LENGTH_SHORT).show();
                Intent phones = new Intent(MainActivity.this, Phones.class);
                startActivity(phones);
                break;



        }
    }

    public boolean alertMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Llamada de Emergencia");
        builder.setMessage("Desea llamar a los servicios de Emergencia?");
        builder.setNegativeButton("No llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                secondAlert();
            }
        });
        builder.show();

        return true;
    }

    public boolean secondAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Llamada de Emergencia");
        builder.setMessage("Esta seguro?");
        builder.setNegativeButton("No llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                googleApiClient.connect();
                double lat = lastLatLng.latitude;
                double lng = lastLatLng.longitude;
                String name = null;
                String age = null;
                String blood = null;
                String extra = null;
                Cursor data = mDatabase.getData(PersonalInfoDBSchema.UserDataTable.NAME);
                if(data.getCount() == 0){
                    return;
                }
                while(data.moveToNext()){
                    name = data.getString(0);
                    age = data.getString(1);
                    blood = data.getString(2);
                    extra = data.getString(3);
                }
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+5218112157495",null,
                        "Ubicacion(Lat: "+lat+" Lng: "+lng + ")",null, null
                );
                smsManager.sendTextMessage("+5218112157495",null,
                        "Nombre: "+name,null, null
                );
                smsManager.sendTextMessage("+5218112157495",null,
                        "Edad: "+age+" - Tipo de Sangre: "+blood,null, null
                );
                /*smsManager.sendTextMessage("+5218112157495",null,
                        "Tipo de Sangre: "+blood,null, null
                );*/
                smsManager.sendTextMessage("+5218112157495",null,
                        "Info. Adicional: "+extra,null, null
                );
                answerAlert();
            }
        });
        builder.show();

        return true;
    }

    public boolean answerAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Llamada de Emergencia");
        builder.setMessage("La ayuda esta en camino");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        builder.show();

        return true;
    }

}
