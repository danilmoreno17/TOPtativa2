package com.example.toptativa2;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.UserDataSource;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class AdminActivity extends AppCompatActivity {
    private CardView cv_crearSorteo, cv_misSorteos;

    private TextView tv_nom_user;
    private UserDataSource ds;
    private User usuario;
    private ImageView iv_User;
    static final int PICKFILE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tv_nom_user=(TextView)findViewById(R.id.tvNombreUsuario);
        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;
        iv_User=(ImageView)findViewById(R.id.iv_user);
        /*
        iv_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, PICKFILE_RESULT_CODE);
            }
        });
        */
        //loadImageFromStorage(usuario.getUrlImage());
        tv_nom_user.setText(usuario.getFullname().toString());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_RESULT_CODE && resultCode == Activity.RESULT_OK){
            Uri content_describer = data.getData();
            iv_User.setImageURI(content_describer);
            Drawable drawable =  iv_User.getDrawable();
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ((EurekaAppAplication)getApplication()).UsuarioActual.saveToInternalStorage(bitmap,getApplicationContext());
        }
    }

    private void loadImageFromStorage(String path)
    {
        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            iv_User.setImageBitmap(b);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
