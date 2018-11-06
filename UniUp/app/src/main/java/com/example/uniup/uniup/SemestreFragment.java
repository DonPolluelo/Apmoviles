package com.example.uniup.uniup;

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
        import android.widget.TextView;

        import com.example.uniup.uniup.adapters.SemestreAdapter;
        import com.example.uniup.uniup.db.DataBaseHelper;
        import com.example.uniup.uniup.db.RamoDB;
        import com.example.uniup.uniup.db.SemestreDB;
        import com.example.uniup.uniup.models.Ramo;
        import com.example.uniup.uniup.models.Semestre;

        import java.util.ArrayList;

        import static android.content.Context.MODE_PRIVATE;

public class SemestreFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.semestre_fragment, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("semestre", MODE_PRIVATE);
        final String semestre = prefs.getString("semestre", "");
        final int id_semestre = prefs.getInt("id", 0);


        TextView textView = (TextView) view.findViewById(R.id.nombreS);
        textView.setText(semestre);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.boton_ramo);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AgregarSemestreActivity.class);
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
}
