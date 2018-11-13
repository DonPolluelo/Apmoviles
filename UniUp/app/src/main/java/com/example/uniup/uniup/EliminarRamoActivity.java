package com.example.uniup.uniup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uniup.uniup.adapters.SeleccionarRamosAdapter;
import com.example.uniup.uniup.adapters.SemestreAdapter;
import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.db.SemestreDB;
import com.example.uniup.uniup.models.Ramo;
import com.example.uniup.uniup.models.Semestre;

import java.util.ArrayList;

public class EliminarRamoActivity extends AppCompatActivity {

    private EditText nombre;
    private View view;
    private ArrayAdapter adapter;
    private ArrayList<Ramo> listaRamos;
    private SemestreDB dbSemestre;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar_ramo);
        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView lv = (ListView) findViewById(R.id.eliminar_ramo_lv);

        SharedPreferences prefs = getSharedPreferences("semestre", MODE_PRIVATE);
        final String semestre = prefs.getString("semestre", "");
        final int id_semestre = prefs.getInt("id", 0);

        //cargar datos
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        dbSemestre = new SemestreDB(dbHelper);
        listaRamos=dbSemestre.consultarRamosPorSemestre(Integer.toString(id_semestre));

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

    public void eliminarRamo(View view){
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SemestreDB db = new SemestreDB(dbHelper);
        SharedPreferences prefs = getSharedPreferences("semestre", MODE_PRIVATE);
        final int idSemestre = prefs.getInt("id", 0);
        Ramo ramo;
        ArrayList<Integer> listaRamosEliminados = new ArrayList<Integer>();
        if (listaRamos.size()==0) {
            Toast.makeText(this, "No hay asignaturas para eliminar", Toast.LENGTH_SHORT).show();}
            else {
                for (int i = 0; i < listaRamos.size(); i++) {
                    ramo = listaRamos.get(i);
                    if (ramo.isCheck()) {
                        listaRamosEliminados.add(ramo.getId());
                        db.eliminarRamo(idSemestre, ramo.getId());
                    }
                }

                if (listaRamosEliminados.size() == 0) {
                    Toast.makeText(this, "No se eliminaron asignaturas", Toast.LENGTH_SHORT).show();
                }
                if (listaRamosEliminados.size() == 1) {
                    Toast.makeText(this, "1 Asignatura Eliminada", Toast.LENGTH_SHORT).show();
                }
                if (listaRamosEliminados.size() > 1) {
                    Toast.makeText(this, listaRamosEliminados.size() + " Asignaturas Eliminadas", Toast.LENGTH_SHORT).show();
                }
        }


        Intent Inicio = new Intent(this, MainActivity.class);
        startActivity(Inicio);
    }
    }