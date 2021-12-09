package com.example.proyectotyam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TuttorDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TuttorVirtual";

    private static String DROP_USERS_TABLE = "DROP TABLE IF EXISTS Usuarios";
    private static String DROP_AREAS_TABLE = "DROP TABLE IF EXISTS Areas";
    private static String DROP_FILES_TABLE = "DROP TABLE IF EXISTS Archivos";

    private static String CREATE_USERS_TABLE =
            "CREATE TABLE Usuarios (" +
                    "id_usuario" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "nombre" + " TEXT NOT NULL, " +
                    "apellidos" + " TEXT NOT NULL," +
                    "edad" + " INTEGER NOT NULL," +
                    "telefono" + " TEXT NOT NULL," +
                    "correo" + " TEXT NOT NULL," +
                    "contrasena" + " TEXT NOT NULL," +
                    "ubicacion" + " TEXT NOT NULL," +
                    "sexo" + " TEXT NOT NULL" +
                    ")";

    private static String CREATE_AREAS_TABLE =
            "CREATE TABLE Areas (" +
                    "id_area" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "nomb_area" + " TEXT NOT NULL" +
                    ")";

    private static String CREATE_FILES_TABLE =
            "CREATE TABLE Archivos (" +
                    "id_archivo" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "nomb_arch" + " TEXT NOT NULL, " +
                    "r_area" + " INTEGER NOT NULL," +
                    "CONSTRAINT fk_area FOREIGN KEY (r_area) REFERENCES Areas(id_area)" +
                    ")";


    public TuttorDatabase (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL (CREATE_USERS_TABLE);
        sqLiteDatabase.execSQL (CREATE_AREAS_TABLE);
        sqLiteDatabase.execSQL (CREATE_FILES_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL (DROP_USERS_TABLE);
        sqLiteDatabase.execSQL (DROP_AREAS_TABLE);
        sqLiteDatabase.execSQL (DROP_FILES_TABLE);
        onCreate (sqLiteDatabase);
    }

    public void addArea (Areas areas) {
        SQLiteDatabase db = this.getWritableDatabase ();

        ContentValues values =  new ContentValues ();
        values.put ("nomb_area", areas.getNomb_area ());

        db.insert ("Areas", null, values);
        db.close ();
    }

    public void addArchivo (Archivos archivos) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put ("nomb_arch", archivos.getNomb_arch());
        values.put ("r_area", archivos.getR_area());

        db.insert ("Archivos", null, values);
        db.close ();
    }


    public int getContactsCount () {
        String countQuery = "SELECT * FROM Areas";

        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (countQuery, null);
        int count = cursor.getCount ();

        cursor.close ();
        db.close ();

        return count;
    }

    public int getArchCount () {
        String countQuery = "SELECT * FROM Archivos";

        SQLiteDatabase db = this.getReadableDatabase ();
        Cursor cursor = db.rawQuery (countQuery, null);
        int count = cursor.getCount ();

        cursor.close ();
        db.close ();

        return count;
    }


}