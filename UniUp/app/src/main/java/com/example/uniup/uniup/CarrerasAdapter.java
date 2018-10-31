package com.example.uniup.uniup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by hecto_000 on 16-08-2015.
 */
public class CarrerasAdapter extends ArrayAdapter<Carreras>{
    Context context;
    int LayoutResortId;
    Carreras data[]= null;

    public CarrerasAdapter(Context context, int layoutResortId, Carreras[] data) {
        super(context, layoutResortId,data);
        this.context= context;
        this.LayoutResortId= layoutResortId;
        this.data= data;

    }

    public View getView(int position, View contentView, ViewGroup parent){
        View row= contentView;
        CarrerasHolder holder = null;

        if(row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row= inflater.inflate(LayoutResortId, parent, false);
            holder= new CarrerasHolder();
            holder.texto = (TextView) row.findViewById((R.id.tv));
            row.setTag(holder);
        }
        else{
            holder= (CarrerasHolder) row.getTag();
        }

        Carreras carreras = data[position];
        holder.texto.setText(carreras.title);


        return row;
    }

    static class CarrerasHolder{
        TextView texto;
    }
}