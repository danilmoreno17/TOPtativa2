package com.example.toptativa2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "eurekapp";
    public static final int DATABASE_VERSION  =2;


    public static final String TBL_USER ="user";
    public static final String ID_USER = "id";
    public static final String FULLNAME = "fullname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER_TYPE = "user_type";
    public static final String METHOD = "sus_method";
    public static final String ACTIVE = "active";


    private static final String CREATE_USER = "CREATE TABLE "+TBL_USER+"("+ID_USER+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FULLNAME+" VARCHAR(50), "+
                                              EMAIL+" VARCHAR(50), "+PASSWORD+" VARCHAR(15), "+
                                              USER_TYPE+" CHAR(3),"+METHOD+" INTEGER, "+ACTIVE+" INTEGER );";


    public static final String TBL_JUEGO="juego";
    public static final String ID_JUEGO = "id";
    public static final String FECHA_JUEGO="fecha_juego";
    public static final String NOMBRE_JUEGO="nombre_juego";
    public static final String PREMIO_MAYOR="premio_mayor";
    public static final String ESTADO_JUEGO="estado_juego";
    public static final String TIPO_JUEGO="tipo_juego";
    public static final String NOMBRE_PREMIO="nombre_premio";
    public static final String CUOTA = "cuota";


    private static final String CREATE_JUEGO = "CREATE TABLE "+TBL_JUEGO+"("+ID_JUEGO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            FECHA_JUEGO+" VARCHAR(50), "+CUOTA+" REAL,"+
            NOMBRE_JUEGO+" VARCHAR(50), "+PREMIO_MAYOR+" REAL, "+
            NOMBRE_PREMIO+" VARCHAR(50), "+
            ESTADO_JUEGO+" CHAR(3),"+TIPO_JUEGO+" CHAR(3) );";


    public static final String TBL_PUBLICACION = "publicacion";
    public static final String ID_PUBLICACION="id";
    public static final String ID_USUARIO_PUBLICACION="id_usuario";
    public static final String ID_JUEGO_PUBLICACION="id_juego";
    public static final String NUMERO_PREMIADO="numero_premiado";
    public static final String ESTADO_PUBLICACION="estado";
    public static final String FECHA_PUBLICACION="fecha_publicacion";
    public static final String FECHA_TOPE =  "fecha_tope";

    private static final String CREATE_PUBLICACION ="CREATE TABLE "+TBL_PUBLICACION+" ("+ID_PUBLICACION+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID_USUARIO_PUBLICACION+" INTEGER,"+ID_JUEGO_PUBLICACION+" INTEGER,"+
            ESTADO_PUBLICACION+" CHAR(3),"+FECHA_PUBLICACION+" VARCHAR(50),"+
            NUMERO_PREMIADO+" VARCHAR(25),"+FECHA_TOPE+" VARCHAR(50));";


    public static final String TBL_DETALLES_JUEGO="detalles_juego";
    public static final String ID_DETALLE_JUEGO="id";
    public static final String ID_JUEGO_DETALLE_JUEGO="id_juego";
    public static final String PREMIO_EFECTIVO ="premio_efectivo";
    public static final String PREMIO_NOMBRE="premio_nombre";
    public static final String NUM_GANADOR ="num_ganador";

    private static final String CREATE_DETALLE_JUEGO = "CREATE TABLE "+TBL_DETALLES_JUEGO+" ("+ID_DETALLE_JUEGO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ID_JUEGO_DETALLE_JUEGO+" INTEGER,"+PREMIO_EFECTIVO+" REAL,"+
            PREMIO_NOMBRE+" VARCHAR(50),"+NUM_GANADOR+" VARCHAR(30));";

    public static final String TBL_PREMIACION = "premiacion";
    public static final String ID_PREMIACION="id";
    public static final String ID_PREMIACION_USUARIO ="id_usuario";
    public static final String ID_PREMIACION_PUBLICACION="id_publicacion";
    public static final String NUM_JUEGO="num_juego";
    public static final String ESTADO_NUMERO="estado_numero";

    private static final String CREATE_PREMIACION ="CREATE TABLE "+TBL_PREMIACION+" ("+ID_PREMIACION+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            ID_PREMIACION_PUBLICACION+" INTEGER,"+ID_PREMIACION_USUARIO+" INTEGER,"+NUM_JUEGO+" VARCHAR(30), "+ESTADO_NUMERO+" INTEGER DEFAULT 0);";


    public DataBaseHelper(Context c ){
        super(c,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_JUEGO);
        db.execSQL(CREATE_DETALLE_JUEGO);
        db.execSQL(CREATE_PUBLICACION);
        db.execSQL(CREATE_PREMIACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TBL_PREMIACION);
        db.execSQL("DROP TABLE IF EXISTS "+TBL_PUBLICACION);
        db.execSQL("DROP TABLE IF EXISTS "+TBL_DETALLES_JUEGO);
        db.execSQL("DROP TABLE IF EXISTS "+TBL_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TBL_JUEGO);
        onCreate(db);
    }
}
