package com.example.toptativa2;

import android.content.Context;
import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptativa2.Adapters.adaSorteosLV;
import com.example.toptativa2.Dialogs.SimpleConfirmDialog;
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

    private Juego juego_procesado;

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

        lista=loadJuegos();
        adapter=new adaSorteosLV(JugarSorteoActivity.this,lista);
        lv_mis_juegos.setAdapter(adapter);

        lv_mis_juegos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                preguardar(lista.get(position));
                SimpleConfirmDialog dialog = new SimpleConfirmDialog(JugarSorteoActivity.this,"Info", "Deseas participar?",R.drawable.androidtutoria);
                dialog.setPositive("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //saveJuego();
                    }
                });
                dialog.setNegative("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no enviar
                        dialog.dismiss();
                    }
                });
                dialog.make();
            }
        });



    }

    private ArrayList<Juego> loadJuegos(){
        ArrayList<Juego> juegos=null;
        try{
            jds.open();
            juegos=jds.juegosList();
            return juegos;
        }catch(SQLException ex){
            juegos=new ArrayList<Juego>();
        }
        return juegos;
    }

    private void preguardar(Juego j){
        juego_procesado=j;
    }
    /*
    private boolean saveJuego(){

    }
    */
}
