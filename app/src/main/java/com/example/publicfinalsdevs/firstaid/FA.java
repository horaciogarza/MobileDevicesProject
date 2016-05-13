package com.example.publicfinalsdevs.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by diego on 11/04/2016.
 */
public class FA extends AppCompatActivity implements View.OnClickListener{

    Intent intent;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa);
        intent = new Intent(FA.this, FAInfo.class);

        Button btnQuemadura = (Button) findViewById(R.id.btnquemadura);
        btnQuemadura.setOnClickListener(this);
        Button btnFractura = (Button) findViewById(R.id.btnfractura);
        btnFractura.setOnClickListener(this);
        Button btnVeneno = (Button) findViewById(R.id.btnveneno);
        btnVeneno.setOnClickListener(this);
        Button btnSangrado = (Button) findViewById(R.id.btnsangrado);
        btnSangrado.setOnClickListener(this);
        Button btnMordedura = (Button) findViewById(R.id.btnmordedura);
        btnMordedura.setOnClickListener(this);
        Button btnInconciente = (Button) findViewById(R.id.btnInconsciente);
        btnInconciente.setOnClickListener(this);
        Button btnAsfixia = (Button) findViewById(R.id.btnasfixia);
        btnAsfixia.setOnClickListener(this);
        Button btnEpilepsia = (Button) findViewById(R.id.btnepilepsia);
        btnEpilepsia.setOnClickListener(this);
        Button btnInfarto = (Button) findViewById(R.id.btninfarto);
        btnInfarto.setOnClickListener(this);
        Button btnEsquince = (Button) findViewById(R.id.btnesguince);
        btnEsquince.setOnClickListener(this);
        Button btnAsma = (Button) findViewById(R.id.btnAsma);
        btnAsma.setOnClickListener(this);
        Button btnCalor = (Button) findViewById(R.id.btncalor);
        btnCalor.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnquemadura:
                intent.putExtra("title", "Quemaduras");
                intent.putExtra("icon", R.drawable.quemadura);
                intent.putExtra("src", "quemaduras.txt");
                startActivity(intent);
                break;
            case R.id.btnfractura:
                intent.putExtra("title", "Fractura");
                intent.putExtra("icon", R.drawable.fractura);
                intent.putExtra("src", "fractura.txt");
                startActivity(intent);
                break;
            case R.id.btnveneno:
                intent.putExtra("title", "Veneno");
                intent.putExtra("icon", R.drawable.veneno);
                intent.putExtra("src", "veneno.txt");
                startActivity(intent);
                break;
            case R.id.btnsangrado:
                intent.putExtra("title", "Sangrado");
                intent.putExtra("icon", R.drawable.sangrado);
                intent.putExtra("src", "sangrado.txt");
                startActivity(intent);
                break;
            case R.id.btnmordedura:
                intent.putExtra("title", "Mordedura");
                intent.putExtra("icon", R.drawable.mordedura);
                intent.putExtra("src", "mordedura.txt");
                startActivity(intent);
                break;
            case R.id.btnInconsciente:
                intent.putExtra("title", "Inconsciente");
                intent.putExtra("icon", R.drawable.incon);
                intent.putExtra("src", "inconsciente.txt");
                startActivity(intent);
                break;
            case R.id.btnasfixia:
                intent.putExtra("title", "Asfixia");
                intent.putExtra("icon", R.drawable.asf);
                intent.putExtra("src", "asfixia.txt");
                startActivity(intent);
                break;
            case R.id.btnepilepsia:
                intent.putExtra("title", "Epilepsia");
                intent.putExtra("icon", R.drawable.epi);
                intent.putExtra("src", "epilepsia.txt");
                startActivity(intent);
                break;
            case R.id.btninfarto:
                intent.putExtra("title", "Infarto");
                intent.putExtra("icon", R.drawable.infarto);
                intent.putExtra("src", "infarto.txt");
                startActivity(intent);
                break;
            case R.id.btnesguince:
                intent.putExtra("title", "Esguince");
                intent.putExtra("icon", R.drawable.esguince);
                intent.putExtra("src", "esguince.txt");
                startActivity(intent);
                break;
            case R.id.btnAsma:
                intent.putExtra("title", "Asma");
                intent.putExtra("icon", R.drawable.asma);
                intent.putExtra("src", "asma.txt");
                startActivity(intent);
                break;
            case R.id.btncalor:
                intent.putExtra("title", "Calor");
                intent.putExtra("icon", R.drawable.calor);
                intent.putExtra("src", "calor.txt");
                startActivity(intent);
                break;
        }
    }
}
