package com.example.uniup.uniup.db;

public class ConstantsDB {
    //General
    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;


    //TABLA RAMO
    public static final String TABLA_RAMO = "ramo";

    public static final String RAMO_ID = "id";
    public static final String RAMO_NOMBRE = "nombre";

    public static final String TABLA_RAMO_SQL =
            "CREATE TABLE  " + TABLA_RAMO + "(" +
                    RAMO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RAMO_NOMBRE + " TEXT NOT NULL)";

    //Otras tablas
}
