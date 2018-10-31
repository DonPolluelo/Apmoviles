package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MenuUniversidadesListViewActivity extends AppCompatActivity {

    ListView listaUniversidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_universidad_listview);
        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);


        Universidades universidades_data[] = new Universidades[]{
                new Universidades("Universidad Técnica Federico Santa María"),
                new Universidades("Unversidad Católica de Valparaíso"),
                new Universidades("Universidad de Chile"),
                new Universidades("Universidad Católica"),
                new Universidades("Universidad de Santiago"),
                new Universidades("Universidad de Concepción"),
                new Universidades("Universidad Adolfo Ibañez"),
                new Universidades("Universidad Andrés Bello"),
                new Universidades("Universidad de Valparaíso"),
                new Universidades("Universidad de Playa Ancha"),
                new Universidades("Universidad Católica del Maule"),
                new Universidades("Universidad de La Serena"),
                new Universidades("Universidad Autónoma"),
                new Universidades("Universidad de Viña del Mar"),
                new Universidades("INACAP"),
                new Universidades("Duoc UC"),
        };

        UniversidadesAdapter adapter = new UniversidadesAdapter(this,R.layout.list_item,universidades_data);

        listaUniversidades= (ListView) findViewById(R.id.listaU);
        listaUniversidades.setAdapter(adapter);

        listaUniversidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String proximamente = getResources().getString(R.string.mensaje_proximamente);
                String select = getResources().getString(R.string.mensaje_seleccionaste);


                if (position==0){
                    Intent Inicio = new Intent(MenuUniversidadesListViewActivity.this, CarrerasUSMActivity.class);

                /*String university = "usm";
                    Inicio.putExtra("uni", university);*/

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
