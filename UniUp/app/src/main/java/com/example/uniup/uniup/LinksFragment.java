package com.example.uniup.uniup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LinksFragment extends Fragment implements AdapterView.OnItemClickListener {

    List pages= new ArrayList();
    List links= new ArrayList();
    ArrayAdapter adapter;


    public LinksFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.links_fragment, container, false);

        ListView lv = (ListView) view.findViewById(R.id.mylistview);
        loadData();
        adapter = new ArrayAdapter(getActivity(), R.layout.list_item, pages);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        return view;
    }


    private void saveData() {
        SharedPreferences.Editor spe = getActivity().getPreferences(MODE_PRIVATE).edit();

        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < pages.size(); i++) {
            sb.append( ((i == 0) ? "" : ";")  + pages.get(i));
        }

        spe.putString("pages", sb.toString());
        spe.commit();
    }


    private void loadData() {
        SharedPreferences sp = getActivity().getPreferences(MODE_PRIVATE);
        String pagesList = sp.getString("pages",
                "Moodle;SIGA;Aula;RREE;CIME");

        String linksList = sp.getString("links",
                "https://moodle.inf.utfsm.cl/;https://www.siga.usm.cl;https://aula.usm.cl;https://www.rree.usm.cl;http://www.cime.cl");

        pages.clear();
        links.clear();

        for (String page : pagesList.split(";")) {
            pages.add(page);
        }

        for (String link : linksList.split(";")) {
            links.add(link);
        }
    }


    @Override
    public void onItemClick(AdapterView parent, View view, int pos, long id) {
        Uri uri = Uri.parse((String) links.get(pos));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
