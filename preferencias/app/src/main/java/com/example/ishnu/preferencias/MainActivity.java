package com.example.ishnu.preferencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_horario:
                // Agregar actividad editar horario
                break;
            case R.id.action_semestre:
                // Agregar actividad editar mi semestre
                break;
            case R.id.action_settings:
                // Agregar actividad editar mis preferencias (universidad  y carrera)
                break;
            default:
                // esto no deberia pasar hehhehehe
        }
        return super.onOptionsItemSelected(item);
    }
}
