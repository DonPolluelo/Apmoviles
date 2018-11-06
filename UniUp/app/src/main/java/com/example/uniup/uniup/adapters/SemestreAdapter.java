package com.example.uniup.uniup.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uniup.uniup.R;

import java.util.ArrayList;

public class SemestreAdapter extends RecyclerView.Adapter<SemestreAdapter.ViewHolderDatos> {

    ArrayList<String> lista_ramos;

    public SemestreAdapter(ArrayList<String> lista_ramos){
        this.lista_ramos=lista_ramos;
    }

    @NonNull
    @Override
    public SemestreAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_ramos_semestre,viewGroup,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreAdapter.ViewHolderDatos viewHolderDatos, int i) {
        viewHolderDatos.asignarDatos(lista_ramos.get(i));
    }

    @Override
    public int getItemCount() {
        return lista_ramos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.ramo_semestre);
        }

        public void asignarDatos(String s) {
            nombre.setText(s);
        }
    }

    public void updateRecords(ArrayList<String> lista_ramos){
        this.lista_ramos = lista_ramos;

        notifyDataSetChanged();
    }
}
