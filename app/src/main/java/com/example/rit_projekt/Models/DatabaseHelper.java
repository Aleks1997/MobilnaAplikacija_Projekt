package com.example.rit_projekt.Models;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import retrofit2.http.FormUrlEncoded;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projekt.db";
    private static final String TABLE_NAME = "artikel";
    public static final String ARTIKELID = "ID";
    private static final String ARTIKELIME = "imeArtikla";
    private static final String ENERGIJSKEV = "energijskaV";
    private static final String MASCOBE = "mascobe";
    private static final String NASICENEMASCOBE = "nasiceneM";
    private static final String OGLJIKOVIHIDRATI = "ogljikoviH";
    private static final String SLADKORJI = "sladkor";
    private static final String BELJAKOVINE = "beljakovine";
    private static final int schemaVersion = 5;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, schemaVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,imeArtikla TEXT,energijskaV TEXT,mascobe TEXT,nasiceneM TEXT,ogljikoviH TEXT," +
                "sladkor TEXT,beljakovine TEXT)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String ime,String enerV,String masc,String nasicM,String ogljH,String slad,String belj){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTIKELIME,ime);
        contentValues.put(ENERGIJSKEV,enerV);
        contentValues.put(MASCOBE,masc);
        contentValues.put(NASICENEMASCOBE,nasicM);
        contentValues.put(OGLJIKOVIHIDRATI,ogljH);
        contentValues.put(SLADKORJI,slad);
        contentValues.put(BELJAKOVINE,belj);
        long result = db.insert(TABLE_NAME,null,contentValues);


        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getData(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
            if(cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    String ime = cursor.getString(cursor.getColumnIndex(ARTIKELIME));
                    list.add(ime);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public ArrayList<String> getInfo(String ime){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try{
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ARTIKELIME +"=" +'"'+ime+'"',null);
            if(c.getCount() > 0){
                c.moveToNext();

                list.add(c.getString(2));
                list.add(c.getString(3));
                list.add(c.getString(4));
                list.add(c.getString(5));
                list.add(c.getString(6));
                list.add(c.getString(7));


                for(int i = 0; i < list.size();i++){
                    Log.d("a",list.get(i));
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    public boolean updateData(String ime,String enerV,String masc,String nasicM,String ogljH,String slad,String belj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ARTIKELIME,ime);
        contentValues.put(ENERGIJSKEV,enerV);
        contentValues.put(MASCOBE,masc);
        contentValues.put(NASICENEMASCOBE,nasicM);
        contentValues.put(OGLJIKOVIHIDRATI,ogljH);
        contentValues.put(SLADKORJI,slad);
        contentValues.put(BELJAKOVINE,belj);
        //Log.d("e","imeArtikla="+ime);
        db.update(TABLE_NAME,contentValues,"imeArtikla=?",new String[] {ime});
        return true;

    }

    public boolean deleteData(String ime){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"imeArtikla=?",new String[] {ime});
        return true;
    }


}
