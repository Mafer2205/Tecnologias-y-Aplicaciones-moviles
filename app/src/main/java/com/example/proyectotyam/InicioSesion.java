package com.example.proyectotyam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class InicioSesion extends AppCompatActivity {
    private EditText edCorreo, edContrasena;
    private Button is, olvido, huella;
    public static final String Correo = "correo";
    public static final String Contrasena = "contrasena";
    int encontrarCorreo = 0;
    String verificarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        edCorreo = (EditText) findViewById(R.id.edtCeloCor);
        edContrasena = (EditText) findViewById(R.id.edtContra);
        is =(Button) findViewById(R.id.btnInicioSes);
        huella =(Button) findViewById(R.id.btnHuella);
        olvido =(Button) findViewById(R.id.TVOlvido);

        olvido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InicioSesion.this,ContraRecupera.class);
                startActivity(intent);
            }
        });

        huella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = verificarCorreo();
                if (encontrarCorreo == 1) {
                    BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                            .setTitle("VERIFICAR HUELLA")
                            .setDescription("Se requiere autenticación para continuar")
                            .setNegativeButtonText("Cancelar")
                            .build();
                    getPrompt().authenticate(promptInfo);
                }
            }
        });
    }

    private BiometricPrompt getPrompt () {
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("AUTENTICACIÓN EXITOSA");

                Intent intent = new Intent(getBaseContext (), UsuarioVista.class);
                intent.putExtra(Correo, verificarCorreo());
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("AUTENTICACIÓN FALLIDA");
            }
        };
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);
        return biometricPrompt;
    }

    private void notifyUser (String message) {Toast.makeText(this, message, Toast.LENGTH_LONG).show(); }

    public String verificarCorreo () {
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        verificarCorreo = edCorreo.getText().toString();

        if (!verificarCorreo.isEmpty()) {
            Cursor cur = bd.rawQuery("select * from Usuarios", null);
            if (cur != null && cur.moveToFirst()) {
                do {
                    if (cur.getString(5).equals(verificarCorreo)) {encontrarCorreo = 1;}
                }
                while (cur.moveToNext());
            }
            if (encontrarCorreo == 0) Toast.makeText(this, "CORREO NO REGISTRADO", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "INGRESAR UN CORREO", Toast.LENGTH_LONG).show();

        if (encontrarCorreo==1) {
            return verificarCorreo;
        }
        return null;
    }

    public void inicioSesion (View view){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        String correo = edCorreo.getText().toString();
        String contrasena = edContrasena.getText().toString();
        int auxiliar = 0;

        if (!correo.isEmpty() && !contrasena.isEmpty()) {
            Cursor cur = bd.rawQuery("select * from Usuarios", null);
            if (cur != null && cur.moveToFirst()) {
                do {
                    if (cur.getString(5).equals(correo) && cur.getString(6).equals(contrasena)) {
                        auxiliar = 1;
                        Toast.makeText(this, "USUARIO ENCONTRADO", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext (), UsuarioVista.class);

                        intent.putExtra(Correo, correo);

                        startActivity(intent);
                    }
                }
                while (cur.moveToNext());
            }
            if (auxiliar == 0) Toast.makeText(this, "USUARIO NO ENCONTRADO", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
    }
}