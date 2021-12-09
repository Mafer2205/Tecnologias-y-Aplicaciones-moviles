package com.example.proyectotyam;

import static java.lang.Integer.parseInt;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Modificacion extends AppCompatActivity {

    EditText Enom, Eapp, Eedad, Eubi, Esex, Econt,Etel;
    TextView Ecor;
    ImageView IM;
    Button Bg, Bf;
    String Nombre = "";
    String Apellido = "";
    String Edad = "";
    String Telefono = "";
    String Ubicacion = "";
    String Contrasena = "";
    String sexo = "";
    String Correo = "";
    int cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificacion);

        Bg =(Button) findViewById(R.id.btnGuardar);
        Bf =(Button) findViewById(R.id.btnFin);
        IM = (ImageView) findViewById(R.id.perfil);
        Enom = (EditText) findViewById(R.id.edtNombre);
        Eapp = (EditText) findViewById(R.id.edtApellido);
        Eedad = (EditText) findViewById(R.id.edtEdad);
        Eubi = (EditText) findViewById(R.id.edtUbicacion);
        Esex = (EditText) findViewById(R.id.edtSexo);
        Ecor = (TextView) findViewById(R.id.edtCorreo);
        Econt = (EditText) findViewById(R.id.edtContrasena);
        Etel = (EditText) findViewById(R.id.edtTelefono);

        Intent intent = getIntent();
        if(intent == null) return;

        Correo = intent.getStringExtra(UsuarioVista.Cor);
        busca(Correo);

        Enom.setText(Nombre);
        Eapp.setText(Apellido);
        Eedad.setText(Edad);
        Eubi.setText(Ubicacion);
        Esex.setText(sexo);
        Ecor.setText(Correo);
        Econt.setText(Contrasena);
        Etel.setText(Telefono);

        Bf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void busca (String correo){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        Cursor cur = bd.rawQuery("select * from Usuarios", null);
        if (cur != null && cur.moveToFirst()) {
            do {
                if (cur.getString(5).equals(correo)) {
                    cod = parseInt(cur.getString(0));
                    Nombre = cur.getString(1);
                    Apellido = cur.getString(2);
                    Edad = cur.getString(3);
                    Telefono = cur.getString(4);
                    Contrasena = cur.getString(6);
                    Ubicacion = cur.getString(7);
                    sexo = cur.getString(8);
                }
            }
            while (cur.moveToNext());
        }

        if(!sexo.equals("")){
            if(sexo.equals("M")){
                IM.setImageResource(R.drawable.h);
            }else{
                IM.setImageResource(R.drawable.m);
            }
        }
    }

    public void modificarus (View view){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        String nomb =Enom.getText().toString();
        String ap =Eapp.getText().toString();
        String ed =Eedad.getText().toString();
        String tel =Etel.getText().toString();
        String cont =Econt.getText().toString();
        String ubi =Eubi.getText().toString();
        String sex =Esex.getText().toString();

        if(!tel.isEmpty() && !nomb.isEmpty() && !ap.isEmpty() && !ed.isEmpty() && !ubi.isEmpty() && !cont.isEmpty() && !sex.isEmpty()) {
            if (parseInt(ed) >= 16 && (sex.equals("M") || sex.equals("F"))) {
                ContentValues registro = new ContentValues();
                registro.put("id_usuario",cod);
                registro.put("nombre", nomb);
                registro.put("apellidos", ap);
                registro.put("edad", parseInt(ed));
                registro.put("telefono", tel);
                registro.put("correo",Correo);
                registro.put("contrasena", cont);
                registro.put("ubicacion", ubi);
                registro.put("sexo", sex);

                int y = bd.update("Usuarios", registro,"id_usuario=" + cod, null);
                bd.close();
                if(y ==1) {
                    Toast.makeText(this, "Actualizacion de datos exitosa", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "No se pudo actualizar la informacion", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Debes ser mayor de 16 años e ingresar M o F en sexo", Toast.LENGTH_LONG).show();

            }
        }   else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
