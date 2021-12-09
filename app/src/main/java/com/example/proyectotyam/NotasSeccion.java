package com.example.proyectotyam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotasSeccion extends Activity {
    private Button bmatematicas;
    private Button blengua;
    private Button binformatica;
    private Button bsalud;
    private Button bcocina;
    private Button bmusica;
    private Button blogistica;
    private Button badmin;
    private Button bgen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion_notas);

        bmatematicas=(Button) findViewById(R.id.mate);
        blengua=(Button) findViewById(R.id.lengua);
        binformatica=(Button) findViewById(R.id.infor);
        bsalud=(Button) findViewById(R.id.salud);
        bcocina=(Button) findViewById(R.id.cocina);
        bmusica=(Button) findViewById(R.id.musica);
        blogistica=(Button) findViewById(R.id.logistic);
        badmin=(Button) findViewById(R.id.admin);
        bgen=(Button) findViewById(R.id.gen);

        bgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,GeneralNota.class);
                startActivity(intent);
            }
        });

        bmatematicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,MatematicasNota.class);
                startActivity(intent);
            }
        });

        blengua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,LenguaNota.class);
                startActivity(intent);
            }
        });

        binformatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,InformaticaNota.class);
                startActivity(intent);
            }
        });

        bsalud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,SaludNota.class);
                startActivity(intent);
            }
        });

        bcocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,CocinaNota.class);
                startActivity(intent);
            }
        });

        bmusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,MusicaNota.class);
                startActivity(intent);
            }
        });

        blogistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,LogisticaNota.class);
                startActivity(intent);
            }
        });

        badmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotasSeccion.this,AdministrativoNota.class);
                startActivity(intent);
            }
        });
    }
}
