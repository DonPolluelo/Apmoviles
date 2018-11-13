package com.example.uniup.uniup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

public class ListaDesplegable extends AppCompatActivity {

    ExpandableListView listaexpandible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_desplegable_carreras);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        listaexpandible = (ExpandableListView) findViewById(R.id.expandable);

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(ListaDesplegable.this);

        listaexpandible.setAdapter(adapter);

    }
}
