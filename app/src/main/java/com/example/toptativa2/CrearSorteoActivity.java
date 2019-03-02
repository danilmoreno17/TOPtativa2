package com.example.toptativa2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.toptativa2.Dialogs.InfoDialog;
import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.Models.Publicacion;
import com.example.toptativa2.Models.User;
import com.example.toptativa2.db.JuegoDataSource;
import com.example.toptativa2.db.PublicacionDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class CrearSorteoActivity extends AppCompatActivity {
    EditText et_titulo, et_premio, et_valor, et_fechaSorteo, et_costo;
    TextView tvNombreUsuario;
    ImageView iv_User;
    private JuegoDataSource jds;
    private PublicacionDataSource pds;
    private User usuario;
    private static final String CERO = "0";
    private static final String BARRA = "/";
    private static final String DOS_PUNTOS = ":";

    private String fechaPicker="";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sorteo);
        tvNombreUsuario = (TextView)findViewById(R.id.tv_nom_user);
        //Cargar Usuario
        if(((EurekaAppAplication)getApplication()).UsuarioActual!=null)
            usuario = ((EurekaAppAplication)getApplication()).UsuarioActual;

        iv_User=(ImageView)findViewById(R.id.iv_user);
        loadImageFromStorage(usuario.getUrlImage());

        tvNombreUsuario.setText(usuario.getFullname());
        jds = new JuegoDataSource(this);
        pds = new PublicacionDataSource(this);
        et_titulo = (EditText)findViewById(R.id.et_titulo);
        et_premio = (EditText)findViewById(R.id.et_premio);
        et_valor = (EditText)findViewById(R.id.et_valor);
        et_fechaSorteo = (EditText)findViewById(R.id.et_fechaSorteo);
        et_fechaSorteo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });
        et_costo = (EditText)findViewById(R.id.et_costo);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notEmpty()){
                    String texto = "";
                    String titulo = "";
                    if(crearSorteo()){
                        texto = "Sorteo creado con exito";
                        titulo = "Exito";
                    }else{
                        texto = "Error al Crear Sorteo";
                        titulo = "Error";
                    }
                    InfoDialog dialogo = new InfoDialog(CrearSorteoActivity.this,titulo,texto, R.drawable.androidtutoria);
                    dialogo.OkButton("Ok",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface alert, int which) {
                            alert.dismiss();
                            Intent i = new Intent(CrearSorteoActivity.this,AdminActivity.class);
                            startActivity(i);
                        }
                    });
                    dialogo.make();
                }else{
                    if(et_titulo.getText().toString().length()==0)
                        et_titulo.setError("Ingrese el titulo");
                    if(et_premio.getText().toString().length()==0)
                        et_premio.setError("Ingrese el premio");
                    if(et_valor.getText().toString().length()==0)
                        et_valor.setError("Ingrese el valor");
                    if(et_fechaSorteo.getText().toString().length()==0)
                        et_fechaSorteo.setError("Ingrese la fecha");
                    if(et_costo.getText().toString().length()==0)
                        et_costo.setError("Ingrese el costo");
                }
            }
        });
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                fechaPicker = diaFormateado + BARRA + mesFormateado + BARRA + year;
                obtenerHora();
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                fechaPicker += " "+ horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM;
                et_fechaSorteo.setText(fechaPicker);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    private boolean crearSorteo() {
        Juego juego = new Juego();
        Publicacion publicacion = new Publicacion();
        try {
            jds.open();
            juego.setNombre_juego(et_titulo.getText().toString());
            juego.setPremio_mayor(Float.parseFloat(et_valor.getText().toString()));
            juego.setCuota(Float.parseFloat(et_costo.getText().toString()));
            juego.setFecha_juego(et_fechaSorteo.getText().toString());
            juego.setEstado_juego("1");
            juego.setTipo_juego("S");
            boolean isInsertjds = (jds.insert(juego)>0);
            int idJuego = jds.juegoByNombre(juego.getNombre_juego()).getId();
            jds.close();
            pds.open();
            publicacion.setId_juego(idJuego);
            publicacion.setId_usuario(usuario.getId());
            publicacion.setFecha_publicacion(new Date().toString());
            publicacion.setFecha_tope(juego.getFecha_juego());
            publicacion.setNombre_juego(juego.getNombre_juego());
            publicacion.setPremio_mayor(et_premio.getText().toString());
            publicacion.setPremio_dinero(juego.getPremio_mayor());
            publicacion.setEstado("1");
            return (isInsertjds&&(pds.insert(publicacion)>0));
        }catch (Exception ex){

        }
        return false;
    }

    private boolean notEmpty(){
        return (et_titulo.getText().toString().length()>0&&
                et_premio.getText().toString().length()>0&&
                et_valor.getText().toString().length()>0&&
                et_fechaSorteo.getText().toString().length()>0&&
                et_costo.getText().toString().length()>0);
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
