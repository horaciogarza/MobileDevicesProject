package com.example.publicfinalsdevs.firstaid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by diego on 12/04/2016.
 */
public class FAInfo extends AppCompatActivity {
    TextView infoTitle, infoText;
    ImageView titleIcon;
    StringBuilder total = new StringBuilder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle data = getIntent().getExtras();
        infoTitle = (TextView) findViewById(R.id.infoTitle);
        titleIcon = (ImageView) findViewById(R.id.imgTitle);
        infoText = (TextView) findViewById(R.id.infoText);
        try {
            InputStream inputStream = getAssets().open(data.getString("src"));
            System.out.println(inputStream.toString());
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            alertMessage("Error", e.getMessage(), R.drawable.ic_error_black_48dp);
        }

        if(!data.isEmpty()) {
            infoTitle.setText(data.getString("title"));
            titleIcon.setImageResource(data.getInt("icon"));
            infoText.setText(Html.fromHtml(total.toString()));
        }

    }

    public void alertMessage(String title, String message, int icon){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(icon);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}
