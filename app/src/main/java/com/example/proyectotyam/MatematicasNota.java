package com.example.proyectotyam;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MatematicasNota extends Activity {

    private EditText edtMats;
    private Button BtMat;
    ImageView picture;
    public static final int REQUEST_CODE_CAMERA = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_matematicas);

        edtMats=(EditText)findViewById(R.id.ETMat);
        picture = findViewById (R.id.ImgMat);
        picture.setImageResource  (R.drawable.fondo);

        SharedPreferences p=getSharedPreferences("matematicas", Context.MODE_PRIVATE);
        edtMats.setText(p.getString("mat",""));


        Button btnfotoM = findViewById(R.id.BTfotoMat);
        btnfotoM.setOnClickListener(view ->{
            int f = checkSelfPermission(Manifest.permission.CAMERA);
            if(f != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA);
                return;
            }
            doPhoto();
        });

        BtMat=(Button) findViewById(R.id.BTMat);
        BtMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("matematicas",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putString("mat", edtMats.getText().toString());
                editor.commit();
            }
        });

    }


    private void doPhoto (){
        Intent takepicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takepicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takepicture, REQUEST_CODE_CAMERA);
        }
    }

    Uri saveImg(Bitmap bitmap, String filename) {
        Uri result = null;
        ContentResolver resolver = getContentResolver ();

        ContentValues v = new ContentValues ();
        v.put (MediaStore.MediaColumns.DISPLAY_NAME, filename);
        v.put (MediaStore.Images.Media.TITLE, filename);
        v.put (MediaStore.Images.Media.DESCRIPTION, "Imagen de notas de matematicas");
        v.put (MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        v.put (MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis ());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            v.put (MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis ());
            v.put (MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);
            v.put (MediaStore.MediaColumns.IS_PENDING, true);
        } else {
            String pd = String.format ("%s/%s", Environment.getExternalStorageDirectory (), Environment.DIRECTORY_DCIM);
            String fullPath = String.format ("%s/%s", pd, filename);

            v.put (MediaStore.MediaColumns.DATA, fullPath);
            v.put (MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis ());
        }

        Uri targetUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Uri imageUri =  resolver.insert (targetUri, v);

        try {
            OutputStream ff = resolver.openOutputStream (imageUri);
            bitmap.compress (Bitmap.CompressFormat.JPEG,100, ff);
            ff.flush ();
            ff.close ();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                v = new ContentValues ();
                v.put (MediaStore.Images.ImageColumns.IS_PENDING, false);
                resolver.update (imageUri, v, null, null);
            }

            result = imageUri;
        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        return result;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK){
            Bundle e = data.getExtras();
            Bitmap bitmap = (Bitmap) e.get("data");
            picture.setImageBitmap(bitmap);

            SimpleDateFormat dateF = new SimpleDateFormat ("yyy-MM-dd-HH-mm-ss", Locale.US);
            String now = dateF.format (Calendar.getInstance().getTime ());
            String filename = "IMG_Mat" + now +  ".jpg";
            Uri imageUri = saveImg(bitmap, filename);

            if (imageUri != null) {
                String fullPath = String.format ("%s/%s/%s", Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DCIM, filename);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults [0] == PackageManager.PERMISSION_GRANTED) {
                doPhoto ();
            }
        }

    }

}
