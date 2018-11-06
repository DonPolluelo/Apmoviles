package com.example.uniup.uniup.db;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

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

    public ArrayList consultarListaRamos(String id_carrera) {

        this.openReadableDB();

        Ramo ramo;
        ArrayList<Ramo> listaRamos = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsDB.TABLA_RAMO +
                " INNER JOIN " + ConstantsDB.TABLA_RAMOSPORCARRERA +
                        " ON " + ConstantsDB.TABLA_RAMOSPORCARRERA +"."+ConstantsDB.ID_RAMO+ " = "+
                ConstantsDB.TABLA_RAMO+"."+ConstantsDB.ID_RAMO +
                " WHERE " + ConstantsDB.TABLA_RAMOSPORCARRERA+"."+ConstantsDB.ID_CARRERA + " =?",new String[]{id_carrera});

        while (cursor.moveToNext()){
            ramo = new Ramo();
            ramo.setId(cursor.getInt(0));
            ramo.setName(cursor.getString(1));
            ramo.setCheck(false);

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
