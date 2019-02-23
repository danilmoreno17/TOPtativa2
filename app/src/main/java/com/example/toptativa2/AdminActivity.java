package com.example.toptativa2;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.UserDataSource;

public class AdminActivity extends AppCompatActivity {
    private CardView cv_crearSorteo, cv_misSorteos;

    private TextView tvNombreUsuario;
    private UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tvNombreUsuario=(TextView)findViewById(R.id.tvNombreUsuario);
        ds= new UserDataSource(AdminActivity.this);
        loadUser();

        cv_crearSorteo = (CardView) findViewById(R.id.cv_crearSorteo);
        cv_misSorteos = (CardView)findViewById(R.id.cv_misSorteos);
        cv_crearSorteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,CrearSorteoActivity.class));
            }
        });
        cv_misSorteos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,MisSorteosActivity.class));
            }
        });
    }

    private void loadUser(){
        User usu = null;
        try{
            ds.open();
            usu = ds.getUser();
            tvNombreUsuario.setText(usu.getFullname());
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
