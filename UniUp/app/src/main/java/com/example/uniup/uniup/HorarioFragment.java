package com.example.uniup.uniup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;


public class HorarioFragment extends Fragment {

    ExpandableListView listaexpandible;


    public HorarioFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.horario_fragment, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.ver_lista);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Asignar Horario",Toast.LENGTH_SHORT).show();
                /*Intent i = new Intent(getActivity(),AgregarRamoHorarioActivity.class);
                startActivity(i);*/
            }
        });

        listaexpandible = (ExpandableListView) view.findViewById(R.id.expandable);

        ExpandableListViewAdapter adaptador = new ExpandableListViewAdapter(getActivity().getApplicationContext());

        listaexpandible.setAdapter(adaptador);


        return view;

    }
    }