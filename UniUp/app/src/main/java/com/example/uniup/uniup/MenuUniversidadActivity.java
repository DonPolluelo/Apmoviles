package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class MenuUniversidadActivity extends AppCompatActivity {

    private ImageButton usm, pucv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_universidad);
        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);


        usm = (ImageButton) findViewById(R.id.usmbutton_id);
        pucv = (ImageButton) findViewById(R.id.pucvbutton_id);

        usm.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Inicio = new Intent(MenuUniversidadActivity.this, MainActivity.class);

                String university = "usm";
                Inicio.putExtra("uni", university);

                startActivity(Inicio);

            }

        });


        pucv.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Inicio = new Intent(MenuUniversidadActivity.this, MainActivity.class);

                String university = "pucv";
                Inicio.putExtra("uni", university);

                startActivity(Inicio);

            }

        });

    }

}
