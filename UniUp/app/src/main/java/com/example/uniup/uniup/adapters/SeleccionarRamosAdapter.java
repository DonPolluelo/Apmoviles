package com.example.uniup.uniup.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uniup.uniup.R;
import com.example.uniup.uniup.models.Ramo;
import com.example.uniup.uniup.models.Semestre;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarRamosAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Ramo> ramo;
    LayoutInflater inflater;

    public SeleccionarRamosAdapter(Activity activity) {
        this.activity = activity;
    }

    public SeleccionarRamosAdapter(Activity activity, ArrayList<Ramo> ramo) {
        this.activity = activity;
        this.ramo = ramo;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return ramo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view_seleccionar_ramos,parent,false);

            holder = new ViewHolder();

            holder.tvNombreSemestre = (TextView) convertView.findViewById(R.id.agregar_semestre_textview);
            holder.ivCheck = (ImageView) convertView.findViewById(R.id.agregar_semestre_checkbox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Ramo r = ramo.get(position);
        holder.tvNombreSemestre.setText(r.getName());
        if (r.isCheck()){
            holder.ivCheck.setBackgroundResource(R.drawable.ic_check_box_black_24dp);
        } else {
            holder.ivCheck.setBackgroundResource(R.drawable.ic_check_box_outline_blank_black_24dp);
        }

        return convertView;
    }

    public void updateRecords(ArrayList<Ramo> ramos){
        this.ramo = ramos;

        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView tvNombreSemestre;
        ImageView ivCheck;
    }
}
