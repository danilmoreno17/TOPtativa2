package com.example.toptativa2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.R;

import java.util.ArrayList;

public class adaSorteosLV extends BaseAdapter {

    private TextView tv_fecha_juego, tv_premio_juego, tv_costo_juego, tv_nom_juego;

    private ArrayList<Juego> lista;
    private Juego juego;
    private Context c;
    private LayoutInflater layoutInflater;


    public adaSorteosLV(Context context, ArrayList<Juego> listaJuego){
        this.layoutInflater =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.c=context;
        this.lista=listaJuego;
    }


    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = convertView;
        if(convertView==null){
            view = layoutInflater.inflate(R.layout.sorteo_adapter,null,true);
        }

        tv_fecha_juego =(TextView)view.findViewById(R.id.tv_fecha_juego);
        tv_premio_juego =(TextView)view.findViewById(R.id.tv_premio_juego);
        tv_costo_juego =(TextView)view.findViewById(R.id.tv_costo_juego);
        tv_nom_juego =(TextView)view.findViewById(R.id.tv_nom_juego);
        juego = (Juego)lista.get(position);

        tv_fecha_juego.setText(juego.getFecha_juego());
        tv_premio_juego.setText(String.valueOf(juego.getPremio_mayor()));
        tv_costo_juego.setText(String.valueOf(juego.getCuota()));
        tv_nom_juego.setText(String.valueOf(juego.getNombre_juego()));

        return view;
    }
}
