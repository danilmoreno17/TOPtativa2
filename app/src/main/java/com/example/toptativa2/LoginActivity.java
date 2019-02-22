package com.example.toptativa2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toptativa2.Dialogs.InfoDialog;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.UserDataSource;


public class LoginActivity extends Activity {


    private EditText et_user,et_pass;
    private Button btn_create_user, btn_login;

    private UserDataSource ds;
    public static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ds = new UserDataSource(LoginActivity.this);

        et_user=(EditText)findViewById(R.id.et_user);
        et_pass=(EditText)findViewById(R.id.et_pass);

        btn_create_user = (Button) findViewById(R.id.btn_create_user);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notBlank(et_user)&&notBlank(et_pass)){
                    user=loadUser();
                    if(user!=null){
                        //if(user.getPassword().equals(et_pass.getText().toString())){
                            startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                        /*}else{
                            InfoDialog alert = new InfoDialog(LoginActivity.this,"Error","Password incorrecto", R.drawable.androidtutoria);
                            alert.OkButton("Ok",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alert.make();
                        }*/
                    }else{
                        InfoDialog alert = new InfoDialog(LoginActivity.this,"Error","El usuario no existe", R.drawable.androidtutoria);
                        alert.OkButton("Ok",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.make();
                    }

                }else{
                    if(et_user.getText().length()==0)
                        et_user.setError("Ingrese el Email");
                    if(et_pass.getText().length()==0)
                        et_pass.setError("Ingrese el Password");

                }


            }
        });

        btn_create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,FormUserActivity.class));

            }
        });
    }


    private boolean notBlank(EditText text){
        return (text.getText().length()>0);
    }

    private User loadUser(){
        User user=null;
        try{
            ds.open();
            user=ds.getLoginUser(et_user.getText().toString());

        }catch(SQLException ex){

        }
        return user;
    }
}
