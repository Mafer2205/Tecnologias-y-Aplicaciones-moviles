package com.example.proyectotyam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BuscaGoogle extends AppCompatActivity {

    Button btcerr, btnots;
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_google);

        wv1 = (WebView) findViewById(R.id.wb1);
        btcerr = (Button) findViewById(R.id.btnCerrar);
        btnots = (Button) findViewById(R.id.btnNots);

        wv1.setWebViewClient(new WebViewClient());
        wv1.loadUrl("https://www.google.com");

        btcerr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnots.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BuscaGoogle.this,NotasSeccion.class);
                startActivity(intent);
            }
        });
    }


}
