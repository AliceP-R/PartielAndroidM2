package com.example.alice.partielm2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by alice on 15/05/2017.
 */

// Adapteur personnalisé pour notre liste
public class AdapterListeAlarme extends BaseAdapter {

    private ArrayList<Alarme> alAlarme;
    private Context context;


    AdapterListeAlarme(Context context) {
        super();
        this.context = context;
        alAlarme = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return alAlarme.size();
    }

    @Override
    public Object getItem(int i) {
        return alAlarme.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.ligne_liste, parent, false);

        TextView tvHeure = (TextView) rowView.findViewById(R.id.tvHeure);
        TextView tvMinute = (TextView) rowView.findViewById(R.id.tvMinute);
        TextView tvDescri = (TextView) rowView.findViewById(R.id.tvDescri);

        GregorianCalendar gc = alAlarme.get(position).getHeure();
        int heure = gc.get(Calendar.HOUR_OF_DAY);
        int minute = gc.get(Calendar.MINUTE);


        tvHeure.setText(String.valueOf(heure));
        tvMinute.setText(String.valueOf(minute));
        tvDescri.setText(alAlarme.get(position).getDescription());


        return rowView;
    }

    void ajouterAlarme(GregorianCalendar gc, String description)
    {
        alAlarme.add(new Alarme(gc, description));
        this.notifyDataSetChanged();
    }

    void effacerTout()
    {
        alAlarme.clear();
        this.notifyDataSetChanged();
    }

    GregorianCalendar getGCFromID(int position)
    {
        return alAlarme.get(position).getHeure();
    }

    void effacerUneAlarme(int ligne) {
        alAlarme.remove(ligne);
        this.notifyDataSetChanged();
    }
}
