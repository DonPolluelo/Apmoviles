package com.example.uniup.uniup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.models.Ramo;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewpager;
    private RamoDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        //DB
        db = new RamoDB(this);

        Ramo ramo1 = new Ramo("Mate");
        Ramo ramo2 = new Ramo("Fisica");
        Ramo ramo3 = new Ramo("Quimica");
        Ramo ramo4 = new Ramo("Progra");

        db.insertRamo(ramo1);
        db.insertRamo(ramo2);
        db.insertRamo(ramo3);
        db.insertRamo(ramo4);


        Intent intent = getIntent();
        String university = intent.getStringExtra("uni");

        Bundle bundle = new Bundle();
        bundle.putString("uni", university);
        LinksFragment fragobj = new LinksFragment();
        fragobj.setArguments(bundle);



        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewpager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new HorarioFragment(), "Horario");
        adapter.AddFragment(new MallaFragment(), "Malla");
        adapter.AddFragment(new SemestreFragment(), "Semestre");
        //adapter.AddFragment(new LinksFragment(), "Links");
        adapter.AddFragment(fragobj, "Links");

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
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
            case R.id.action_universidad:
                Intent universidad = new Intent(this, MenuUniversidadActivity.class);
                startActivity(universidad);
                break;
            case R.id.action_carrera:
                Intent carrera = new Intent(this, MenuCarrerasActivity.class);
                startActivity(carrera);
                break;
            default:
                // esto no deberia pasar hehhehehe
        }
        return super.onOptionsItemSelected(item);
    }
}
