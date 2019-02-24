package com.example.toptativa2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toptativa2.Dialogs.InfoDialog;
import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.Models.Publicacion;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.JuegoDataSource;
import com.example.toptativa2.db.PublicacionDataSource;

import java.sql.SQLException;
import java.util.Date;

public class CrearSorteoActivity extends AppCompatActivity {
    EditText et_titulo, et_premio, et_valor, et_fechaSorteo, et_costo;
    TextView tvNombreUsuario;
    private JuegoDataSource jds;
    private PublicacionDataSource pds;
    private User usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sorteo);
        tvNombreUsuario = (TextView)findViewById(R.id.tv_nom_user);
        //Cargar Usuario
        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        tvNombreUsuario.setText(usuario.getFullname());
        jds = new JuegoDataSource(this);
        pds = new PublicacionDataSource(this);
        et_titulo = (EditText)findViewById(R.id.et_titulo);
        et_premio = (EditText)findViewById(R.id.et_premio);
        et_valor = (EditText)findViewById(R.id.et_valor);
        et_fechaSorteo = (EditText)findViewById(R.id.et_fechaSorteo);
        et_costo = (EditText)findViewById(R.id.et_costo);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notEmpty()){
                    String texto = "";
                    String titulo = "";
                    if(crearSorteo()){
                        texto = "Sorteo creado con exito";
                        titulo = "Exito";
                    }else{
                        texto = "Error al Crear Sorteo";
                        titulo = "Error";
                    }
                    InfoDialog dialogo = new InfoDialog(CrearSorteoActivity.this,titulo,texto, R.drawable.androidtutoria);
                    dialogo.OkButton("Ok",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface alert, int which) {
                            alert.dismiss();
                            Intent i = new Intent(CrearSorteoActivity.this,AdminActivity.class);
                            startActivity(i);
                        }
                    });
                    dialogo.make();
                }else{
                    if(et_titulo.getText().toString().length()==0)
                        et_titulo.setError("Ingrese el titulo");
                    if(et_premio.getText().toString().length()==0)
                        et_premio.setError("Ingrese el premio");
                    if(et_valor.getText().toString().length()==0)
                        et_valor.setError("Ingrese el valor");
                    if(et_fechaSorteo.getText().toString().length()==0)
                        et_fechaSorteo.setError("Ingrese la fecha");
                    if(et_costo.getText().toString().length()==0)
                        et_costo.setError("Ingrese el costo");
                }
            }
        });
    }

    private boolean crearSorteo() {
        Juego juego = new Juego();
        Publicacion publicacion = new Publicacion();
        try {
            jds.open();
            juego.setNombre_juego(et_titulo.getText().toString());
            juego.setPremio_mayor(Float.parseFloat(et_valor.getText().toString()));
            juego.setCuota(Float.parseFloat(et_costo.getText().toString()));
            juego.setFecha_juego(et_fechaSorteo.getText().toString());
            juego.setEstado_juego("1");
            juego.setTipo_juego("S");
            boolean isInsertjds = (jds.insert(juego)>0);

            pds.open();
            publicacion.setId_juego(jds.juegoByNombre(juego.getNombre_juego()).getId());
            publicacion.setId_usuario(usuario.getId());
            publicacion.setFecha_publicacion(new Date().toString());
            publicacion.setFecha_tope(juego.getFecha_juego());
            publicacion.setNombre_juego(juego.getNombre_juego());
            publicacion.setPremio_mayor(et_premio.getText().toString());
            publicacion.setPremio_dinero(juego.getPremio_mayor());
            publicacion.setEstado("1");
            return (isInsertjds&&(pds.insert(publicacion)>0));
        }catch (Exception ex){

        }
        return false;
    }

    private boolean notEmpty(){
        return (et_titulo.getText().toString().length()>0&&
                et_premio.getText().toString().length()>0&&
                et_valor.getText().toString().length()>0&&
                et_fechaSorteo.getText().toString().length()>0&&
                et_costo.getText().toString().length()>0);
    }
}
