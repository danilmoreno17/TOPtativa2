package com.example.toptativa2;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptativa2.Adapters.adaMenuRV;
import com.example.toptativa2.Models.Option;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.UserDataSource;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private TextView tv_nom_user;
    private UserDataSource ds;
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tv_nom_user=(TextView)findViewById(R.id.tv_nom_user);
        /*ds= new UserDataSource(MenuActivity.this);
        loadUser();*/
        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        tv_nom_user.setText(usuario.getFullname());

        RecyclerView mRecyclerView = findViewById(R.id.rvMenu);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MenuActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        List<Option> mOptionList = new ArrayList<>();
        mOptionList.add(new Option("SORTEOS",R.drawable.sorteo));
        mOptionList.add(new Option("BINGOS",R.drawable.bingo));
        mOptionList.add(new Option("LOTERIA",R.drawable.loteria));
        mOptionList.add(new Option("LOTERIA NACIONAL",R.drawable.loterianacional));
        adaMenuRV adapter = new adaMenuRV(MenuActivity.this,mOptionList);
        mRecyclerView.setAdapter(adapter);

        final GestureDetector mGestureDetector = new GestureDetector(MenuActivity.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                    boolean exitApp=false;
                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                        Intent i =null;
                        int position = recyclerView.getChildAdapterPosition(child);

                        if(position==0){//warehouse
                           i = new Intent(MenuActivity.this,AdminActivity.class);
                        }


                        startActivity(i);

                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });

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
