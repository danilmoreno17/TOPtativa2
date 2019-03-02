package com.example.toptativa2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.toptativa2.R;

import java.util.ArrayList;

public class adaNumeros extends BaseAdapter {


    private TextView tv_numero;

    private ArrayList<String> lista;
    private String juego;
    private Context c;
    private LayoutInflater layoutInflater;


    public adaNumeros(Context context, ArrayList<String> lista){
        this.c=context;
        this.lista=lista;
        this.layoutInflater =(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = convertView;
        if(convertView==null){
            view = layoutInflater.inflate(R.layout.numeros_adapter,null,true);
        }

        tv_numero =(TextView)view.findViewById(R.id.tv_numero);
        juego = (String) this.lista.get(position);

        tv_numero.setText(juego);

        return view;
    }
}
