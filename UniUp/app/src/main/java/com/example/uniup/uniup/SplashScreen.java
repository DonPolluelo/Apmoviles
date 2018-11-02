package com.example.uniup.uniup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicio);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean usm = prefs.getBoolean("usm", false);
        boolean pucv = prefs.getBoolean("pucv", false);


        if (!usm && !pucv){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreen.this, MenuUniversidadesListViewActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, 3000);
        }

        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, 3000);
        }


    }


}
