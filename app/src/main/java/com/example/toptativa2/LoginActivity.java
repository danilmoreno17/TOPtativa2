package com.example.toptativa2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    EditText et_user,et_pass;
    Button btn_create_user, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_user=(EditText)findViewById(R.id.et_user);
        et_pass=(EditText)findViewById(R.id.et_pass);

        btn_create_user = (Button) findViewById(R.id.btn_create_user);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(notBlank(et_user)&&notBlank(et_pass))

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
}
