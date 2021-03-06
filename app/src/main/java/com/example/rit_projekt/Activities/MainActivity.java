package com.example.rit_projekt.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rit_projekt.Models.DatabaseHelper;
import com.example.rit_projekt.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button vstaviBaza;
    Button izracunaj,spremeniBazo;
    EditText teza;
    TextView energijskaV,mascobe,nasicene,hidrati,sladkor,beljakovine;
    DatabaseHelper myDB = new DatabaseHelper(this);
    ArrayList<String> listVrednosti = new ArrayList<>();

    private static DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vstaviBaza = findViewById(R.id.btnVstaviNewActivity);
        izracunaj = findViewById(R.id.btnIzracun);
        izracunaj.setOnClickListener(this);
        vstaviBaza.setOnClickListener(this);

        spremeniBazo=findViewById(R.id.btnSpremeniBazo);
        spremeniBazo.setOnClickListener(this);

        teza = findViewById(R.id.teza);

        energijskaV = findViewById(R.id.textView3);
        mascobe = findViewById(R.id.textView4);
        nasicene = findViewById(R.id.textView5);
        hidrati = findViewById(R.id.textView7);
        sladkor = findViewById(R.id.textView6);
        beljakovine = findViewById(R.id.textView8);

        ArrayList<String> listImen = myDB.getData();

        Spinner sp = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listImen);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selected = parent.getItemAtPosition(position).toString();
               listVrednosti = myDB.getInfo(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVstaviNewActivity:
                startActivity(new Intent(this, insertBazaActivity.class));
                break;

            case R.id.btnIzracun:

                int stevec = 0;


                    if(teza.getText().toString().isEmpty())
                        stevec = 6;

                try {
                    while (stevec <= 5) {
                        double vrednost = Double.parseDouble(listVrednosti.get(stevec));
                        vrednost /= 100;
                        double tezaVrednosti = Double.parseDouble(teza.getText().toString());

                        if (stevec == 0) {
                            String ene;
                            Double a;
                            stevec++;

                            Double st = vrednost * tezaVrednosti;

                            if (energijskaV.getText().length() > 0) {
                                ene = energijskaV.getText().toString();

                                String nova = ene.replaceAll(",",".");
                                a = Double.parseDouble(nova);

                            } else {
                                a = 1.00;
                            }
                            Double tmp = a + st;

                            String eneV = df.format(tmp);
                            energijskaV.setText(eneV);

                        } else if (stevec == 1) {
                            stevec++;
                            String ene;
                            Double a;

                            Double st = vrednost * tezaVrednosti;
                            if (mascobe.getText().length() > 0) {
                                ene = mascobe.getText().toString();
                                String nova = ene.replaceAll(",",".");
                                a = Double.parseDouble(nova);
                            } else {
                                a = 1.00;
                            }
                            Double tmp = a + st;

                            String eneV = df.format(tmp);
                            mascobe.setText(eneV);

                        } else if (stevec == 2) {
                            stevec++;
                            String ene;
                            Double a;

                            Double st = vrednost * tezaVrednosti;
                            if (nasicene.getText().length() > 0) {
                                ene = nasicene.getText().toString();
                                String nova = ene.replaceAll(",",".");
                                a = Double.parseDouble(nova);
                            } else {
                                a = 1.00;
                            }
                            Double tmp = a + st;

                            String eneV = df.format(tmp);
                            nasicene.setText(eneV);

                        } else if (stevec == 3) {
                            stevec++;
                            String ene;
                            Double a;

                            Double st = vrednost * tezaVrednosti;
                            if (hidrati.getText().length() > 0) {
                                ene = hidrati.getText().toString();
                                String nova = ene.replaceAll(",",".");
                                a = Double.parseDouble(nova);
                            } else {
                                a = 1.00;
                            }
                            Double tmp = a + st;

                            String eneV = df.format(tmp);
                            hidrati.setText(eneV);

                        } else if (stevec == 4) {
                            stevec++;
                            String ene;
                            Double a;

                            Double st = vrednost * tezaVrednosti;
                            if (sladkor.getText().length() > 0) {
                                ene = sladkor.getText().toString();
                                String nova = ene.replaceAll(",",".");
                                a = Double.parseDouble(nova);
                            } else {
                                a = 1.00;
                            }
                            Double tmp = a + st;

                            String eneV = df.format(tmp);
                            sladkor.setText(eneV);

                        } else if (stevec == 5) {
                            String ene;
                            Double a;
                            stevec++;

                            Double st = vrednost * tezaVrednosti;
                            if (beljakovine.getText().length() > 0) {
                                ene = beljakovine.getText().toString();
                                String nova = ene.replaceAll(",",".");
                                a = Double.parseDouble(nova);
                            } else {
                                a = 1.00;
                            }
                            Double tmp = a + st;

                            String eneV = df.format(tmp);
                            beljakovine.setText(eneV);
                        }

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.btnSpremeniBazo:
                startActivity(new Intent(this, SpremeniArtikelActivity.class));
                break;

        }

    }
}
