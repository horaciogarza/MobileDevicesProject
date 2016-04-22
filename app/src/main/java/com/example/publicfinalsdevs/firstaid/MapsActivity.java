package com.example.publicfinalsdevs.firstaid;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

/**
 * Created by diego on 19/02/2016.
 */
public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Buscar Hospitales Cercanos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(MapsActivity.this, Map.class));
            }
        });
        initListView();
    }

    private void initListView() {

        String[] lNames = new String[]{"a", "b", "c"};
        String[] lDesc = new String[]{"asd", "bnm", "cvb"};
        Integer[] imgId = new Integer[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations);
        //StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, locations);

        StableArrayAdapter adapter = new StableArrayAdapter(this, lNames, lDesc, imgId);
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

    private BottomSheetDialog createBottomDialog(String title, String desc) {
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
                Snackbar.make(v, "Abrir Mapa", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(MapsActivity.this, Map.class));
            }
        });
        Button btn_delete = (Button) v.findViewById(R.id.delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
                Snackbar.make(v, "Eliminar Ubicacion de la Lista", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });
        dialog.contentView(v);
        return dialog;

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] itemName;
        private final String[] descriptions;
        private final Integer[] imgId;

        public StableArrayAdapter(Activity context, String[] itemName, String[] descriptions, Integer[] imgId) {
            super(context, R.layout.location_list, itemName);
            this.context=context;
            this.itemName=itemName;
            this.descriptions = descriptions;
            this.imgId=imgId;
        }

        public View getView(final int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.location_list, null,true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.locationName);
            ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.imageButton);
            TextView desc = (TextView) rowView.findViewById(R.id.description);

            LinearLayout info = (LinearLayout) rowView.findViewById(R.id.info);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createBottomDialog(itemName[position], descriptions[position]).show();
                }
            });
            txtTitle.setText(itemName[position]);
            imageButton.setImageResource(R.drawable.common_ic_googleplayservices);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Ruta al Hospital", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //Map StartIntent
                    startActivity(new Intent(MapsActivity.this, Map.class));
                    //startActivity(new Intent(MapsActivity.this, Map.class, new Bundle()));
                }
            });
            desc.setText("Description " + descriptions[position]);
            return rowView;
        };

    }

}
