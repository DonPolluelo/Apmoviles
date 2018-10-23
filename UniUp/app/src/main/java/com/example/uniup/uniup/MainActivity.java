package com.example.uniup.uniup;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        db = new RamoDB(this);

        Ramo ramo1 = new Ramo("Mate");
        Ramo ramo2 = new Ramo("Fisica");
        Ramo ramo3 = new Ramo("Quimica");
        Ramo ramo4 = new Ramo("Progra");

        db.insertRamo(ramo1);
        db.insertRamo(ramo2);
        db.insertRamo(ramo3);
        db.insertRamo(ramo4);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewpager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new HorarioFragment(), "Horario");
        adapter.AddFragment(new MallaFragment(), "Malla");
        adapter.AddFragment(new SemestreFragment(), "Semestre");
        adapter.AddFragment(new LinksFragment(), "Links");

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
