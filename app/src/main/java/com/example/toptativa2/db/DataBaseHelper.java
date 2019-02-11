package com.example.toptativa2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TBL_USER ="user";
    public static final String ID_USER = "id";
    public static final String FULLNAME = "fullname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER_TYPE = "user_type";
    public static final String METHOD = "sus_method";

    public static final String DATABASE_NAME = "eurekapp";
    public static final int DATABASE_VERSION  =1;

    private static final String CREATE_USER = "CREATE TABLE "+TBL_USER+"("+ID_USER+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FULLNAME+" VARCHAR(50), "+
                                              EMAIL+" VARCHAR(50), "+PASSWORD+" VARCHAR(15), "+
                                              USER_TYPE+" CHAR(3),"+METHOD+" INTEGER );";



    public DataBaseHelper(Context c ){
        super(c,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TBL_USER);
    }
}
