package com.example.uniup.uniup.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.uniup.uniup.models.Ramo;
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
    public void eliminarRamo(long idSemestre,int idRamo) {
       this.openWriteableDB();
        if (db != null) {
        /*ContentValues cv = new ContentValues();
        cv.put(ConstantsDB.ID_SEMESTRE, idSemestre);
        cv.put(ConstantsDB.ID_RAMO, idRamo);*/
            db.delete("RAMOSDELSEMESTRE","ID_SEMESTRE='"+idSemestre+"'and ID_RAMO='"+idRamo+"'",null);
       //    db.execSQL("DELETE FROM 'RAMOSDELSEMESTRE' WHERE 'ID_SEMESTRE=idSemestre?' and 'ID_RAMO=idRamo?'");
        }

       // db.delete("RamosDelSemestre","ID_RAMO=idRamo",null);
     //   long rowID = db.delete(ConstantsDB.TABLA_RAMOSDELSEMESTRE,null,cv);
        this.closeDB();
      //  Log.i(TAG,"Ramo eliminado en semestre");

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
            Log.i(TAG,"Semestre recuperado");
        }
        this.closeDB();

        return listaSemestres;
    }

    public ArrayList consultarRamosPorSemestre(String id_semestre) {
        this.openReadableDB();
        Log.i(TAG,id_semestre);
        Ramo ramo;
        ArrayList<Ramo> listaRamos = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsDB.TABLA_RAMO +
                " INNER JOIN " + ConstantsDB.TABLA_RAMOSDELSEMESTRE +
                " ON " + ConstantsDB.TABLA_RAMOSDELSEMESTRE +"."+ConstantsDB.ID_RAMO+ " = "+
                ConstantsDB.TABLA_RAMO+"."+ConstantsDB.ID_RAMO +
                " WHERE " + ConstantsDB.TABLA_RAMOSDELSEMESTRE+"."+ConstantsDB.ID_SEMESTRE + " =?",new String[]{id_semestre});

        while (cursor.moveToNext()){
            ramo = new Ramo();
            ramo.setId(cursor.getInt(0));
            ramo.setName(cursor.getString(1));

            listaRamos.add(ramo);
            Log.i(TAG,"Ramo mostrado en semestre");
        }
        this.closeDB();

        return listaRamos;
    }

    public ArrayList consultarHorarios() {
        this.openReadableDB();

        Semestre semestre;
        ArrayList<Semestre> listaSemestres = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ConstantsDB.TABLA_SEMANA,null);

        while (cursor.moveToNext()){
            semestre = new Semestre();
            semestre.setId(cursor.getInt(0));
            semestre.setNombre(cursor.getString(1));

            listaSemestres.add(semestre);
            Log.i(TAG,"Semestre recuperado");
        }
        this.closeDB();

        return listaSemestres;
    }

}
