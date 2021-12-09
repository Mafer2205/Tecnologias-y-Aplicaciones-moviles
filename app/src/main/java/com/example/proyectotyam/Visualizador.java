package com.example.proyectotyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

public class Visualizador extends AppCompatActivity {

    private PDFView pdfView;
    Button bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador);

        Archivos element = (Archivos) getIntent().getSerializableExtra("Archivos");
        String name = element.getNomb_arch() + ".pdf" ;
        int area = element.getR_area();

        bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (area == 0) {
                     intent = new Intent(Visualizador.this, MatematicasNota.class);
                }
                if (area == 1) {
                    intent = new Intent(Visualizador.this, AdministrativoNota.class);
                }
                if (area == 2) {
                    intent = new Intent(Visualizador.this, CocinaNota.class);
                }
                if (area == 3) {
                    intent = new Intent(Visualizador.this, InformaticaNota.class);
                }
                if (area == 4) {
                    intent = new Intent(Visualizador.this, LenguaNota.class);
                }
                if (area == 5) {
                    intent = new Intent(Visualizador.this, LogisticaNota.class);
                }
                if (area == 6) {
                    intent = new Intent(Visualizador.this, MusicaNota.class);
                }
                if (area == 7) {
                    intent = new Intent(Visualizador.this, SaludNota.class);
                }
                startActivity(intent);
            }
        });

        pdfView = findViewById(R.id.pdf_view);
        pdfView.fromAsset(name).load();
    }
}