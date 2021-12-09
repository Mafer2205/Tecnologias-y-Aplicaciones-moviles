package com.example.proyectotyam;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RegistroActivity extends AppCompatActivity {

    private EditText edcorre;
    private EditText edcel;
    private EditText ednomb;
    private EditText edapell;
    private EditText ededad;
    private EditText edcontra;
    private EditText edubic;
    private EditText edsex;
    private Button btnbuscar;
    LocationListener locationListener;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edcorre = (EditText) findViewById(R.id.edtCorreo);
        edcel = (EditText) findViewById(R.id.edtCel);
        ednomb = (EditText) findViewById(R.id.edtNomb);
        edapell = (EditText) findViewById(R.id.edtApp);
        ededad = (EditText) findViewById(R.id.edtEdad);
        edubic = (EditText) findViewById(R.id.edtUbicacion);
        edcontra = (EditText) findViewById(R.id.edtCont);
        edsex = (EditText) findViewById(R.id.edtSexo);

        btnbuscar= (Button) findViewById(R.id.btnBusUbi);
        btnbuscar.setOnClickListener(view ->{
            int permission = getBaseContext().checkSelfPermission (Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions (
                        new String [] { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 1001);
                return;
            }
            Location ();
        });
    }

    @SuppressLint("MissingPermission")
    private void Location () {
        locationListener = new LocationL ();
        locationManager = (LocationManager) getSystemService (LOCATION_SERVICE);
        locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER, 3000, 10, locationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1001) {
            if (grantResults [0] == PackageManager.PERMISSION_GRANTED) {
                Location ();
            }
        }
    }

    class LocationL implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            Toast.makeText (getBaseContext (), "Lat: " + location.getLatitude() + " Lon: " + location.getLongitude (), Toast.LENGTH_SHORT).show ();

            Geocoder geocoder = new Geocoder (getBaseContext(), Locale.getDefault ());
            List<Address> addresses;
            String ciudad = "";

            try {
                addresses = geocoder.getFromLocation (location.getLatitude(), location.getLongitude(), 1);
                if (addresses.size () > 0) {
                    ciudad = addresses.get (0).getLocality ();
                }
            } catch (IOException ex) {
                ex.printStackTrace ();
            }

            edubic.setText(ciudad);
        }

        public void onLocationChanged(@NonNull List<Location> locations) {
        }

        public void onFlushComplete(int requestCode) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {
        }
    }

    public void registro (View view){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        String correo = edcorre.getText().toString();
        String celular = edcel.getText().toString();
        String nombre = ednomb.getText().toString();
        String apellido  = edapell.getText().toString();
        String edad = ededad.getText().toString();
        String ubicacion = edubic.getText().toString();
        String contra  = edcontra.getText().toString();
        String sexo  = edsex.getText().toString();

        int x = busca(correo);

        if(!correo.isEmpty() && !celular.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !edad.isEmpty() && !ubicacion.isEmpty() && !contra.isEmpty() && !sexo.isEmpty()) {
            if (x == 0) {
                if (parseInt(edad) >= 16 && (sexo.equals("M") || sexo.equals("F"))) {
                    ContentValues regist = new ContentValues();
                    regist.put("nombre", nombre);
                    regist.put("apellidos", apellido);
                    regist.put("edad", parseInt(edad));
                    regist.put("telefono", celular);
                    regist.put("correo", correo);
                    regist.put("contrasena", contra);
                    regist.put("ubicacion", ubicacion);
                    regist.put("sexo", sexo);

                    bd.insert("Usuarios", null, regist);
                    bd.close();

                    edcorre.setText("");
                    edcel.setText("");
                    ednomb.setText("");
                    edapell.setText("");
                    ededad.setText("");
                    edubic.setText("");
                    edcontra.setText("");
                    edsex.setText("");

                    Toast.makeText(this, "Registro exitoso, ya puedes iniciar sesion", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Debes ser mayor de 16 años e ingresar M o F en sexo", Toast.LENGTH_LONG).show();

                }
            }  else {
                Toast.makeText(this, "El correo ingresado ya esta registrado", Toast.LENGTH_LONG).show();
            }
        }   else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public int busca (String correo){
        TuttorDatabase TDB = new TuttorDatabase(this);
        SQLiteDatabase bd = TDB.getWritableDatabase();

        int auxiliar = 0;

        Cursor cur = bd.rawQuery("select * from Usuarios", null);
        if (cur != null && cur.moveToFirst()) {
            do {
                if (cur.getString(5).equals(correo)) {
                    auxiliar = 1;
                }
            }
            while (cur.moveToNext());
        }
        return auxiliar;
    }
}