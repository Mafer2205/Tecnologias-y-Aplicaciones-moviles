package com.example.proyectotyam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Correo extends AppCompatActivity {

    EditText  edtas, edtcont;
    Button bf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);

        edtas = (EditText) findViewById(R.id.edtAsunto);
        edtcont = (EditText) findViewById(R.id.edtText);
        bf = (Button) findViewById(R.id.btnf);

        bf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void enviar (View view){
        String edtto = "mafergarcia2205@gmail.com , grissanlaz@gmail.com ";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_EMAIL,new String[]{edtto});
        intent.putExtra(intent.EXTRA_SUBJECT,edtas.getText().toString());
        intent.putExtra(intent.EXTRA_TEXT, edtcont.getText().toString());
        startActivity(intent);

        edtas.setText("");
        edtcont.setText("");
    }
}