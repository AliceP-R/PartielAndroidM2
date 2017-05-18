package com.example.alice.partielm2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.app.AlarmManager.RTC;

public class GestionAlarme extends AppCompatActivity {

    long tempsMilli;
    int ligne;
    PendingIntent pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        Intent reception = getIntent();
        tempsMilli = reception.getLongExtra("temps", -1);
        ligne = reception.getIntExtra("id", -1);

        Button bArmer = (Button) findViewById(R.id.bArmer);
        bArmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickArmer();
            }
        });

        Button bDesarmer = (Button) findViewById(R.id.bDesarmer);
        bDesarmer.setEnabled(false);
        bDesarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDesarmer();
            }
        });

        Button bSupprimer = (Button) findViewById(R.id.bSupprimer);
        bSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEffacer();
            }
        });
    }

    void onClickArmer()
    {
        Toast.makeText(getApplicationContext(), "Armer !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(GestionAlarme.this, GestionAlarme.class);
        pending = PendingIntent.getActivity(GestionAlarme.this, 0, intent, 0);
        AlarmManager manager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        manager.set(RTC, tempsMilli, pending);

        Button bDesarmer = (Button) findViewById(R.id.bDesarmer);
        bDesarmer.setEnabled(true);
        Button bArmer = (Button) findViewById(R.id.bArmer);
        bArmer.setEnabled(false);

    }

    void onClickDesarmer()
    {
        pending.cancel();
        Toast.makeText(getApplicationContext(), "Désarmer !", Toast.LENGTH_SHORT).show();

        Button bArmer = (Button) findViewById(R.id.bArmer);
        bArmer.setEnabled(true);
        Button bDesarmer = (Button) findViewById(R.id.bDesarmer);
        bDesarmer.setEnabled(false);
    }

    void onClickEffacer()
    {
        Accueil.getAdapteurPerso().effacerUneAlarme(ligne);
        Intent intent = new Intent(getApplicationContext(), Accueil.class);
        startActivity(intent);
    }

}
