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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        String university = intent.getStringExtra("uni");
        String career = intent.getStringExtra("car");
        Bundle bundle = new Bundle();
        bundle.putString("uni", university);
        bundle.putString("car",career);
        LinksFragment fragobj = new LinksFragment();
        fragobj.setArguments(bundle);

        String horario = getResources().getString(R.string.horario);
        String malla = getResources().getString(R.string.malla);
        String semestre = getResources().getString(R.string.semestre);


        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewpager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new HorarioFragment(), horario);
        adapter.AddFragment(new MallaFragment(), malla);
        adapter.AddFragment(new SemestreFragment(),semestre);
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
                Intent universidad = new Intent(this, MenuUniversidadesListViewActivity.class);
                startActivity(universidad);
                break;
            case R.id.action_carrera:
                Intent carrera = new Intent(this, CarrerasUSMActivity.class);
                startActivity(carrera);
                break;
            default:
                // esto no deberia pasar hehhehehe
        }
        return super.onOptionsItemSelected(item);
    }

}
