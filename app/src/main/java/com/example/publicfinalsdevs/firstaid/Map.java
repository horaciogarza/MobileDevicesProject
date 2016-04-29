package com.example.publicfinalsdevs.firstaid;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by diego on 21/02/2016.
 */
public class Map extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private final String TAG = "Map";

    protected GoogleApiClient googleApiClient;
    protected MapFragment mapFragment;

    //First mode
    private Location lastLocation;
    static LatLng lastLatLng;
    static boolean searchfinish = false;
    public Bundle data;
    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        JSONTask.message = null;
        buildGoogleApiClient();
        data = getIntent().getExtras();
        dialog = ProgressDialog.show(this, "Cargando", "Porfavor Espere", true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

    }

    public void doPositiveClick(char type) {
        // Do stuff here.
        switch (type) {
            case '1':
                break;
            case '2':
                finish();
                break;
        }
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    public void doNegativeClick() {
        // Do stuff here.
        Log.i("FragmentAlertDialog", "Negative click!");
    }

    public boolean alertMessage(String title, String message){
        char t =message.charAt(0);
        message = message.substring(1);
        DialogFragment newFragment = MyAlertDialogFragment.newInstance(title, message, t);
        newFragment.show(getFragmentManager(), "dialog");

        return true;
    }

    @Override
    public void onMapReady(GoogleMap map) {

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

        //First mode
        switch (data.getInt("mode")){
            case 1:
                map.setMyLocationEnabled(true);
                if(lastLocation != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 15));
                }
                searchPlaces();
                boolean mes = false;
                while (!searchfinish){
                    //Espera
                }
                if(JSONTask.message != null){
                    String message = JSONTask.message;
                    alertMessage("Error", message);
                    dialog.dismiss();
                }
                dialog.dismiss();
                for(int i=0; JSONTask.placesArrayList.size()>i;i++){
                    String lat = JSONTask.placesArrayList.get(i).getLat();
                    Double latD = Double.parseDouble(lat);
                    String lng = JSONTask.placesArrayList.get(i).getLng();
                    Double lngD = Double.parseDouble(lng);
                    String name = JSONTask.placesArrayList.get(i).getName();
                    String address = JSONTask.placesArrayList.get(i).getAddress();
                    String placeId = JSONTask.placesArrayList.get(i).getPlaceId();
                    map.addMarker(new MarkerOptions()
                                    .position(new LatLng(latD,
                                                        lngD))
                                    .title(name)
                                    .snippet(address));
                }
                break;
            case 2:
                map.setMyLocationEnabled(true);
                String lat = data.getString("lat");
                Double latD = Double.parseDouble(lat);
                String lng = data.getString("lng");
                Double lngD = Double.parseDouble(lng);
                LatLng placelatlng = new LatLng(latD,lngD);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(placelatlng, 15));
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(latD,
                                lngD))
                        .title(data.getString("name"))
                        .snippet(data.getString("address")));
                dialog.dismiss();
                break;
            default:

                break;
        }


        /*LatLng e1 = new LatLng(lastLatLng.latitude-.025, lastLatLng.longitude+.025);
        LatLng e2 = new LatLng(lastLatLng.latitude+.025, lastLatLng.longitude+.025);
        LatLng e3 = new LatLng(lastLatLng.latitude-.025, lastLatLng.longitude-.025);
        LatLng e4 = new LatLng(lastLatLng.latitude+.025, lastLatLng.longitude-.025);

        map.addMarker(new MarkerOptions().position(e1));
        map.addMarker(new MarkerOptions().position(e2));
        map.addMarker(new MarkerOptions().position(e3));
        map.addMarker(new MarkerOptions().position(e4));*/

    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
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
            mapFragment.getMapAsync(this);
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

    private void searchPlaces(){
        new JSONTask().execute();
    }

}

class JSONTask extends AsyncTask<String, String, String> {

    static ArrayList<com.example.publicfinalsdevs.firstaid.Places> placesArrayList = new ArrayList<>();
    StringBuffer stringBuffer = new StringBuffer();
    static String message = null;

    @Override
    protected String doInBackground(String... params) {
        System.out.println("BackGround");
        String page = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+Map.lastLatLng.latitude+","+Map.lastLatLng.longitude+"&radius=1000&types=hospital&key=AIzaSyA-JeVd1qcSRxVT90WqptZkZQgJjEhxziw";
        URL url = null;
        HttpsURLConnection connection = null;
        InputStream input = null;
        BufferedReader reader = null;
        try {
            url = new URL(page);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            input = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            String line = "";
            while((line = reader.readLine()) != null){
                stringBuffer.append(line);
            }

            String finalJSON = stringBuffer.toString();
            JSONObject parentObject = null;

            parentObject = new JSONObject(finalJSON);
            String resObjectStats = parentObject.getString("status");
            JSONArray resultsArray = parentObject.getJSONArray("results");

            if(resObjectStats.equals("OK")){
            for (int i=0; resultsArray.length()> i; i++) {
                System.out.println(i);
                JSONObject resultsObject = resultsArray.getJSONObject(i);
                JSONObject geometryObject = resultsObject.getJSONObject("geometry");
                JSONObject locationObject = geometryObject.getJSONObject("location");

                String lat = locationObject.getString("lat");
                String lng = locationObject.getString("lng");
                String placeId = resultsObject.getString("place_id");
                String name = resultsObject.getString("name");
                String address = resultsObject.getString("vicinity");
                com.example.publicfinalsdevs.firstaid.Places p = new com.example.publicfinalsdevs.firstaid.Places(placeId,address,name,lat,lng);
                placesArrayList.add(i, p);
            }
            }else {
                switch (resObjectStats) {
                    case "ZERO_RESULTS":
                        message = "1No hay hospitales cercanos";
                        break;
                }
            }

            //Datos en stringBuffer return
            Map.searchfinish = true;
            return message;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            message = "2"+e;
            Map.searchfinish = true;
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            message = "2"+e;
            Map.searchfinish = true;
            return message;
        } catch (JSONException e) {
            e.printStackTrace();
            message = "2"+e;
            Map.searchfinish = true;
            return message;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                message = "2"+e;
                return message;
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

class MyAlertDialogFragment extends DialogFragment {

    public static MyAlertDialogFragment newInstance(String title, String message, char type) {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        args.putChar("type", type);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        final char type = getArguments().getChar("type");

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_error_black_48dp)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((Map) getActivity()).doPositiveClick(type);
                            }
                        }
                )
                /*.setNegativeButton("R.string.alert_dialog_cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((Map)getActivity()).doNegativeClick();
                            }
                        }
                )*/
                .create();
    }
}

