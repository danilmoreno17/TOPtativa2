package com.example.toptativa2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.toptativa2.Models.Juego;

import java.util.ArrayList;

public class JuegoDataSource {

    public String [] allColumns={
            DataBaseHelper.ID_JUEGO,
            DataBaseHelper.FECHA_JUEGO,
            DataBaseHelper.NOMBRE_JUEGO,
            DataBaseHelper.PREMIO_MAYOR,
            DataBaseHelper.ESTADO_JUEGO,
            DataBaseHelper.TIPO_JUEGO,
            DataBaseHelper.CUOTA
    };

    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    public JuegoDataSource(Context c){
        dbHelper = new DataBaseHelper(c);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public ContentValues juegoValues(Juego j){
        ContentValues v = new ContentValues();

            //v.put(DataBaseHelper.ID_JUEGO,j.getId());
        if(!j.getFecha_juego().toString().equals(""))
            v.put(DataBaseHelper.FECHA_JUEGO,j.getFecha_juego());
        if(!j.getNombre_juego().toString().equals(""))
            v.put(DataBaseHelper.NOMBRE_JUEGO,j.getNombre_juego());
        v.put(DataBaseHelper.PREMIO_MAYOR,j.getPremio_mayor());
        v.put(DataBaseHelper.ESTADO_JUEGO,j.getEstado_juego());
        v.put(DataBaseHelper.TIPO_JUEGO,j.getTipo_juego());
        v.put(DataBaseHelper.CUOTA,j.getCuota());
        return v;
    }


    public long insert(Juego j){
        ContentValues v = juegoValues(j);
        long resp = database.insert(DataBaseHelper.TBL_JUEGO,null,v);
        return resp;
    }

    public long update(Juego j){
        ContentValues v =juegoValues(j);
        long resp = database.update(DataBaseHelper.TBL_JUEGO,v,DataBaseHelper.ID_JUEGO+"="+j.getId(),null);
        return resp;
    }


    private Juego cursorJuego(Cursor c){
        Juego j=new Juego();

        j.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_JUEGO)));
        j.setFecha_juego(c.getString(c.getColumnIndex(DataBaseHelper.FECHA_JUEGO)));
        j.setNombre_juego(c.getString(c.getColumnIndex(DataBaseHelper.NOMBRE_JUEGO)));
        j.setPremio_mayor(c.getFloat(c.getColumnIndex(DataBaseHelper.PREMIO_MAYOR)));
        j.setEstado_juego(c.getString(c.getColumnIndex(DataBaseHelper.ESTADO_JUEGO)));
        j.setTipo_juego(c.getString(c.getColumnIndex(DataBaseHelper.TIPO_JUEGO)));
        j.setCuota(c.getFloat(c.getColumnIndex(DataBaseHelper.CUOTA)));
        return j;
    }


    public ArrayList<Juego> juegosList(){
        ArrayList<Juego> lista = new ArrayList<>();
        Cursor c = null;
        try{
            String where= DataBaseHelper.ESTADO_JUEGO+"=1";
            c=database.query(DataBaseHelper.TBL_JUEGO,allColumns,where,null,null,null,null);
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

    public Juego juegoByNombre(String titulo){
        Juego juego = new Juego();
        Cursor c = null;
        try{
            String where= DataBaseHelper.ESTADO_JUEGO+"=1 and "+DataBaseHelper.NOMBRE_JUEGO+"='"+titulo+"'";
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


    public int getLast(){
        int id =0;
        Cursor c = null;
        try{

            String query = "SELECT last_insert_rowid() FROM "+DataBaseHelper.TBL_JUEGO+" LIMIT 1;";
            c = database.rawQuery(query, null);
            if(c.getCount()>0){
                c.moveToFirst();
                id=c.getInt(c.getInt(0));
            }
        }catch(SQLException ex){
            id=0;
        }
        return id;
    }


}
