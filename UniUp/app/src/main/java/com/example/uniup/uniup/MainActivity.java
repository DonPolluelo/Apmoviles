package com.example.uniup.uniup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uniup.uniup.HorarioFragment;
import com.example.uniup.uniup.LinksFragment;
import com.example.uniup.uniup.MallaFragment;
import com.example.uniup.uniup.R;
import com.example.uniup.uniup.SemestreFragment;
import com.example.uniup.uniup.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
