package com.example.rit_projekt;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "projekt.db";
    public static final String TABLE_NAME = "artikel";
    public static final String ARTIKELID = "ID";
    public static final String ARTIKELIME = "imeArtikla";
    public static final String ENERGIJSKEV = "energijskeV";
    public static final String MASCOBE = "mascobe";
    public static final String NASICENEMASCOBE = "nasiceneM";
    public static final String OGLJIKOVIHIDRATI = "ogljikoviH";
    public static final String SLADKORJI = "sladkorji";
    public static final String BELJAKOVINE = "beljakovine";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,imeArtikla TEXT,energijskeV TEXT,mascobe TEXT,ogljikoviH TEST," +
                "sladkorji TEXT,beljakovine TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
