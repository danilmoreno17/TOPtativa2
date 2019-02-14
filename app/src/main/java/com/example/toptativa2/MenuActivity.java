package com.example.toptativa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.toptativa2.Adapters.adaMenuRV;
import com.example.toptativa2.Models.Option;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
    }
}
