package com.example.proyectotyam;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Baja extends AppCompatActivity {

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
    Button can;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baja);

        cor = (EditText) findViewById(R.id.edtCor);
        contr = (EditText) findViewById(R.id.edtCont);
        can = (Button) findViewById(R.id.btnCan);

        can.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public int busca (String correo, String contra){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        int a = 0;
        Cursor cur = bd.rawQuery("select * from Usuarios", null);
        if (cur != null && cur.moveToFirst()) {
            do {
                if (cur.getString(5).equals(correo) && cur.getString(6).equals(contra)) {
                    cod = parseInt(cur.getString(0));
                    Nombre = cur.getString(1);
                    Apellido = cur.getString(2);
                    Edad = cur.getString(3);
                    Telefono = cur.getString(4);
                    Correo = correo;
                    Contrasena = contra;
                    Ubicacion = cur.getString(7);
                    sexo = cur.getString(8);
                    a = 1;
                }
            }
            while (cur.moveToNext());
        }
        return a;
    }

    public void baja (View view){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        String cont =contr.getText().toString();
        String correo = cor.getText().toString();
        int x = busca(correo,cont);

        if (!correo.isEmpty() && !cont.isEmpty()) {
            if (x == 1) {
                int y = bd.delete("Usuarios", "id_usuario=" + cod, null);
                bd.close();

                if (y == 1) {
                    Toast.makeText(this, "Eliminacion exitosa", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent=new Intent(Baja.this,MainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "No se pudo eliminar la cuenta", Toast.LENGTH_LONG).show();
                }

            }else {
                Toast.makeText(this, "El correo o contraseña no fueron encontrados", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
