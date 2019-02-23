package com.example.toptativa2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toptativa2.Dialogs.InfoDialog;
import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.JuegoDataSource;
import com.example.toptativa2.db.UserDataSource;

public class CrearSorteoActivity extends AppCompatActivity {

    private TextView tv_nom_user;
    private UserDataSource ds;
    private JuegoDataSource jds;

    private EditText et_costo, et_fechaSorteo, et_valor, et_premio, et_titulo;
    android.support.design.widget.FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sorteo);

        et_costo=(EditText)findViewById(R.id.et_costo);
        et_fechaSorteo=(EditText)findViewById(R.id.et_fechaSorteo);
        et_valor=(EditText)findViewById(R.id.et_valor);
        et_premio=(EditText)findViewById(R.id.et_premio);
        et_titulo=(EditText)findViewById(R.id.et_titulo);

        tv_nom_user=(TextView)findViewById(R.id.tv_nom_user);
        ds= new UserDataSource(CrearSorteoActivity.this);
        jds = new JuegoDataSource(CrearSorteoActivity.this);
        loadUser();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notEmpty()){
                    if(crearJuego()){
                        InfoDialog id = new InfoDialog(CrearSorteoActivity.this,"Error","Juego creado con exito", R.drawable.androidtutoria);
                        id.OkButton("Ok",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface alert, int which) {
                                alert.dismiss();
                            }
                        });
                        id.make();
                    }
                }else{
                    InfoDialog id = new InfoDialog(CrearSorteoActivity.this,"Error","Llene los campos que faltan", R.drawable.androidtutoria);
                    id.OkButton("Ok",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface alert, int which) {
                            alert.dismiss();
                            errores();
                        }
                    });
                    id.make();
                }
            }
        });
    }


    public void errores(){
        if(et_fechaSorteo.getText().toString().length()==0)
            et_fechaSorteo.setError("Ingrese la fecha de sorteo");
        if(et_titulo.getText().toString().length()==0)
            et_titulo.setError("Ingrese nombre de sorteo");
        if(et_valor.getText().toString().length()==0)
            et_valor.setError("Ingrese el valor del sorteo");
        if(et_premio.getText().toString().length()==0)
            et_premio.setError("Ingrese el Nombre del premio");
        if(et_costo.getText().toString().length()==0)
            et_costo.setError("Ingrese el costo del sorteo");
    }


    public boolean crearJuego(){
        boolean saved= false;
        Juego juego =  new Juego();

        juego.setFecha_juego(et_fechaSorteo.getText().toString());
        juego.setNombre_juego(et_titulo.getText().toString());
        float premio_mayor = Float.parseFloat(et_valor.getText().toString());
        juego.setPremio_mayor(premio_mayor);
        juego.setEstado_juego("A");
        juego.setTipo_juego("S");
        juego.setNombre_premio(et_premio.getText().toString());
        float cuota = Float.parseFloat(et_costo.getText().toString());
        juego.setCuota(cuota);
        try{
            jds.open();
            saved = (jds.insert(juego)>0);
        }catch(SQLException e){
            e.printStackTrace();
            saved=false;
        }
        return saved;
    }

    public boolean notEmpty(){
        return (
                et_fechaSorteo.getText().toString().length()>0&&
                et_titulo.getText().toString().length()>0&&
                et_valor.getText().toString().length()>0&&
                et_premio.getText().toString().length()>0&&
                et_costo.getText().toString().length()>0
                );
    }

    private void loadUser(){
        User usu = null;
        try{
            ds.open();
            usu = ds.getUser();
            tv_nom_user.setText(usu.getFullname());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
