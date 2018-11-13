package com.example.uniup.uniup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.db.SemestreDB;
import com.example.uniup.uniup.models.Semestre;

import java.util.ArrayList;

public class MiSemestreActivity extends AppCompatActivity {

    ListView listViewSemestre;
    ArrayList<String> listaInformacion;

    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mdb;
    ArrayList<Semestre> listaSemestres;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_semestres);


        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        mDBHelper = new DataBaseHelper(this);

        SemestreDB dbSemestre = new SemestreDB(mDBHelper);
        listaSemestres = dbSemestre.consultarListaSemestre();
        ArrayList<String> nombresSemestres = new ArrayList<>();

        for (int i = 0; i < listaSemestres.size(); i++) {
            nombresSemestres.add(listaSemestres.get(i).getNombre());
        }

        listViewSemestre = (ListView) findViewById(R.id.listaS);

        ArrayAdapter adaptador = new ArrayAdapter(this, R.layout.list_item, nombresSemestres);
        listViewSemestre.setAdapter(adaptador);

        SharedPreferences prefs = getSharedPreferences("semestre", MODE_PRIVATE);
        final String career = prefs.getString("semestre", "");
        final int id_career = prefs.getInt("id", 0);

        listViewSemestre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String informacion = listaSemestres.get(pos).getNombre();
                int id_carrera = listaSemestres.get(pos).getId();

                SharedPreferences prefs = getSharedPreferences("semestre", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("semestre", informacion);
                editor.putInt("id", id_carrera);
                editor.apply();


                final String c = prefs.getString("semestre", "");
                final int i = prefs.getInt("id", 0);

                Intent intent = new Intent(MiSemestreActivity.this, MainActivity.class);

                startActivity(intent);

            }
        });

    }

}
