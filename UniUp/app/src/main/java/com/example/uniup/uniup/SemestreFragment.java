package com.example.uniup.uniup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.uniup.uniup.db.RamoDB;
import com.example.uniup.uniup.models.Ramo;

import java.util.ArrayList;
import java.util.List;

public class SemestreFragment extends Fragment {

    private RamoDB db;
    private ArrayAdapter adapter;

    public SemestreFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.semestre_fragment, container, false);
        db = new RamoDB(getActivity());

        ArrayList list = db.loadClientes();

        List listaNombres= new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            listaNombres.add(list.get(i));
        }
        ListView lv = (ListView) view.findViewById(R.id.mylistview);
        adapter = new ArrayAdapter(getActivity(), R.layout.list_item, listaNombres);
        lv.setAdapter(adapter);

        return view;
    }
}
