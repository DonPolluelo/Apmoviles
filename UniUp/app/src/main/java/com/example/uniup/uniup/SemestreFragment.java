package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.uniup.uniup.adapters.SemestreAdapter;
import com.example.uniup.uniup.db.DataBaseHelper;
import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;

public class SemestreFragment extends Fragment {

    private static final String TAG = "SemestreFragment";
    private RamoDB db;
    RecyclerView recycler;


    public SemestreFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.semestre_fragment, container, false);
        DataBaseHelper dbHelper = new DataBaseHelper(getActivity());
        db = new RamoDB(dbHelper);

        recycler = (RecyclerView) view.findViewById(R.id.recycler_semestre);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.boton_ramo);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AgregarSemestreActivity.class);
                startActivity(i);
            }
        });

        final ArrayList<Ramo> listaRamos = db.consultarListaRamos();
        ArrayList<String> infoRamos = new ArrayList<>();

        for  (int i = 0; i<listaRamos.size();i++) {
            infoRamos.add(listaRamos.get(i).getName());
        }

        SemestreAdapter adapter = new SemestreAdapter(infoRamos);
        recycler.setAdapter(adapter);

        return view;
    }
}
