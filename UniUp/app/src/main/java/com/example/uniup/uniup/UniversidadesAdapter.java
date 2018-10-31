package com.example.uniup.uniup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hecto_000 on 16-08-2015.
 */
public class UniversidadesAdapter extends ArrayAdapter<Universidades>{
    Context context;
    int LayoutResortId;
    Universidades data[]= null;

    public UniversidadesAdapter(Context context, int layoutResortId, Universidades[] data) {
        super(context, layoutResortId,data);
        this.context= context;
        this.LayoutResortId= layoutResortId;
        this.data= data;

    }

    public View getView(int position, View contentView, ViewGroup parent){
        View row= contentView;
        UniversidadesHolder holder = null;

        if(row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row= inflater.inflate(LayoutResortId, parent, false);
            holder= new UniversidadesHolder();
            holder.texto = (TextView) row.findViewById((R.id.tv));
            row.setTag(holder);
        }
        else{
            holder= (UniversidadesHolder) row.getTag();
        }

        Universidades universidades = data[position];
        holder.texto.setText(universidades.title);


        return row;
    }

    static class UniversidadesHolder{
        TextView texto;
    }
}