package com.example.toptativa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.toptativa2.Models.Publicacion;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.JuegoDataSource;
import com.example.toptativa2.db.PublicacionDataSource;

public class EditarSorteoActivity extends AppCompatActivity {

    EditText et_titulo, et_premio,et_numero_ganador;

    TextView tvNombreUsuario,tv_valor_premio, tv_fecha_juego, tv_costo_juego;
    private JuegoDataSource jds;
    private PublicacionDataSource pds;
    private User usuario;
    private Publicacion p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sorteo);

        //Cargar Usuario
        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        tvNombreUsuario.setText(usuario.getFullname());
        jds = new JuegoDataSource(this);
        pds = new PublicacionDataSource(this);
        et_titulo = (EditText)findViewById(R.id.et_titulo);
        et_premio = (EditText)findViewById(R.id.et_premio);
        et_numero_ganador=(EditText)findViewById(R.id.et_numero_ganador);
        tv_valor_premio = (TextView)findViewById(R.id.tv_valor_premio);
        tv_fecha_juego = (TextView)findViewById(R.id.tv_fecha_juego);
        tv_costo_juego = (TextView)findViewById(R.id.tv_costo_juego);

    }
}
