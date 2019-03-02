package com.example.toptativa2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toptativa2.Adapters.adaNumeros;
import com.example.toptativa2.Dialogs.InfoDialog;
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


        numeros= new ArrayList<>();
        numeros.add(NumericGenerator.getNumers(8));
        for(int i=0;i<60;i++){
            numeros.add(NumericGenerator.getNumericList(numeros,8));
        }
        adapter= new adaNumeros(ListaNumerosActivity.this,numeros);
        lv_numeros.setAdapter(adapter);
        lv_numeros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                try {
                    nds.open();
                    NumeroJuego nj =nds.verificaJuego( numeros.get(position));
                    nds.insert(nj);
                }catch (Exception e){

                }


                InfoDialog dialogo = new InfoDialog(ListaNumerosActivity.this,"Respuesta","Ticket reservado", R.drawable.androidtutoria);
                dialogo.OkButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface alert, int which) {
                        alert.dismiss();
                        Intent i = new Intent(ListaNumerosActivity.this,JugarSorteoActivity.class);
                        startActivity(i);
                    }
                });
                dialogo.make();


            }
        });

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
