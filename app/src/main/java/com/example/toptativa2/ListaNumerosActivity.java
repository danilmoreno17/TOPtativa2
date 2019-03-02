package com.example.toptativa2;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toptativa2.Adapters.adaNumeros;
import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.Models.NumericGenerator;
import com.example.toptativa2.Models.Numero;
import com.example.toptativa2.Models.NumeroJuego;
import com.example.toptativa2.Models.Publicacion;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.NumeroDataSource;

import java.util.ArrayList;

public class ListaNumerosActivity extends AppCompatActivity {

    private User usuario;
    private TextView tv_nom_user;
    private ListView lv_numeros;
    private ArrayList<String> numeros;
    private adaNumeros adapter;
    private static Publicacion publicacion;

    private ArrayList<NumeroJuego> lista_numero_juego;

    private NumeroDataSource nds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_numeros);

        nds=new NumeroDataSource(ListaNumerosActivity.this);

        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        lv_numeros=(ListView)findViewById(R.id.lv_numeros);
        tv_nom_user=(TextView)findViewById(R.id.tv_nom_user);
        tv_nom_user.setText(usuario.getFullname());
        if(getNumeros().size()==0){
            numeros= new ArrayList<>();
            numeros.add(NumericGenerator.getNumers(8));
        }
        for(int i=0;i<60;i++){
            numeros.add(NumericGenerator.getNumericList(numeros,8));
        }
        adapter= new adaNumeros(ListaNumerosActivity.this,numeros);
        lv_numeros.setAdapter(adapter);


    }



    public ArrayList<String> getNumeros(){

        this.publicacion= JugarSorteoActivity.publicacion;
        try {
            nds.open();
            int id_juego =this.publicacion.getId_juego();
            return nds.juegosList(id_juego);
        }catch (Exception e){
            return null;
        }

    }



}
