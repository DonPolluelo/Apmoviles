package com.example.uniup.uniup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.uniup.uniup.models.Ramo;

public class ListaNotasActivity extends AppCompatActivity {

    TextView campoNombre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_notas);

        campoNombre = (TextView) findViewById(R.id.nombre);

        Bundle objetoEnviado = getIntent().getExtras();
        Ramo ramo;

        if (objetoEnviado!=null){
            ramo = (Ramo) objetoEnviado.getSerializable("ramo");
            campoNombre.setText(ramo.getName());
        }
    }
}
