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

    private List pages= new ArrayList();
    private List links= new ArrayList();
    private ArrayAdapter adapter;


    public LinksFragment() {
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.links_fragment, container, false);

        ListView lv = (ListView) view.findViewById(R.id.mylistview);




        String university = getArguments().getString("uni");



        loadData(university);
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


    private void loadData(String university) {
        SharedPreferences sp = getActivity().getPreferences(MODE_PRIVATE);



        if (university.equals("usm")) {
            String pagesList = sp.getString("pages",
                    "Moodle;SIGA;Aula;Relaciones Estudiantiles;CIME;Minuta;SIREB;Gimnasio;MINEDUC;JUNAEB;SODEXO;Becas y Créditos");

            String linksList = sp.getString("links",
                    "https://moodle.inf.utfsm.cl/;https://www.siga.usm.cl;https://aula.usm.cl;https://www.rree.usm.cl;http://www.cime.cl;" +
                            "https://www.usm.cl/comunidad/servicio-de-alimentacion/;https://sireb.usm.cl/;https://siga.usm.cl/gim/;" +
                            "https://www.mineduc.cl/;https://www.junaeb.cl/;http://www.becajunaebsodexo.cl/;http://portal.beneficiosestudiantiles.cl/");


            pages.clear();
            links.clear();

            for (String page : pagesList.split(";")) {
                pages.add(page);
            }

            for (String link : linksList.split(";")) {
                links.add(link);
            }
        }


        if (university.equals("pucv")) {
            String pagesList = sp.getString("pages",
                    "Nave13;MINEDUC;JUNAEB;SODEXO;Becas y Créditos");

            String linksList = sp.getString("links",
                    "https://nave13.ucv.cl/" +
                            "https://www.mineduc.cl/;https://www.junaeb.cl/;http://www.becajunaebsodexo.cl/;http://portal.beneficiosestudiantiles.cl/");


            pages.clear();
            links.clear();

            for (String page : pagesList.split(";")) {
                pages.add(page);
            }

            for (String link : linksList.split(";")) {
                links.add(link);
            }
        }

    }


    @Override
    public void onItemClick(AdapterView parent, View view, int pos, long id) {
        Uri uri = Uri.parse((String) links.get(pos));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
