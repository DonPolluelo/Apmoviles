package com.example.uniup.uniup.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.uniup.uniup.models.Semestre;

import java.util.ArrayList;


public class SemestreDB {
    private static final String TAG = "SemestreDB";
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public SemestreDB(DataBaseHelper dbHelper) {
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

    public void actualizarUsuario(Semestre semestre){
        this.openWriteableDB();
        String[] parametros={Integer.toString(semestre.getId())};
        ContentValues values = new ContentValues();
        values.put(ConstantsDB.NOMBRE_SEMESTRE,semestre.getNombre());

        db.update(ConstantsDB.TABLA_SEMESTRE,values,ConstantsDB.ID_SEMESTRE+"=?",parametros);
    }
    public long insertarSemestre(Semestre semestre) {
        this.openWriteableDB();
        long rowID = db.insert(ConstantsDB.TABLA_SEMESTRE, null, semestreMapperContentValues(semestre));
        this.closeDB();
        Log.i(TAG,"Semestre insertado");
        return rowID;
    }
    public long insertarRamo(long idSemestre,int idRamo) {
        this.openWriteableDB();
        ContentValues cv = new ContentValues();
        cv.put(ConstantsDB.ID_SEMESTRE, idSemestre);
        cv.put(ConstantsDB.ID_RAMO, idRamo);
        long rowID = db.insert(ConstantsDB.TABLA_RAMOSDELSEMESTRE, null, cv);
        this.closeDB();
        Log.i(TAG,"Ramo insertado en semestre");
        return rowID;
    }
    private ContentValues semestreMapperContentValues(Semestre semestre) {
        ContentValues cv = new ContentValues();
        cv.put(ConstantsDB.NOMBRE_SEMESTRE, semestre.getNombre());
        return cv;
    }

    public ArrayList consultarListaSemestre() {
        this.openReadableDB();

        Semestre semestre;
        ArrayList<Semestre> listaSemestres = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsDB.TABLA_SEMESTRE,null);

        while (cursor.moveToNext()){
            semestre = new Semestre();
            semestre.setId(cursor.getInt(0));
            semestre.setNombre(cursor.getString(1));

            listaSemestres.add(semestre);
        }
        this.closeDB();

        return listaSemestres;
    }
}
