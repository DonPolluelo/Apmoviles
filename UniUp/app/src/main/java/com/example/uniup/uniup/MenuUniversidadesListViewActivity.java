package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MenuUniversidadesListViewActivity extends AppCompatActivity {

    ListView listaUniversidades;
    String[] universidades = new String[]{"Universidad Técnica Federico Santa María", "Unversidad Católica de Valparaíso", "Universidad de Chile",
            "Universidad Católica de Chile", "Universidad de Concepción", "Unversidad de Valparaíso",
            "Universidad de Playa Ancha", "Universidad Andrés Bello", "Universidad de Santiago",
            "Unversidad de Viña del Mar", "Universidad Adolfo Ibañez", "Universidad Autónoma",
            "Universidad Católica del Maule", "Unversidad de La Serena", "INACAP", "DUOC UC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_universidad_listview);

        //List View de Universidades
        listaUniversidades= (ListView) findViewById(R.id.listaU);

        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,universidades);

        listaUniversidades.setAdapter(adapter);
        listaUniversidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String proximamente = getResources().getString(R.string.mensaje_proximamente);
                String select = getResources().getString(R.string.mensaje_seleccionaste);


                if (position==0){
                    Intent Inicio = new Intent(MenuUniversidadesListViewActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" UTFSM", Toast.LENGTH_LONG).show();
                }
                if (position==1){
                    Intent Inicio = new Intent(MenuUniversidadesListViewActivity.this, MainActivity.class);

                    String university = "pucv";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" PUCV", Toast.LENGTH_LONG).show();

                }
                if (position!=1 & position!=0){
                    Toast.makeText(getApplicationContext(),proximamente, Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
