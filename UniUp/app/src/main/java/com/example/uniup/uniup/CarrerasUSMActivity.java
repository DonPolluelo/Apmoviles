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

public class CarrerasUSMActivity extends AppCompatActivity {

    ListView listaCarreras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_carrera_listview);
        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);


        Carreras carreras_data[] = new Carreras[]{
                new Carreras("Ingeniería Civil Informática"),
                new Carreras("Ingeniería Civil Industrial"),
                new Carreras("Ingeniería Civil Mecánica"),
                new Carreras("Ingeniería Comercial"),
                new Carreras("Ingeniería Civil Ambiental"),
                new Carreras("Ingeniería Civil"),
        };

        CarrerasAdapter adapter = new CarrerasAdapter(this,R.layout.list_item,carreras_data);

        listaCarreras= (ListView) findViewById(R.id.listaC);
        listaCarreras.setAdapter(adapter);

        listaCarreras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String proximamente = getResources().getString(R.string.mensaje_proximamente);
                String select = getResources().getString(R.string.mensaje_seleccionaste);


                if (position==0){
                    Intent Inicio = new Intent(CarrerasUSMActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" Ingeniería Civil Informática", Toast.LENGTH_LONG).show();
                }
                if (position==1){
                    Intent Inicio = new Intent(CarrerasUSMActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" Ingeniería Civil Industrial", Toast.LENGTH_LONG).show();

                }
                if (position==2){
                    Intent Inicio = new Intent(CarrerasUSMActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" Ingeniería Civil Mecánica", Toast.LENGTH_LONG).show();

                }
                if (position==3){
                    Intent Inicio = new Intent(CarrerasUSMActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" Ingeniería Comercial", Toast.LENGTH_LONG).show();

                }
                if (position==4){
                    Intent Inicio = new Intent(CarrerasUSMActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" Ingeniería Civil Ambiental", Toast.LENGTH_LONG).show();

                }
                if (position==5){
                    Intent Inicio = new Intent(CarrerasUSMActivity.this, MainActivity.class);

                    String university = "usm";
                    Inicio.putExtra("uni", university);

                    startActivity(Inicio);
                    Toast.makeText(getApplicationContext(),select+" Ingeniería Civil", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

}
