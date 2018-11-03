package com.example.uniup.uniup;

import android.content.SharedPreferences;
import android.database.AbstractCursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.models.Carrera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Carreras extends AppCompatActivity {


        ListView listViewCarreras;
        ArrayList<String> listaInformacion;
        ArrayList<Carrera> listaCarrera;

        private DataBaseHelper mDBHelper;
        private SQLiteDatabase mdb;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.menu_carrera_listview);

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

            listViewCarreras= (ListView) findViewById(R.id.listaC);

            consultarListaCarreras(mdb);

            ArrayAdapter adaptador = new ArrayAdapter(this,R.layout.list_item,listaInformacion);
            listViewCarreras.setAdapter(adaptador);

            listViewCarreras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                    Intent intent=new Intent(Carreras.this,MainActivity.class);

                    startActivity(intent);

                }
            });

        }

        private void consultarListaCarreras(SQLiteDatabase db) {

            //db = conn.getWritableDatabase();

            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            boolean usm = prefs.getBoolean("usm", false);
            boolean pucv = prefs.getBoolean("pucv", false);

            Carrera carrera=null;
            listaCarrera=new ArrayList<Carrera>();
            Cursor cursor;

            if (usm) {

                cursor=db.rawQuery("SELECT * FROM `Carrera` WHERE `id_universidad` = 1",null);

            }

            else {

                cursor=db.rawQuery("SELECT * FROM `Carrera` WHERE `id_universidad` = 2",null);
            }


            while (cursor.moveToNext()){
                carrera=new Carrera();
                carrera.setId(cursor.getInt(0));
                carrera.setNombre(cursor.getString(1));
                carrera.setId_universidad(cursor.getInt(2));

                listaCarrera.add(carrera);
            }
            obtenerLista();
        }





        private void obtenerLista() {
            listaInformacion=new ArrayList<String>();

            for (int i=0; i<listaCarrera.size();i++){
                listaInformacion.add(listaCarrera.get(i).getNombre());
            }
            Collections.sort(listaInformacion);

        }

    }