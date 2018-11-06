package com.example.uniup.uniup;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.models.Carrera;
import com.example.uniup.uniup.models.Ramo;
import com.example.uniup.uniup.models.RamoPorCarrera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class MallaFragment extends Fragment {

    View view;

    ListView listViewRamos;
    ArrayList<RamoPorCarrera> listaRamo;
    ArrayList<String> listaInformacion;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mdb;


    ArrayList<String> listadeprueba;



    public MallaFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences prefs = this.getActivity().getSharedPreferences("carrera", MODE_PRIVATE);
        final String i = prefs.getString("carrera", "");


        view = inflater.inflate(R.layout.malla_fragment, container, false);

        mDBHelper = new DataBaseHelper(getActivity());

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


        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());



        Toast.makeText(getActivity(),"Seleccionaste " + i ,Toast.LENGTH_LONG).show();

        consultarListaRamos(mdb);

        listViewRamos = (ListView) view.findViewById(R.id.ramolist);

        ArrayAdapter adaptador = new ArrayAdapter(getActivity(), R.layout.list_item, listaInformacion);
        listViewRamos.setAdapter(adaptador);



        return view;
    }








   private void consultarListaRamos(SQLiteDatabase db) {

        SharedPreferences prefs = getActivity().getSharedPreferences("carrera", MODE_PRIVATE);
        final int id_carrera = prefs.getInt("id", 0);

       //int id_carrera = 1;


        RamoPorCarrera ramoxcarrera;
        listaRamo=new ArrayList<RamoPorCarrera>();
        listadeprueba = new ArrayList<String>();
        Cursor cursor;

        cursor=db.rawQuery("SELECT * FROM `RamosPorCarrera` WHERE `id_carrera` = ?", new String[] {String.valueOf(id_carrera)});


        while (cursor.moveToNext()){
            ramoxcarrera=new RamoPorCarrera();
            ramoxcarrera.setId_carrera(cursor.getInt(0));
            ramoxcarrera.setId_ramo(cursor.getInt(1));
            listaRamo.add(ramoxcarrera);


            listadeprueba.add(String.valueOf(cursor.getInt(0)));


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



