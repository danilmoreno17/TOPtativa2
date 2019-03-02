package com.example.toptativa2;

import android.content.Context;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toptativa2.Adapters.adaSorteosLV;
import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.JuegoDataSource;

import java.util.ArrayList;

public class JugarSorteoActivity extends AppCompatActivity {

    private TextView tv_nom_user;
    private ListView lv_mis_juegos;
    private ArrayList<Juego> lista;
    private adaSorteosLV adapter;
    private User usuario;
    private JuegoDataSource jds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_sorteo);

        jds = new JuegoDataSource(JugarSorteoActivity.this);
        tv_nom_user=(TextView)findViewById(R.id.tv_nom_user);
        lv_mis_juegos=(ListView)findViewById(R.id.lv_mis_juegos);

        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        tv_nom_user.setText(usuario.getFullname());

        loadJuegos();
        adapter=new adaSorteosLV(JugarSorteoActivity.this,lista);
        lv_mis_juegos.setAdapter(adapter);




    }

    private void loadJuegos(){
        ArrayList<Juego> juegos=null;
        try{
            jds.open();
            juegos=jds.juegosList();
            int total=juegos.size();
            if(total>0){
                for(int g=0;g<total;g++){
                    lista.add(juegos.get(g));
                }
            }
        }catch(SQLException ex){

        }
    }
}
