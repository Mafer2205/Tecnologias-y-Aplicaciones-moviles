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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContraRecupera extends AppCompatActivity {

    EditText cor, contr;
    String Nombre = "";
    String Apellido = "";
    String Edad = "";
    String Telefono = "";
    String Ubicacion = "";
    String Contrasena = "";
    String sexo = "";
    String Correo = "";
    int cod;
    Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_contra);

        cor = (EditText) findViewById(R.id.edtCeloCorr);
        contr = (EditText) findViewById(R.id.edtNuevaContra);
        c = (Button) findViewById(R.id.btnCer);

        c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public int busca (String correo){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        int a = 0;
        Cursor cur = bd.rawQuery("select * from Usuarios", null);
        if (cur != null && cur.moveToFirst()) {
            do {
                if (cur.getString(5).equals(correo)) {
                    cod = parseInt(cur.getString(0));
                    Nombre = cur.getString(1);
                    Apellido = cur.getString(2);
                    Edad = cur.getString(3);
                    Telefono = cur.getString(4);
                    Correo = correo;
                    Contrasena = cur.getString(6);
                    Ubicacion = cur.getString(7);
                    sexo = cur.getString(8);
                    a = 1;
                }
            }
            while (cur.moveToNext());
        }
        return a;
    }

    public void actualizaC (View view){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        String cont =contr.getText().toString();
        String correo = cor.getText().toString();
        int x = busca(correo);

        if (!correo.isEmpty() && !cont.isEmpty()) {
            if (x == 1) {
                ContentValues registro = new ContentValues();
                registro.put("id_usuario", cod);
                registro.put("nombre", Nombre);
                registro.put("apellidos", Apellido);
                registro.put("edad", parseInt(Edad));
                registro.put("telefono", Telefono);
                registro.put("correo", correo);
                registro.put("contrasena", cont);
                registro.put("ubicacion", Ubicacion);
                registro.put("sexo", sexo);

                int y = bd.update("Usuarios", registro, "id_usuario=" + cod, null);
                bd.close();
                if (y == 1) {
                    Toast.makeText(this, "Actualizacion de contraseña exitosa", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "No se pudo actualizar la contraseña", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(this, "El correo no fue encontrado", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
