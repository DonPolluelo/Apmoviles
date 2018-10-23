package com.example.uniup.uniup;

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

public class SemestreFragment extends Fragment {

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
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item, db.consultarListaRamos());
        lv.setAdapter(adapter);

        return view;
    }
}
