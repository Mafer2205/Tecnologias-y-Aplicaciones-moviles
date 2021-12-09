package com.example.proyectotyam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button bIS;
    private Button bRU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

        bIS=(Button) findViewById(R.id.btnIniciarSesion);
        bRU=(Button) findViewById(R.id.btnCrearCuenta);
        TuttorDatabase database = new TuttorDatabase (this);

        bIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,InicioSesion.class);
                startActivity(intent);
            }
        });

        bRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });


        if (database.getContactsCount ()  == 0) {
            database.addArea (new Areas ("Matematicas"));
            database.addArea (new Areas ("Lengua"));
            database.addArea (new Areas ("Informatica"));
            database.addArea (new Areas ("Ciencias de la Salud"));
            database.addArea (new Areas ("Cocina"));
            database.addArea (new Areas ("Musica"));
            database.addArea (new Areas ("Logistica"));
            database.addArea (new Areas ("Administrativo"));
        }

        if (database.getArchCount ()  == 0) {
            database.addArchivo(new Archivos("Divisiones binarias.pdf", 1));
            database.addArchivo(new Archivos("Multiplicaciones en binario.pdf", 1));
            database.addArchivo(new Archivos("Reglas en el uso del idioma inglés.pdf", 2));
            database.addArchivo(new Archivos("Tiempos en el uso del idioma inglés.pdf", 2));
            database.addArchivo(new Archivos("Historia de la IA.pdf", 3));
            database.addArchivo(new Archivos("Agentes en IA.pdf", 3));
            database.addArchivo(new Archivos("Aprendiendo del Microscopio.pdf", 4));
            database.addArchivo(new Archivos("Bacteria.pdf", 4));
            database.addArchivo(new Archivos("Tips para la cocina.pdf", 5));
            database.addArchivo(new Archivos("Utensilios para postres.pdf", 5));
            database.addArchivo(new Archivos("Música.pdf", 6));
            database.addArchivo(new Archivos("Elementos de la musica.pdf", 6));
            database.addArchivo(new Archivos("Mercado meta.pdf", 7));
            database.addArchivo(new Archivos("Sector comercial.pdf", 7));
            database.addArchivo(new Archivos("Admnistracion Financiera.pdf", 8));
            database.addArchivo(new Archivos("Conceptos Basicos de la Administracion.pdf", 8));
        }
    }
}