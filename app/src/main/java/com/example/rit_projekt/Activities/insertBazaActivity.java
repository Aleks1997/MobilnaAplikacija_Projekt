package com.example.rit_projekt.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rit_projekt.Models.DatabaseHelper;
import com.example.rit_projekt.R;

import java.io.File;
import java.util.ArrayList;


public class insertBazaActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB;
    EditText ime;
    EditText energijskaV;
    EditText mascobe;
    EditText nasiceneM;
    EditText hidrati;
    EditText sladkor;
    EditText beljakovine;
    Button vstavi, nazaj;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView userlist;
    String FILE_NAME = "baza.json";
    File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_baza);

        ime = findViewById(R.id.imeArtikla);
        energijskaV = findViewById(R.id.energjskaV);
        mascobe = findViewById(R.id.mascobe);
        nasiceneM = findViewById(R.id.nasicene);
        hidrati = findViewById(R.id.hidrati);
        sladkor = findViewById(R.id.sladkor);
        beljakovine = findViewById(R.id.beljakovine);
        vstavi = findViewById(R.id.btnVstavi);
        nazaj = findViewById(R.id.btnNazaj);
        nazaj.setOnClickListener(this);

        myDB = new DatabaseHelper(this);

        AddData();
        file=new File(getFilesDir(),FILE_NAME);
    }


    public void AddData() {
        vstavi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(ime.getText().toString(), energijskaV.getText().toString()
                                , mascobe.getText().toString(), nasiceneM.getText().toString(), hidrati.getText().toString(),
                                sladkor.getText().toString(), beljakovine.getText().toString());

                        finish();

                        if (isInserted) {
                            Toast.makeText(insertBazaActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(insertBazaActivity.this, "Data NOT inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNazaj:
                finish();
                break;
        }
    }
}