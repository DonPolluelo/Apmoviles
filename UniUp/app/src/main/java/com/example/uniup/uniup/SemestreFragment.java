package com.example.uniup.uniup;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
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
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.uniup.uniup.adapters.SemestreAdapter;
        import com.example.uniup.uniup.db.DataBaseHelper;
        import com.example.uniup.uniup.db.RamoDB;
        import com.example.uniup.uniup.db.SemestreDB;
        import com.example.uniup.uniup.models.Ramo;
        import com.example.uniup.uniup.models.Semestre;
        import com.github.clans.fab.FloatingActionMenu;

        import java.util.ArrayList;

        import static android.content.Context.MODE_PRIVATE;

public class SemestreFragment extends Fragment implements AdapterView.OnItemClickListener{

    private static final String TAG = "SemestreFragment";
    private RamoDB dbRamo;
    private SemestreDB dbSemestre;
    ArrayList<Semestre> listaSemestres;
    DataBaseHelper dbHelper;
    RecyclerView recycler;
    SemestreAdapter adapter;

    public SemestreFragment() {
    }

    View view;

    FloatingActionMenu actionMenu;
    FloatingActionButton button1,button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.semestre_fragment, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("semestre", MODE_PRIVATE);
        final String semestre = prefs.getString("semestre", "");
        final int id_semestre = prefs.getInt("id", 0);


        TextView textView = (TextView) view.findViewById(R.id.nombreS);
        textView.setText(semestre);

        FloatingActionMenu actionMenu = (FloatingActionMenu) view.findViewById(R.id.menu_flotante);
        com.github.clans.fab.FloatingActionButton button1 = (com.github.clans.fab.FloatingActionButton)
                view.findViewById(R.id.boton_semestre);
        com.github.clans.fab.FloatingActionButton button2 = (com.github.clans.fab.FloatingActionButton)
                view.findViewById(R.id.boton_ramo);
        com.github.clans.fab.FloatingActionButton button3 = (com.github.clans.fab.FloatingActionButton)
                view.findViewById(R.id.boton_agregar_ramo);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            //    Toast.makeText(getActivity(),"Agregar Semestre",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),AgregarSemestreActivity.class);
                startActivity(i);
            }
        });
       button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               // Toast.makeText(getActivity(),"Eliminar Ramo",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),EliminarRamoActivity.class);
                startActivity(i);

            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            //    Toast.makeText(getActivity(),"Agregar Ramo",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),AgregarRamoActivity.class);
                startActivity(i);

            }
        });


        //Lista Ramos

        dbHelper = new DataBaseHelper(getActivity());
        dbSemestre = new SemestreDB(dbHelper);

        ArrayList<Ramo> listaRamos=dbSemestre.consultarRamosPorSemestre(Integer.toString(id_semestre));
        ArrayList<String> infoRamos = new ArrayList<>();

        for  (int i = 0; i<listaRamos.size();i++) {
            infoRamos.add(listaRamos.get(i).getName());
        }
        recycler = (RecyclerView) view.findViewById(R.id.recycler_semestre);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adapter = new SemestreAdapter(infoRamos);
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int pos, long id) {
            Intent Inicio = new Intent(getActivity(), MainActivity.class);
            SharedPreferences prefs = getActivity().getSharedPreferences("ramo", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("ramo", pos + 1);
            editor.apply();
        startActivity(Inicio);

    }

}
