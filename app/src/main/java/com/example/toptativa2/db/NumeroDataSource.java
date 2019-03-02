package com.example.toptativa2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.Models.NumeroJuego;

import java.util.ArrayList;

public class NumeroDataSource {


    public String [] allColumns={
            DataBaseHelper.ID_NUMERO_JUEGO,
            DataBaseHelper.ID_JUEGO_NUMERO_JUEGO,
            DataBaseHelper.ID_USER_NUMERO_JUEGO,
            DataBaseHelper.NUMERO
    };

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public NumeroDataSource(Context c){
        dbHelper = new DataBaseHelper(c);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public ContentValues juegoValues(NumeroJuego j){
        ContentValues v = new ContentValues();

        v.put(DataBaseHelper.ID_JUEGO_NUMERO_JUEGO,j.getId_juego());
        if(j.getId_user()>0)
            v.put(DataBaseHelper.ID_USER_NUMERO_JUEGO,j.getId_user());
        v.put(DataBaseHelper.NUMERO,j.getNumero_juego());
        return v;
    }


    public long insert(NumeroJuego j){
        ContentValues v = juegoValues(j);
        long resp = database.insert(DataBaseHelper.TBL_NUMERO_JUEGO,null,v);
        return resp;
    }

    public long update(NumeroJuego j){
        ContentValues v =juegoValues(j);
        long resp = database.update(DataBaseHelper.TBL_NUMERO_JUEGO,v,DataBaseHelper.ID_NUMERO_JUEGO+"="+j.getId(),null);
        return resp;
    }


    private NumeroJuego cursorJuego(Cursor c){
        NumeroJuego j=new NumeroJuego();

        j.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_NUMERO_JUEGO)));
        j.setId_juego(c.getInt(c.getColumnIndex(DataBaseHelper.ID_JUEGO_NUMERO_JUEGO)));
        j.setId_user(c.getInt(c.getColumnIndex(DataBaseHelper.ID_USER_NUMERO_JUEGO)));
        j.setNumero_juego(c.getString(c.getColumnIndex(DataBaseHelper.NUMERO)));

        return j;
    }


    public ArrayList<String> juegosList(int id_juego){
        ArrayList<String> lista = new ArrayList<>();
        Cursor c = null;
        try{
            String where= DataBaseHelper.ID_JUEGO_NUMERO_JUEGO+"="+id_juego;
            c=database.query(DataBaseHelper.TBL_NUMERO_JUEGO,allColumns,where,null,null,null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                NumeroJuego j = cursorJuego(c);
                lista.add(j.getNumero_juego());
                c.moveToNext();
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return lista;
    }


    public NumeroJuego verificaJuego(String numero){
        NumeroJuego juego = new NumeroJuego();
        Cursor c = null;
        try{
            String where= DataBaseHelper.NUMERO+"='"+numero+"'";
            c=database.query(DataBaseHelper.TBL_JUEGO,allColumns,where,null,null,null,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                juego = cursorJuego(c);
                c.moveToNext();
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        return juego;
    }


}
