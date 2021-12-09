package com.example.proyectotyam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class AreasActivity extends AppCompatActivity {

    Button busgoo,b1,b2,b3,b4,b5,b6,b7,b8;
    public static final String Area = "nomb_area";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areas);

        busgoo=(Button) findViewById(R.id.btnBuscaGoogle);
        b1=(Button) findViewById(R.id.btnAreaUno);
        b2=(Button) findViewById(R.id.btnAreaDos);
        b3=(Button) findViewById(R.id.btnAreaTres);
        b4=(Button) findViewById(R.id.btnAreaCuatro);
        b5=(Button) findViewById(R.id.btnAreaCinco);
        b6=(Button) findViewById(R.id.btnAreaSeis);
        b7=(Button) findViewById(R.id.btnAreaSiete);
        b8=(Button) findViewById(R.id.btnAreaOcho);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Matematicas");
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Administrativo");
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Cocina");
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Informatica");
                startActivity(intent);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Lengua");
                startActivity(intent);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Logistica");
                startActivity(intent);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Musica");
                startActivity(intent);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext (),ArchivosActivity.class);
                intent.putExtra(Area, "Ciencias de la Salud");
                startActivity(intent);
            }
        });

        busgoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AreasActivity.this,BuscaGoogle.class);
                startActivity(intent);
            }
        });
    }
}
