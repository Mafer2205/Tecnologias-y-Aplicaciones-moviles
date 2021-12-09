package com.example.proyectotyam;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArchivosActivity extends Activity {
    String area;
    List<Archivos> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos);

        Intent intent = getIntent();
        if(intent == null) return;

        area = intent.getStringExtra(AreasActivity.Area);

        if (area.equals("Matematicas")) archivosMatematicas();
        else if (area.equals("Administrativo")) archivosAdministrativo();
        else if (area.equals("Cocina")) archivosCocina();
        else if (area.equals("Informatica")) archivosInformatica();
        else if (area.equals("Lengua")) archivosLengua();
        else if (area.equals("Logistica")) archivosLogistica();
        else if (area.equals("Musica")) archivosMusica();
        else if (area.equals("Ciencias de la Salud")) archivosSalud();
    }

    public void moveToDescription(Archivos item){
        Intent intent = new Intent(this,Visualizador.class);
        intent.putExtra("Archivos",item);
        startActivity(intent);
    }

    public void archivosMatematicas() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Multiplicaciones en binario", "#775447",0));
        elements.add(new Archivos("Divisiones binarias", "#607D8B",0));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosAdministrativo() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Administracion Financiera", "#775447",1));
        elements.add(new Archivos("Conceptos Basicos de la Administracion", "#607D8B",1));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosCocina() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Tips para la cocina", "#775447",2));
        elements.add(new Archivos("Utensilios para postres", "#607D8B",2));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosInformatica() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Historia de la IA", "#775447",3));
        elements.add(new Archivos("Agentes en IA", "#607D8B",3));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosLengua() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Reglas en el uso del idioma inglés", "#775447",4));
        elements.add(new Archivos("Tiempos en el uso del idioma inglés", "#607D8B",4));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosLogistica() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Mercado meta", "#775447",5));
        elements.add(new Archivos("Sector comercial", "#607D8B",5));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosMusica() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Música", "#775447",6));
        elements.add(new Archivos("Elementos de la música", "#607D8B",6));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void archivosSalud() {
        elements = new ArrayList<>();
        elements.add(new Archivos("Aprendiendo del Microscopio", "#775447",7));
        elements.add(new Archivos("Bacteria", "#607D8B",7));

        ArchivosAdapter listAdapter = new ArchivosAdapter(elements, this, new ArchivosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Archivos item) {
                moveToDescription(item);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}
