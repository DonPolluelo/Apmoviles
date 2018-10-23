package com.example.uniup.uniup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;

public class RamoDB {
    private static final String TAG = "RamoDB";
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public RamoDB(Context context) {
        dbHelper = new DBHelper(context);
    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if(db!=null){
            db.close();
        }
    }

    // CRUD

    public long insertRamo(Ramo ramo) {
        this.openWriteableDB();
        long rowID = db.insert(ConstantsDB.TABLA_RAMO, null, clienteMapperContentValues(ramo));
        this.closeDB();
        Log.i(TAG,"Ramo insertado");
        return rowID;
    }

    public ArrayList loadClientes() {

        ArrayList list = new ArrayList<>();

        this.openReadableDB();
        String[] campos = new String[]{ConstantsDB.RAMO_ID, ConstantsDB.RAMO_NOMBRE};
        Cursor c = db.query(ConstantsDB.TABLA_RAMO, campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                Ramo ramo = new Ramo();
                ramo.setId(c.getInt(0));
                ramo.setName(c.getString(1));
                list.add(ramo);
            }
        } finally {
            c.close();
        }
        this.closeDB();

        return list;
    }

    private ContentValues clienteMapperContentValues(Ramo ramo) {
        ContentValues cv = new ContentValues();
        cv.put(ConstantsDB.RAMO_NOMBRE, ramo.getName());
        return cv;
    }

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, ConstantsDB.DB_NAME, null, ConstantsDB.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ConstantsDB.TABLA_RAMO_SQL);
            Log.i(TAG, "Base de datos creada");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
