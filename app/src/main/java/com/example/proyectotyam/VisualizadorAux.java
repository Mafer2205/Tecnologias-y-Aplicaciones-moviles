package com.example.proyectotyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class VisualizadorAux extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador_aux);

        Intent intent = getIntent();
        if(intent == null) return;

        int v = Integer.parseInt(intent.getStringExtra(UsuarioVista.valor));
        String name = "";

        if (v == 1 ){
            name = "Conocenos.pdf";
        }
        else if (v == 2){
            name = "Manual de usuario.pdf";
        }

        pdfView = findViewById(R.id.pdf_view2);
        pdfView.fromAsset(name).load();
    }
}