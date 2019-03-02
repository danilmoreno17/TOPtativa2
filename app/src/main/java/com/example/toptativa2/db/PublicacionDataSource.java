package com.example.toptativa2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.toptativa2.Models.Publicacion;

import java.util.ArrayList;

public class PublicacionDataSource {

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public PublicacionDataSource(Context c){
        dbHelper = new DataBaseHelper(c);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    private ContentValues publicacionValues(Publicacion p){
        ContentValues v = new ContentValues();
        //v.put(DataBaseHelper.ID_PUBLICACION,p.getId());
        v.put(DataBaseHelper.ID_USUARIO_PUBLICACION,p.getId_usuario());
        v.put(DataBaseHelper.ID_JUEGO_PUBLICACION,p.getId_juego());
        v.put(DataBaseHelper.ESTADO_PUBLICACION,p.getEstado());
        v.put(DataBaseHelper.FECHA_PUBLICACION,p.getFecha_publicacion());
        v.put(DataBaseHelper.FECHA_TOPE,p.getFecha_tope());
        return v;
    }

    private Publicacion cursorPublicacion(Cursor c){
        Publicacion p=new Publicacion();
        p.setNombre_juego(c.getString(c.getColumnIndex(DataBaseHelper.NOMBRE_JUEGO)));
        p.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_PUBLICACION)));
        p.setId_usuario(c.getInt(c.getColumnIndex(DataBaseHelper.ID_USUARIO_PUBLICACION)));
        p.setId_juego(c.getInt(c.getColumnIndex(DataBaseHelper.ID_JUEGO_PUBLICACION)));
        p.setEstado(c.getString(c.getColumnIndex(DataBaseHelper.ESTADO_PUBLICACION)));
        p.setFecha_publicacion(c.getString(c.getColumnIndex(DataBaseHelper.FECHA_PUBLICACION)));
        p.setFecha_tope(c.getString(c.getColumnIndex(DataBaseHelper.FECHA_TOPE)));
        p.setNumero_premiado(c.getString(c.getColumnIndex(DataBaseHelper.NUMERO_PREMIADO)));
        return p;
    }



    public long insert(Publicacion p){
        ContentValues v = publicacionValues(p);
        long resp = database.insert(DataBaseHelper.TBL_PUBLICACION,null,v);
        return resp;
    }

    public long update(Publicacion p){
        ContentValues v =publicacionValues(p);
        String where = DataBaseHelper.ID_PUBLICACION+"="+p.getId();
        long resp = database.update(DataBaseHelper.TBL_PUBLICACION,v,where,null);
        return resp;
    }

    public long numero_premiado(int id, String premiado){
        ContentValues v =new ContentValues();
        v.put(DataBaseHelper.NUMERO_PREMIADO,premiado);
        String where = DataBaseHelper.ID_PUBLICACION+"="+id;
        long resp = database.update(DataBaseHelper.TBL_PUBLICACION,v,where,null);
        return resp;
    }

    public ArrayList<Publicacion> publicacionList(int id_usuario){

        ArrayList<Publicacion> lista = new ArrayList<>();
        Cursor c = null;
        try{
            String tabla_principal = DataBaseHelper.TBL_PUBLICACION;
            String tabla_juego = DataBaseHelper.TBL_JUEGO;
            String tabla_usuario = DataBaseHelper.TBL_USER;
            String where= "WHERE "+DataBaseHelper.ID_USUARIO_PUBLICACION+"="+id_usuario;

            String query =  "SELECT "+tabla_principal+"."+DataBaseHelper.ID_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.ID_USUARIO_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.ID_JUEGO_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.ESTADO_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.FECHA_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.FECHA_TOPE+","+
                    tabla_principal+"."+DataBaseHelper.NUMERO_PREMIADO+","+
                    tabla_juego+"."+DataBaseHelper.NOMBRE_JUEGO+","+
                    tabla_juego+"."+DataBaseHelper.PREMIO_MAYOR+" "+
                    "FROM "+tabla_principal+" " +
                    "INNER JOIN "+tabla_juego+" ON "+tabla_juego+"."+DataBaseHelper.ID_JUEGO+"="+tabla_principal+"."+DataBaseHelper.ID_JUEGO_PUBLICACION+" "+
                    where;
            c = database.rawQuery(query, null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                lista.add(cursorPublicacion(c));
                c.moveToNext();
            }
        }catch(SQLException ex){
            ex.getMessage();
        }

        return lista;
    }

    public Publicacion getPublicacion(int id_usuario, int id_juego){
        Publicacion p=null;
        Cursor c = null;
        try{
            String tabla_principal = DataBaseHelper.TBL_PUBLICACION;
            String tabla_juego = DataBaseHelper.TBL_JUEGO;
            String tabla_usuario = DataBaseHelper.TBL_USER;
            String where= "WHERE "+DataBaseHelper.ID_USUARIO_PUBLICACION+"="+id_usuario+" AND "+tabla_principal+"."+DataBaseHelper.NUMERO_PREMIADO+"="+id_juego;

            String query =  "SELECT "+tabla_principal+"."+DataBaseHelper.ID_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.ID_USUARIO_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.ID_JUEGO_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.ESTADO_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.FECHA_PUBLICACION+","+
                    tabla_principal+"."+DataBaseHelper.FECHA_TOPE+","+
                    tabla_principal+"."+DataBaseHelper.NUMERO_PREMIADO+","+
                    tabla_juego+"."+DataBaseHelper.NOMBRE_JUEGO+","+
                    tabla_juego+"."+DataBaseHelper.PREMIO_MAYOR+" "+
                    "FROM "+tabla_principal+" " +
                    "INNER JOIN "+tabla_juego+" ON "+tabla_juego+"."+DataBaseHelper.ID_JUEGO+"="+tabla_principal+"."+DataBaseHelper.ID_JUEGO_PUBLICACION+" "+
                    where;
            c = database.rawQuery(query, null);
            if(c.getCount()>0){
                c.moveToFirst();
                p=cursorPublicacion(c);
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return p;
    }

}
