package com.example.publicfinalsdevs.firstaid;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by diego on 12/04/2016.
 */
public class FAInfo extends AppCompatActivity {
    TextView infoTitle, infoText;
    ImageView titleIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        infoTitle = (TextView) findViewById(R.id.infoTitle);
        infoText = (TextView) findViewById(R.id.infoText);

        infoTitle.setText("Titulo");
        infoText.setText("Texto");

    }
}
