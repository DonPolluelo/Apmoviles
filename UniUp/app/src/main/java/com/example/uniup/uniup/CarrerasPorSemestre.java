package com.example.uniup.uniup;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.uniup.uniup.R;
import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.models.Ramo;
import com.example.uniup.uniup.models.RamoPorCarrera;

import java.io.IOException;
import java.util.ArrayList;

public class CarrerasPorSemestre extends AppCompatActivity {

    ListView listViewRamos;
    ArrayList<RamoPorCarrera> listaRamo;
    ArrayList<String> listaInformacion;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrera_semestre_listview);

        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        mDBHelper = new DataBaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mdb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        listViewRamos= (ListView) findViewById(R.id.listaSemestre);

        consultarListaRamos(mdb);

        ArrayAdapter adaptador = new ArrayAdapter(this,R.layout.list_item,listaInformacion);
        listViewRamos.setAdapter(adaptador);


    }


    private void consultarListaRamos(SQLiteDatabase db) {

        SharedPreferences prefs = getSharedPreferences("carrera", MODE_PRIVATE);
        final int id_carrera = prefs.getInt("id", 0);
        final int semestre = prefs.getInt("semestre", 0);



        RamoPorCarrera ramoxcarrera;
        listaRamo=new ArrayList<RamoPorCarrera>();
        Cursor cursor;

        cursor=db.rawQuery("SELECT * FROM `RamosPorCarrera` WHERE `id_carrera` = ? AND semestre = ?", new String[] {String.valueOf(id_carrera), String.valueOf(semestre)});


        while (cursor.moveToNext()){
            ramoxcarrera=new RamoPorCarrera();
            ramoxcarrera.setId_carrera(cursor.getInt(0));
            ramoxcarrera.setId_ramo(cursor.getInt(1));
            ramoxcarrera.setSemestre(cursor.getInt(2));
            listaRamo.add(ramoxcarrera);




        }
        obtenerLista();
    }



    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();
        Cursor ramo_cursor;
        Ramo ramo = null;

        for (int i=0; i<listaRamo.size();i++) {
            int id_ramo = listaRamo.get(i).getId_ramo();

            ramo_cursor = mdb.rawQuery("SELECT * FROM `Ramo` WHERE `id_ramo` = ?", new String[] {String.valueOf(id_ramo)});

            while (ramo_cursor.moveToNext()) {
                ramo = new Ramo();
                ramo.setId(ramo_cursor.getInt(0));
                ramo.setName(ramo_cursor.getString(1));


                listaInformacion.add(ramo.getName());
            }

        }

    }
}
