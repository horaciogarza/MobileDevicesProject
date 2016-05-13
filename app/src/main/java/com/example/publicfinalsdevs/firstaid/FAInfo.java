package com.example.publicfinalsdevs.firstaid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by diego on 12/04/2016.
 */
public class FAInfo extends AppCompatActivity {
    TextView infoTitle;
    static TextView infoText;
    static boolean imgLoad;
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
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
            alertMessage("Error", e.toString(), R.drawable.ic_error_black_48dp);
        }
        Spanned info = Html.fromHtml(total.toString(), new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                LevelListDrawable d = new LevelListDrawable();
                Drawable empty = getResources().getDrawable(R.drawable.asma, null);
                d.addLevel(0, 0, empty);
                d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());
                new LoadImage().execute(source, d);
                return d;
            }
        },null );

        if(!data.isEmpty()) {
            infoTitle.setText(data.getString("title"));
            titleIcon.setImageResource(data.getInt("icon"));
            infoText.setText(info);
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

class LoadImage extends AsyncTask<Object, Void, Bitmap> {

    private LevelListDrawable mDrawable;

    @Override
    protected Bitmap doInBackground(Object... params) {
        String source = (String) params[0];
        mDrawable = (LevelListDrawable) params[1];
        try {
            InputStream is = new URL(source).openStream();
            return BitmapFactory.decodeStream(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            BitmapDrawable d = new BitmapDrawable(bitmap);
            mDrawable.addLevel(1, 1, d);
            mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
            mDrawable.setLevel(1);
            // i don't know yet a better way to refresh TextView
            // mTv.invalidate() doesn't work as expected
            CharSequence t = FAInfo.infoText.getText();
            FAInfo.infoText.setText(t);
        }else{

        }
    }
}

