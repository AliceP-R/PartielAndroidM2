package com.example.alice.partielm2;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Accueil extends AppCompatActivity {

    private static AdapterListeAlarme adapteurPerso;

    private class HandlerLongclick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(getApplicationContext(), "Clic long ligne "+i, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), GestionAlarme.class);
            intent.putExtra("temps", adapteurPerso.getGCFromID(i).getTimeInMillis());
            intent.putExtra("id", i);
            startActivity(intent);
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        ListView listView = (ListView) findViewById(R.id.lvAlarme);
        adapteurPerso = new AdapterListeAlarme(this);
        listView.setOnItemLongClickListener(new HandlerLongclick());
        listView.setAdapter(adapteurPerso);
        adapteurPerso.notifyDataSetChanged();

        Button bEffacer = (Button) findViewById(R.id.bEffacer);
        bEffacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEffacer();
            }
        });

        ImageButton bAjouter = (ImageButton) findViewById(R.id.ibAjouter);
        bAjouter.setOnClickListener(new View.OnClickListener() {

            int heure;
            int minute;
            String description;

            GregorianCalendar gc;

            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(Accueil.this);
                alertDialog.setTitle("Description");
                alertDialog.setMessage("Entrez la description de l'alarme");

                final EditText input = new EditText(Accueil.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setNeutralButton("Valider",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                description = input.getText().toString();
                                adapteurPerso.ajouterAlarme(gc, description);
                            }
                        });

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Accueil.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        heure = selectedHour;
                        minute = selectedMinute;

                        gc = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, heure, minute);
                        alertDialog.show();
                    }
                }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
                mTimePicker.setTitle("Heure de l'alarme ?");
                mTimePicker.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapteurPerso.notifyDataSetChanged();
    }

    public void onClickEffacer()
    {
        adapteurPerso.effacerTout();
    }

    public static AdapterListeAlarme getAdapteurPerso() {
        return adapteurPerso;
    }
}
