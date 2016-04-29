package com.example.publicfinalsdevs.firstaid;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.rey.material.drawable.ThemeDrawable;
import com.rey.material.util.ViewUtil;
import com.rey.material.widget.ListView;
import com.rey.material.drawable.CircularProgressDrawable;
import com.rey.material.app.BottomSheetDialog;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;

import database.PlacesDBSchema;
import database.SQLHelper;

/**
 * Created by diego on 19/02/2016.
 */
public class MapsActivity extends AppCompatActivity {

    Intent intent;
    static ArrayList<Places> places = new ArrayList<Places>();
    SQLHelper mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        intent = new Intent(MapsActivity.this, Map.class);
        mDataBase = new SQLHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Buscar Hospitales Cercanos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                intent.putExtra("mode", 1);
                startActivity(intent);
            }
        });
        FloatingActionButton addPlace = (FloatingActionButton) findViewById(R.id.addPlace);
        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, AddPlace.class);
                startActivityForResult(i, 90);
            }
        });
        getDataBase();
        //initListView();
    }

    /* Called when the second activity's finished */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 90:
                if (resultCode == RESULT_OK) {
                    Bundle res = data.getExtras();
                    boolean result = res.getBoolean("results");
                    if(result){
                        getDataBase();
                    }
                    Log.d("FIRST", "result:" + result);
                }
                break;
        }
    }

    private void getDataBase(){
        places.clear();

        Cursor c =mDataBase.getData(PlacesDBSchema.PlacesDataTable.NAME);
        if(c.getCount() == 0){
            //Message No data
            return;
        }
        while(c.moveToNext()){
            Places p = new Places(c.getString(0),
                                c.getString(1),
                                c.getString(3),
                                c.getString(2),
                                c.getString(4),
                                c.getString(5));
            places.add(p);
        }
        //Iniciar listView despues de obtener la base de Datos
        initListView();
    }

    private void initListView() {

        //String[] lNames = new String[]{"a", "b", "c"};
        //String[] lDesc = new String[]{"asd", "bnm", "cvb"};

        /*places.add(new Places("ChIJUUgAOQy_YoYRWhNLTxgRLKM", "Lázaro Cárdenas 2660, Mirador Recidencial, Monterrey",
                                "Bioanalisis S.A.", "25.62200129999999", "-100.2870768"));
        places.add(new Places("ChIJUUgAOQy_YoYRWhNLTxgRLKM", "Lázaro Cárdenas 2660, Mirador Recidencial, Monterrey",
                "Bioanalisis S.A.", "25.62200129999999", "-100.2870768"));
        places.add(new Places("ChIJUUgAOQy_YoYRWhNLTxgRLKM", "Lázaro Cárdenas 2660, Mirador Recidencial, Monterrey",
                "Bioanalisis S.A.", "25.62200129999999", "-100.2870768"));*/


        String[] lNames = new String[places.size()];
        String[] lDesc = new String[places.size()];
        for(int i=0;i<places.size();i++){
            lNames[i]=places.get(i).getName();
            lDesc[i]=places.get(i).getAddress();
        }


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations);
        //StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, locations);

        //StableArrayAdapter adapter = new StableArrayAdapter(this, lNames, lDesc);
        //StableArrayAdapter adapter = new StableArrayAdapter(this, places);
        StableArrayAdapter adapter = new StableArrayAdapter(this, lNames, lDesc, places);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listview);

    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private BottomSheetDialog createBottomDialog(String title, String desc, final int pos) {
        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.Material_App_BottomSheetDialog);
        View v = LayoutInflater.from(this).inflate(R.layout.location_info, null);
        ViewUtil.setBackground(v, new ThemeDrawable(R.layout.activity_maps));
        TextView locationName = (com.rey.material.widget.TextView) v.findViewById(R.id.locationName);
        locationName.setText(title);
        TextView description = (com.rey.material.widget.TextView) v.findViewById(R.id.description);
        description.setText(desc);
        Button btn_mapsearch = (Button) v.findViewById(R.id.mapsearch);
        btn_mapsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.heightParam(ViewGroup.LayoutParams.MATCH_PARENT);
                //Snackbar.make(v, "Abrir Mapa", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                intent.putExtra("mode", 2);
                intent.putExtra("lat", places.get(pos).getLat());
                intent.putExtra("lng", places.get(pos).getLng());
                intent.putExtra("name", places.get(pos).getName());
                intent.putExtra("address", places.get(pos).getAddress());
                startActivity(intent);
            }
        });
        Button btn_delete = (Button) v.findViewById(R.id.delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
                //Snackbar.make(v, "Eliminar Ubicacion de la Lista", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                mDataBase.deleteData(PlacesDBSchema.PlacesDataTable.NAME, "id", places.get(pos).getId());
                getDataBase();
                dialog.cancel();

            }
        });
        dialog.contentView(v);
        return dialog;

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        private Activity context;
        private String[] itemName;
        private String[] descriptions;
        private ArrayList<Places> places = new ArrayList<>();

        public StableArrayAdapter(Activity context, String[] itemName, String[] descriptions) {
            super(context, R.layout.location_list, itemName);
            this.context=context;
            this.itemName=itemName;
            this.descriptions = descriptions;
        }

        public StableArrayAdapter(Activity context, ArrayList<Places> places){
            super(context, R.layout.location_list);
            this.context = context;
            this.places = places;
        }

        public StableArrayAdapter(Activity context, String[] itemName, String[] descriptions, ArrayList<Places> places){
            super(context, R.layout.location_list, itemName);
            this.context = context;
            this.itemName=itemName;
            this.descriptions = descriptions;
            this.places = places;
        }

        public View getView(final int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.location_list, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.locationName);
            //Imagen
            ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.imageButton);
            TextView desc = (TextView) rowView.findViewById(R.id.description);
            LinearLayout info = (LinearLayout) rowView.findViewById(R.id.info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //createBottomDialog(itemName[position], descriptions[position], position).show();
                    createBottomDialog(places.get(position).getName(), places.get(position).getAddress(), position).show();
                }
            });
            //txtTitle.setText(itemName[position]);
            txtTitle.setText(places.get(position).getName());
            //Icon
            imageButton.setImageResource(R.drawable.ic_navigation_black_48dp);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Ruta al Hospital", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //Map StartIntent
                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+places.get(position).getLat()+","+places.get(position).getLng()+"&mode=d");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    //startActivity(new Intent(MapsActivity.this, Map.class));
                    //startActivity(new Intent(MapsActivity.this, Map.class, new Bundle()));
                }
            });
            //desc.setText(descriptions[position]);
            desc.setText(places.get(position).getAddress());
            return rowView;
        };

    }

}
