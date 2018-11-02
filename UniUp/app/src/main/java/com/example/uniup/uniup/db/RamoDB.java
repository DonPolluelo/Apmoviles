package com.example.uniup.uniup.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;

public class RamoDB {
    private static final String TAG = "RamoDB";
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public RamoDB(DataBaseHelper dbHelper) {
        this.dbHelper = dbHelper;
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

    public long insertRamo(Ramo ramo) {
        this.openWriteableDB();
        long rowID = db.insert(ConstantsDB.TABLA_RAMO, null, clienteMapperContentValues(ramo));
        this.closeDB();
        Log.i(TAG,"Ramo insertado");
        return rowID;
    }

    public ArrayList consultarListaRamos() {
        this.openReadableDB();

        Ramo ramo;
        ArrayList<Ramo> listaRamos = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsDB.TABLA_RAMO,null);

        while (cursor.moveToNext()){
            ramo = new Ramo();
            ramo.setId(cursor.getInt(0));
            ramo.setName(cursor.getString(1));

            listaRamos.add(ramo);
        }
        this.closeDB();

        return listaRamos;
    }

    private ContentValues clienteMapperContentValues(Ramo ramo) {
        ContentValues cv = new ContentValues();
        cv.put(ConstantsDB.NOMBRE_RAMO, ramo.getName());
        return cv;
    }
}
