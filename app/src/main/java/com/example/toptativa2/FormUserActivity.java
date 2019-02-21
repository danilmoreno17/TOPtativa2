package com.example.toptativa2;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.toptativa2.Dialogs.InfoDialog;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.UserDataSource;

public class FormUserActivity extends AppCompatActivity {


    private EditText et_full_nombre, et_email, et_password;
    private Button btn_suscribe, btn_clean;
    private RadioGroup rg_suscripcion;

    private RadioButton rb_op1,rb_op2,rb_op3,rb_op4;

    private ImageView iv_check;

    private UserDataSource ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);
        setElems();
        ds = new UserDataSource(this);
        rb_op1.setChecked(true);
        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv_check.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_checkbox_checked).getConstantState()))
                   iv_check.setImageResource(R.drawable.img_checkbox_uncheck);
                else{
                    iv_check.setImageResource(R.drawable.img_checkbox_checked);
                }
            }
        });
        btn_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_full_nombre.setText("");
                et_email.setText("");
                et_password.setText("");

                rb_op1.setChecked(true);

            }
        });
        btn_suscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notEmpty()) {
                    if (!userExists()) {
                        String texto = "";
                        String titulo = "";
                        if(register()){
                            texto = "Usuario registrado con exito";
                            titulo = "Exito";
                        }else{
                            texto = "Error al Crear al usuario";
                            titulo = "Error";
                        }
                        InfoDialog dialogo = new InfoDialog(FormUserActivity.this,titulo,texto, R.drawable.androidtutoria);
                        dialogo.OkButton("Ok",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface alert, int which) {
                                alert.dismiss();
                                Intent i = new Intent(FormUserActivity.this,LoginActivity.class);
                                startActivity(i);
                            }
                        });
                        dialogo.make();
                    }else{
                        et_email.setError("Ese email le pertenece a otro usuario");
                    }
                }else{
                    if(et_full_nombre.getText().toString().length()==0)
                        et_full_nombre.setError("Ingrese su nombre");
                    if(et_email.getText().toString().length()==0)
                        et_email.setError("Ingrese su email");
                    if(et_password.getText().toString().length()==0)
                        et_password.setError("Ingrese su password");
                }
            }
        });

    }


    private void setElems(){
        et_full_nombre=(EditText)findViewById(R.id.et_full_nombre);
        et_email=(EditText)findViewById(R.id.et_email);
        et_password=(EditText)findViewById(R.id.et_password);

        rg_suscripcion = (RadioGroup)findViewById(R.id.rg_suscripcion);
        rb_op1=(RadioButton)findViewById(R.id.rb_op1);
        rb_op2=(RadioButton)findViewById(R.id.rb_op2);
        rb_op3=(RadioButton)findViewById(R.id.rb_op3);
        rb_op4=(RadioButton)findViewById(R.id.rb_op4);

        btn_suscribe = (Button)findViewById(R.id.btn_suscribe);
        btn_clean = (Button)findViewById(R.id.btn_clean);

        iv_check= (ImageView)findViewById(R.id.iv_check);
    }

    private boolean notEmpty(){
        return (et_full_nombre.getText().toString().length()>0&&
                et_email.getText().toString().length()>0&&
                et_password.getText().toString().length()>0);
    }

    private boolean userExists(){
        User usu = null;
        try{
            ds.open();
            usu = ds.getLoginUser(et_email.getText().toString());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return (usu!=null);
    }

    private boolean register(){
        User u = new User();
        try{
            ds.open();
            int op = (rb_op1.isChecked())?1:(rb_op2.isChecked())?2:(rb_op3.isChecked())?3:4;
            u.setActive(1);
            u.setEmail(et_email.getText().toString());
            u.setFullname(et_full_nombre.getText().toString());
            u.setPassword(et_password.getText().toString());
            u.setSus_method(op);
            String type = iv_check.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.img_checkbox_checked).getConstantState())?"C":"J";

            //String type = (iv_check.getDrawable() == R.drawable.img_checkbox_checked)?"C":"J";
            u.setUser_type(type);
            return (ds.insert(u)>0);
        }catch(SQLException ex){

        }
        return false;
    }
}
