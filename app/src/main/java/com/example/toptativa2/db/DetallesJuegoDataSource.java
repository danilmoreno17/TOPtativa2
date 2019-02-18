package com.example.toptativa2.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.toptativa2.Models.DetallesJuego;

import java.util.ArrayList;

public class DetallesJuegoDataSource {

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public DetallesJuegoDataSource(Context c){
        dbHelper = new DataBaseHelper(c);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public DetallesJuego loadDetalleJuegos(int id){
        DetallesJuego dj = new DetallesJuego();
        Cursor c= null;
        try{
            String tabla_detalle = DataBaseHelper.TBL_DETALLES_JUEGO;
            String tabla = DataBaseHelper.TBL_JUEGO;


            String query = "SELECT "+tabla_detalle+"."+DataBaseHelper.ID_DETALLE_JUEGO+","+
                                     tabla_detalle+"."+DataBaseHelper.ID_JUEGO_DETALLE_JUEGO+","+
                                     tabla_detalle+"."+DataBaseHelper.PREMIO_EFECTIVO+","+
                                     tabla_detalle+"."+DataBaseHelper.PREMIO_NOMBRE+","+
                                     tabla_detalle+"."+DataBaseHelper.NUM_GANADOR+","+
                                     tabla+"."+DataBaseHelper.NOMBRE_JUEGO+" "+
                            "FROM "+tabla_detalle+" " +
                            "INNER JOIN "+tabla+" ON "+tabla+"."+DataBaseHelper.ID_JUEGO+"="+tabla_detalle+"."+DataBaseHelper.ID_JUEGO_DETALLE_JUEGO+" "+
                            "WHERE "+tabla_detalle+"."+DataBaseHelper.ID_JUEGO_DETALLE_JUEGO+" = "+id;


            c = database.rawQuery(query, null);
            if(c.getCount()>0){
                c.moveToFirst();
                dj=cursorJuego(c);
            }
        }finally{
            c.close();
        }
        return dj;
    }


    private DetallesJuego cursorJuego(Cursor c){
        DetallesJuego dj=new DetallesJuego();
        dj.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_DETALLE_JUEGO)));
        dj.setId_juego(c.getInt(c.getColumnIndex(DataBaseHelper.ID_JUEGO_DETALLE_JUEGO)));
        dj.setPremio_efectivo(c.getFloat(c.getColumnIndex(DataBaseHelper.PREMIO_EFECTIVO)));
        dj.setPremio_nombre(c.getString(c.getColumnIndex(DataBaseHelper.PREMIO_NOMBRE)));
        dj.setNum_ganador(c.getString(c.getColumnIndex(DataBaseHelper.NUM_GANADOR)));
        dj.setNombre_juego_principal(c.getString(c.getColumnIndex(DataBaseHelper.NOMBRE_JUEGO)));
        return dj;
    }


    public ArrayList<DetallesJuego> detalles_juegosList(int id_juego){

        ArrayList<DetallesJuego> lista = new ArrayList<>();
        Cursor c = null;
        try{
            String tabla_detalle = DataBaseHelper.TBL_DETALLES_JUEGO;
            String tabla = DataBaseHelper.TBL_JUEGO;
            String where= "WHERE "+DataBaseHelper.ID_JUEGO_DETALLE_JUEGO+"="+id_juego;

            String query =  "SELECT "+tabla_detalle+"."+DataBaseHelper.ID_DETALLE_JUEGO+","+
                                      tabla_detalle+"."+DataBaseHelper.ID_JUEGO_DETALLE_JUEGO+","+
                                      tabla_detalle+"."+DataBaseHelper.PREMIO_EFECTIVO+","+
                                      tabla_detalle+"."+DataBaseHelper.PREMIO_NOMBRE+","+
                                      tabla_detalle+"."+DataBaseHelper.NUM_GANADOR+","+
                                      tabla+"."+DataBaseHelper.NOMBRE_JUEGO+" "+
                            "FROM "+tabla_detalle+" " +
                            "INNER JOIN "+tabla+" ON "+tabla+"."+DataBaseHelper.ID_JUEGO+"="+tabla_detalle+"."+DataBaseHelper.ID_JUEGO_DETALLE_JUEGO+" "+
                            where;
            c = database.rawQuery(query, null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                lista.add(cursorJuego(c));
                c.moveToNext();
            }
        }catch(SQLException ex){
            ex.getMessage();
        }

        return lista;
    }





}
