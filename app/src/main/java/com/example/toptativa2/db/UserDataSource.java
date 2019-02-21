package com.example.toptativa2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.toptativa2.Models.User;

public class UserDataSource {

    public String [] allColumns={
            DataBaseHelper.ID_USER,
            DataBaseHelper.FULLNAME,
            DataBaseHelper.EMAIL,
            DataBaseHelper.PASSWORD,
            DataBaseHelper.USER_TYPE,
            DataBaseHelper.METHOD,
            DataBaseHelper.ACTIVE
    };

    private SQLiteDatabase database;
    private DataBaseHelper dbhelper;

    public UserDataSource(Context c){
        dbhelper = new DataBaseHelper(c);
    }

    public void open(){
        database=dbhelper.getReadableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    private ContentValues userValues(User u){
        ContentValues v = new ContentValues();
        if(!u.getFullname().toString().equals(""))
            v.put(DataBaseHelper.FULLNAME,u.getFullname());
        if(!u.getPassword().toString().equals(""))
            v.put(DataBaseHelper.PASSWORD,u.getPassword());
        if(!u.getEmail().toString().equals(""))
            v.put(DataBaseHelper.EMAIL,u.getEmail());
        v.put(DataBaseHelper.METHOD,u.getSus_method());
        v.put(DataBaseHelper.USER_TYPE,u.getUser_type());
        v.put(DataBaseHelper.ACTIVE,u.getActive());


        return v;
    }


    private ContentValues suscriptionValues(User u){
        ContentValues v = new ContentValues();
        v.put(DataBaseHelper.METHOD,u.getSus_method());

        return v;
    }


    public long insert(User u){
        ContentValues v = userValues(u);
        long resp = database.insert(DataBaseHelper.TBL_USER,null,v);
        return resp;
    }

    public long updateSuscription(User u){
        ContentValues v =suscriptionValues(u);
        long resp = database.update(DataBaseHelper.TBL_USER,v,DataBaseHelper.ID_USER+"="+u.getId(),null);
        return resp;
    }


    public User getLoginUser(String email){
        User u = null;
        Cursor c =null;
        try {
            c =database.query(DataBaseHelper.TBL_USER,allColumns,DataBaseHelper.EMAIL+"='"+email+"'",null,null,null,null);
            if(c.getCount()>0){
                c.moveToFirst();
                u=new User();
                u.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_USER)));
                u.setFullname(c.getString(c.getColumnIndex(DataBaseHelper.FULLNAME)));
                u.setPassword(c.getString(c.getColumnIndex(DataBaseHelper.PASSWORD)));
                u.setEmail(c.getString(c.getColumnIndex(DataBaseHelper.EMAIL)));
                u.setSus_method(c.getInt(c.getColumnIndex(DataBaseHelper.METHOD)));
                u.setUser_type(c.getString(c.getColumnIndex(DataBaseHelper.USER_TYPE)));
                u.setActive(c.getInt(c.getColumnIndex(DataBaseHelper.ACTIVE)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }


    public User getUser(int id){
        User u = null;
        Cursor c =null;
        try {
            c =database.query(DataBaseHelper.TBL_USER,allColumns,DataBaseHelper.ID_USER+"="+id,null,null,null,null);
            if(c.getCount()>0){
                c.moveToFirst();
                u=new User();
                u.setId(c.getInt(c.getColumnIndex(DataBaseHelper.ID_USER)));
                u.setFullname(c.getString(c.getColumnIndex(DataBaseHelper.FULLNAME)));
                u.setEmail(c.getString(c.getColumnIndex(DataBaseHelper.EMAIL)));
                u.setSus_method(c.getInt(c.getColumnIndex(DataBaseHelper.METHOD)));
                u.setUser_type(c.getString(c.getColumnIndex(DataBaseHelper.USER_TYPE)));
                u.setActive(c.getInt(c.getColumnIndex(DataBaseHelper.ACTIVE)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }



}
