package com.example.toptativa2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.toptativa2.Models.Premiacion;

import java.util.ArrayList;

public class PremiacionDataSource {

    private String[] allColumns={
            DataBaseHelper.ID_PREMIACION,
            DataBaseHelper.ID_PREMIACION_USUARIO,
            DataBaseHelper.ID_PREMIACION_PUBLICACION,
            DataBaseHelper.NUM_JUEGO,
            DataBaseHelper.ESTADO_NUMERO
    };

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public PremiacionDataSource(Context c){
        dbHelper = new DataBaseHelper(c);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    private Premiacion cursorPremiacion(Cursor c){
        Premiacion p=new Premiacion();

        p.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_PREMIACION)));
        p.setId_usuario(c.getInt(c.getColumnIndex(DataBaseHelper.ID_PREMIACION_USUARIO)));
        p.setId_publicacion(c.getInt(c.getColumnIndex(DataBaseHelper.ID_PREMIACION_PUBLICACION)));
        p.setNum_juego(c.getString(c.getColumnIndex(DataBaseHelper.NUM_JUEGO)));
        p.setEstado_numero(c.getInt(c.getColumnIndex(DataBaseHelper.ESTADO_NUMERO)));

        return p;
    }

    public ContentValues premiacionValues(Premiacion p){
        ContentValues v = new ContentValues();
        v.put(DataBaseHelper.ID_PREMIACION,p.getId());
        v.put(DataBaseHelper.ID_PREMIACION_USUARIO,p.getId_usuario());
        v.put(DataBaseHelper.ID_PREMIACION_PUBLICACION,p.getId_publicacion());
        v.put(DataBaseHelper.NUM_JUEGO,p.getNum_juego());
        v.put(DataBaseHelper.ESTADO_NUMERO,p.getEstado_numero());
        return v;
    }

    public long insert(Premiacion p){
        ContentValues v = premiacionValues(p);
        long resp = database.insert(DataBaseHelper.TBL_PREMIACION,null,v);
        return resp;
    }

    public long update(Premiacion p){
        ContentValues v =premiacionValues(p);
        long resp = database.update(DataBaseHelper.TBL_PREMIACION,v,DataBaseHelper.ID_PREMIACION+"="+p.getId(),null);
        return resp;
    }

    public ArrayList<Premiacion> premiacionList(int id_usuario){

        ArrayList<Premiacion> lista = new ArrayList<>();
        Cursor c = null;
        try{
            String where= "WHERE "+DataBaseHelper.ID_PREMIACION_USUARIO+"="+id_usuario;
            c = database.query(DataBaseHelper.TBL_PREMIACION,allColumns,where,null,null,null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                lista.add(cursorPremiacion(c));
                c.moveToNext();
            }
        }catch(SQLException ex){
            ex.getMessage();
        }

        return lista;
    }
    public ArrayList<Premiacion> premiacionListByPublicacion(int id_publicacion){

        ArrayList<Premiacion> lista = new ArrayList<>();
        Cursor c = null;
        try{
            String where= "WHERE "+DataBaseHelper.ID_PREMIACION_PUBLICACION+"="+id_publicacion;
            c = database.query(DataBaseHelper.TBL_PREMIACION,allColumns,where,null,null,null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                lista.add(cursorPremiacion(c));
                c.moveToNext();
            }
        }catch(SQLException ex){
            ex.getMessage();
        }

        return lista;
    }

}
