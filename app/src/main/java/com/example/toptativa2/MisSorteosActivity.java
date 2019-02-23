package com.example.toptativa2;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.UserDataSource;

public class MisSorteosActivity extends AppCompatActivity {

    private TextView tv_nom_user;
    private UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_sorteos);

        tv_nom_user=(TextView)findViewById(R.id.tv_nom_user);
        ds= new UserDataSource(MisSorteosActivity.this);
        loadUser();
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
