package com.example.toptativa2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class AdminActivity extends AppCompatActivity {
    private CardView cv_crearSorteo, cv_misSorteos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        cv_crearSorteo = (CardView) findViewById(R.id.cv_crearSorteo);
        cv_misSorteos = (CardView)findViewById(R.id.cv_misSorteos);
        cv_crearSorteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,CrearSorteoActivity.class));
            }
        });
    }
}
