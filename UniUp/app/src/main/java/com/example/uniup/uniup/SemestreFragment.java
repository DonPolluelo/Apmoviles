package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;

public class SemestreFragment extends Fragment {

    private static final String TAG = "OnCreateView";

    private RamoDB db;


    public SemestreFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.semestre_fragment, container, false);
        db = new RamoDB(getActivity());

        ListView lv = (ListView) view.findViewById(R.id.mylistview);


        final ArrayList<Ramo> listaRamos = db.consultarListaRamos();
        ArrayList<String> infoRamos = new ArrayList<>();

        for  (int i = 0; i<listaRamos.size();i++) {
            infoRamos.add(listaRamos.get(i).getName());
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item, infoRamos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Abrir nueva activity
                Ramo ramo = listaRamos.get(position);
                Intent intent = new Intent(getActivity(), ListaNotasActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ramo",ramo);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }
}
