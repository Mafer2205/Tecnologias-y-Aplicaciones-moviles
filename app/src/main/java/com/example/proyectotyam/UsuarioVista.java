package com.example.proyectotyam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UsuarioVista extends AppCompatActivity {

    private Button  btnsalir, btnedit, btnarea,  btnnotas, btnayuda, btnbaja, bcob, bconoce;
    private TextView tvusu;
    private ImageView IMs;
    public static final String Cor = "correo";
    public static final String valor= "valor";

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vista_usuario);

            btnsalir =(Button) findViewById(R.id.btnSalir);//listo
            btnedit =(Button) findViewById(R.id.btnEdit); //listo
            btnarea =(Button) findViewById(R.id.btnAreas); //listo
            btnnotas =(Button) findViewById(R.id.btnVerNotas);//listo
            btnayuda =(Button) findViewById(R.id.btnAyud);
            bconoce =(Button) findViewById(R.id.btnconocenos);
            btnbaja =(Button) findViewById(R.id.btnBaja);//listo
            bcob =(Button) findViewById(R.id.btnCob);//listo
            tvusu = (TextView) findViewById(R.id.nombreUsuario);
            IMs = (ImageView) findViewById(R.id.perfil);

            Intent intent = getIntent();
            if(intent == null) return;

            String correo = intent.getStringExtra(InicioSesion.Correo);

            String x = busca(correo);
            tvusu.setText(x);

            bconoce.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getBaseContext (),VisualizadorAux.class);
                    intent.putExtra(valor, "1");
                    startActivity(intent);
                }
            });

            btnayuda.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getBaseContext (),VisualizadorAux.class);
                    intent.putExtra(valor, "2");
                    startActivity(intent);
                }
            });

            bcob.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UsuarioVista.this,Correo.class);
                    startActivity(intent);
                }
            });

            btnbaja.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UsuarioVista.this,Baja.class);
                    startActivity(intent);
                }
            });


            btnarea.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UsuarioVista.this,AreasActivity.class);
                    startActivity(intent);
                }
            });

            btnedit.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getBaseContext (),Modificacion.class);
                    intent.putExtra(Cor, correo);
                    startActivity(intent);
                }
            });

            btnsalir.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            btnnotas.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(UsuarioVista.this,NotasSeccion.class);
                    startActivity(intent);
                }
            });

        }

    public String busca (String correo){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        String n = "";
        String s = "";

        Cursor cur = bd.rawQuery("select * from Usuarios", null);
        if (cur != null && cur.moveToFirst()) {
            do {
                if (cur.getString(5).equals(correo)) {
                    n = cur.getString(1) + " " + cur.getString(2);
                    s = cur.getString(8);
                }
            }
            while (cur.moveToNext());
        }

        if(!s.equals("")){
            if(s.equals("M")){
                IMs.setImageResource(R.drawable.h);
            }else{
                IMs.setImageResource(R.drawable.m);
            }
        }
        return n;
    }
}
