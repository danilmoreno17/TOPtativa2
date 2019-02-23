package com.example.toptativa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.toptativa2.Adapters.adaMisSorteosRV;
import com.example.toptativa2.Models.Publicacion;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.PublicacionDataSource;

import java.util.ArrayList;
import java.util.List;

public class MisSorteosActivity extends AppCompatActivity {
    private User usuario;
    private TextView tv_nom_user;
    private PublicacionDataSource pds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_sorteos);
        tv_nom_user=(TextView)findViewById(R.id.tvNombreUsuario);

        pds = new PublicacionDataSource(this);
        List<Publicacion> mPublicacionList = new ArrayList<>();
        //Cargar Usuario
        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        tv_nom_user.setText(usuario.getFullname());
        try {
            pds.open();
            mPublicacionList = pds.publicacionList(usuario.getId());
        }catch (Exception e){

        }
        RecyclerView mRecyclerView = findViewById(R.id.rvMisSorteos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adaMisSorteosRV adapter = new adaMisSorteosRV(MisSorteosActivity.this,mPublicacionList);
        mRecyclerView.setAdapter(adapter);
    }
}
