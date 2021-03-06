package com.example.uniup.uniup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uniup.uniup.adapters.SeleccionarRamosAdapter;
import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.db.SemestreDB;
import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;

public class AgregarRamoActivity extends AppCompatActivity {

    private EditText nombre;
    private View view;
    private ArrayAdapter adapter;
    private ArrayList<Ramo> listaRamos;
    private SemestreDB dbSemestre;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_ramo);
        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs = getSharedPreferences("carrera", MODE_PRIVATE);
        final int id_career = prefs.getInt("id", 0);

        ListView lv = (ListView) findViewById(R.id.agregar_ramo_lv);

        //cargar datos
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        RamoDB db = new RamoDB(dbHelper);
        listaRamos = db.consultarListaRamos(Integer.toString(id_career));
        final SeleccionarRamosAdapter adapter = new SeleccionarRamosAdapter(this,listaRamos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ramo ramo = listaRamos.get(position);
                if (ramo.isCheck()){
                    ramo.setCheck(false);
                } else {
                    ramo.setCheck(true);
                }
                listaRamos.set(position,ramo);

                adapter.updateRecords(listaRamos);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void agregarRamo(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SemestreDB db = new SemestreDB(dbHelper);
        SharedPreferences prefs = getSharedPreferences("semestre", MODE_PRIVATE);
        final int idSemestre = prefs.getInt("id", 0);
        Ramo ramo;
        ArrayList<Integer> listaRamosAgregados = new ArrayList<Integer>();

        for (int i = 0; i < listaRamos.size();i++) {
            ramo = listaRamos.get(i);
            if (ramo.isCheck()) {
                listaRamosAgregados.add(ramo.getId());
                db.insertarRamo(idSemestre, ramo.getId());
                }
            //Toast.makeText(this,"Asignaturas añadidas",Toast.LENGTH_SHORT).show();
          }

        if (listaRamosAgregados.size() == 0) {
            Toast.makeText(this, "No se agregaron asignaturas", Toast.LENGTH_SHORT).show();
        }
        if (listaRamosAgregados.size() == 1) {
            Toast.makeText(this, "1 Asignatura Agregada", Toast.LENGTH_SHORT).show();
        }
        if (listaRamosAgregados.size() > 1) {
            Toast.makeText(this, listaRamosAgregados.size() + " Asignaturas Agregadas", Toast.LENGTH_SHORT).show();
        }
        Intent Inicio = new Intent(this, MainActivity.class);
        startActivity(Inicio);
    }
    }