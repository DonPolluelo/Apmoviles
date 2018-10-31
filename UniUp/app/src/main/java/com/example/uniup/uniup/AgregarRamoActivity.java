package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.models.Ramo;

public class AgregarRamoActivity extends AppCompatActivity {

    EditText nombre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_ramo);

        nombre = (EditText) findViewById(R.id.nombre_ramo);
    }

    public void agregarRamo(View view){
        RamoDB db = new RamoDB(this);
        Ramo ramo = new Ramo(nombre.getText().toString());
        db.insertRamo(ramo);
        Intent intent = new Intent(AgregarRamoActivity.this, LinksFragment.class);
        startActivity(intent);
    }
}
