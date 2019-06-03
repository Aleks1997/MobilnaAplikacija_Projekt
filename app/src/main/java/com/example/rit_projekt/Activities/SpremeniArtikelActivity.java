package com.example.rit_projekt.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rit_projekt.Models.DatabaseHelper;
import com.example.rit_projekt.R;

import java.util.ArrayList;

public class SpremeniArtikelActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB = new DatabaseHelper(this);
    ArrayList<String> listVrednosti = new ArrayList<>();

     Button btnSpremeni,btnNazaj, btnDel;
     EditText evEnerg, evMasc,evHidr,evSlad,evBelj, evNasic;
     String g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spremeni_artikel);

        btnSpremeni = findViewById(R.id.btnSpremeniti);
        btnNazaj = findViewById(R.id.btnBack);
        btnDel = findViewById(R.id.btnDelete);

        evEnerg = findViewById(R.id.eneV);
        evMasc = findViewById(R.id.masc);
        evHidr = findViewById(R.id.hidr);
        evSlad = findViewById(R.id.slad);
        evBelj = findViewById(R.id.belj);
        evNasic = findViewById(R.id.nasic);

        btnNazaj.setOnClickListener(this);
        btnSpremeni.setOnClickListener(this);
        btnDel.setOnClickListener(this);

        ArrayList<String> listImen = myDB.getData();

        Spinner sp = findViewById(R.id.spinner2);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listImen);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                g = selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnSpremeniti:
                myDB.updateData(g,evEnerg.getText().toString(),evMasc.getText().toString(),evHidr.getText().toString(),evSlad.getText().toString(),
                        evSlad.getText().toString(),evBelj.getText().toString());

                break;

            case R.id.btnDelete:
                myDB.deleteData(g);
                break;
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
