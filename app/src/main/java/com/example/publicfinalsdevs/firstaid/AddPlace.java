package com.example.publicfinalsdevs.firstaid;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.*;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import database.PlacesDBSchema;
import database.SQLHelper;

/**
 * Created by diego on 25/04/2016.
 */
public class AddPlace extends FragmentActivity
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {


    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter mAdapter;

    private AutoCompleteTextView mAutocompleteView;

    private TextView mPlaceDetailsText;

    private TextView mPlaceDetailsAttribution;

    private static String TAG = AddPlace.class.getName();
    private Location lastLocation = null;
    private LatLng lastLatLng = null;
    private LatLngBounds rango = null;

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private TextView placeDatatxt;
    private Button savePlacebtn;
    private SQLHelper dataBase;
    com.example.publicfinalsdevs.firstaid.Places p;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_place);
        dataBase= new SQLHelper(this);

        placeDatatxt = (TextView) findViewById(R.id.placeData);
        savePlacebtn = (Button) findViewById(R.id.savePlacebtn);
        // Construct a GoogleApiClient for the {@link Places#GEO_DATA_API} using AutoManage
        // functionality, which automatically sets up the API client to handle Activity lifecycle
        // events. If your activity does not extend FragmentActivity, make sure to call connect()
        // and disconnect() explicitly.
        buildGoogleApiClient();



        /*txtSearch = (EditText) findViewById(R.id.txtSearchPlace);
        placeDataText = (TextView) findViewById(R.id.placeDataText);
        placeDataText1 = (TextView) findViewById(R.id.placeData1);
        placeDataText2 = (TextView) findViewById(R.id.placeData2);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAutocompleteActivity();
            }
        });*/
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    private void autoComplete(){
        // Retrieve the AutoCompleteTextView that will display Place suggestions.
        mAutocompleteView = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places);

        // Register a listener that receives callbacks when a suggestion has been selected
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        // Retrieve the TextViews that will display details and attributions of the selected place.
        /*mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceDetailsAttribution = (TextView) findViewById(R.id.place_attribution);*/

        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.



        //Filtro
        AutocompleteFilter.Builder filter = new AutocompleteFilter.Builder();
        //2 = FILTER_ADDRESS, 47 = FILTER_HEALTH, 50 = FILTER_HOSPITAL
        filter.setTypeFilter(2).setTypeFilter(47).setTypeFilter(50);
        AutocompleteFilter finalFilter = filter.build();



        mAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, rango,
                finalFilter);
        System.out.println(mAdapter.toString());
        mAutocompleteView.setAdapter(mAdapter);

        // Set up the 'clear text' button that clears the text in the autocomplete view
        Button clearButton = (Button) findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAutocompleteView.setText("");
            }
        });
    }

    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data API
     * to retrieve more details about the place.
     *
     * @see com.google.android.gms.location.places.GeoDataApi#getPlaceById(com.google.android.gms.common.api.GoogleApiClient,
     * String...)
     */
    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
            final AutocompletePrediction item = mAdapter.getItem(position);
            final String placeId = item.getPlaceId();
            final CharSequence primaryText = item.getPrimaryText(null);

            Log.i(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
             details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            /*Toast.makeText(getApplicationContext(), "Clicked: " + primaryText,
                    Toast.LENGTH_SHORT).show();*/
            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            // Format details of the place for display and show it in a TextView.
            /*mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
                    place.getId(), place.getAddress(), place.getPhoneNumber(),
                    place.getWebsiteUri()));*/

            String name = (String)place.getName();
            String address = (String) place.getAddress();
            String placeId = (String) place.getId();
            LatLng latLng = place.getLatLng();
            String lat = String.valueOf(latLng.latitude);
            String lng = String.valueOf(latLng.longitude);
            p =
                            new com.example.publicfinalsdevs.firstaid.Places(placeId,address,name,lat,lng);
            placeDatatxt.setText(formatPlaceDetails(getResources(), name, address));

            // Display the third party attributions if set.
            /*final CharSequence thirdPartyAttribution = places.getAttributions();
            if (thirdPartyAttribution == null) {
                mPlaceDetailsAttribution.setVisibility(View.GONE);
            } else {
                mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
                mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
            }*/

            Log.i(TAG, "Place details received: " + place.getName());

            places.release();

            savePlacebtn.setVisibility(View.VISIBLE);
            savePlacebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean db;
                    db = dataBase.insertData(PlacesDBSchema.PlacesDataTable.NAME,
                            new String[]{p.getPlaceId(),
                                    p.getName(),
                                    p.getAddress(),
                                    p.getLat(),
                                    p.getLng()});
                    if (db) {
                        Toast.makeText(AddPlace.this, "Se ha Guardado", Toast.LENGTH_SHORT).show();
                        Bundle conData = new Bundle();
                        conData.putBoolean("results", true);
                        Intent intent = new Intent();
                        intent.putExtras(conData);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(AddPlace.this, "Error en la base de Datos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
    }

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, CharSequence address){
        return Html.fromHtml(res.getString(R.string.placeData, name, address));
    }


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

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
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (lastLocation != null) {
            lastLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            rango = new LatLngBounds(new LatLng(lastLatLng.latitude-.025, lastLatLng.longitude-.025),
                                    new LatLng(lastLatLng.latitude+.025, lastLatLng.longitude+.025));
            autoComplete();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Error");
        alert.setMessage("Error en conexion con Google./nError:" + connectionResult.getErrorMessage());
        alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }
}

