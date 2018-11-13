package com.example.uniup.uniup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


public class MallaFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    ListView listViewSemestres;
    ArrayList<String> listaInformacion;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mdb;





    public MallaFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences prefs = this.getActivity().getSharedPreferences("carrera", MODE_PRIVATE);
        //final String i = prefs.getString("carrera", "");
        final int id_carrera = prefs.getInt("id", 0);
        final int semestre = prefs.getInt("semestre", 0);
        final int duracion = prefs.getInt("duracion", 0);


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



        listaInformacion = new ArrayList<>();

        for(int i = 0; i < duracion; i++ ){
            listaInformacion.add(getResources().getString(R.string.semestre)+" "+ String.valueOf(i + 1));
        }

        listViewSemestres = (ListView) view.findViewById(R.id.ramolist);

        ArrayAdapter adaptador = new ArrayAdapter(getActivity(), R.layout.list_item, listaInformacion);
        listViewSemestres.setAdapter(adaptador);
        listViewSemestres.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView parent, View view, int pos, long id) {

        SharedPreferences prefs = getActivity().getSharedPreferences("carrera", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("semestre", pos + 1);
        editor.apply();

        Intent intent = new Intent(getActivity(),CarrerasPorSemestre.class);
        startActivity(intent);
    }

}



