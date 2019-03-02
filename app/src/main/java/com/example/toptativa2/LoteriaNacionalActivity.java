package com.example.toptativa2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class  LoteriaNacionalActivity extends AppCompatActivity {
    private ImageView ivloterianacional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loteria_nacional);
        ivloterianacional = (ImageView)findViewById(R.id.ivloterianacional);
        //Picasso.get().load("https://www.loteria.com.ec/Images/Thumb/--Files--boletines--T16221__jpg?width=768&height=1100").into(ivloterianacional);

        String foto = "https://www.loteria.com.ec/Images/Thumb/--Files--boletines--T16221__jpg?width=768&height=1100";
        Picasso.with(LoteriaNacionalActivity.this).load(foto).into(ivloterianacional);
    }
}
