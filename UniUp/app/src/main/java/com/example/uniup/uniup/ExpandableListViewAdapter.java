package com.example.uniup.uniup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    String[] nombresGrupos= {"Lunes","Martes","Miércoles","Jueves","Viernes"};
    String[][] nombresHijos = {{"9-10 Matemáticas 3","11-12 Ayudantía Matemáticas 3"},{"3-4 Física General 3","7-8 Natación","9-10 Aplicaciones Móviles"},{"5-6 Inglés"},{"3-4 Física General 3","9-10 Matemáticas 3","13-14 Estructura de Datos"},{"1-2 Estructura de Datos","3-4 Economía","5-6 Matemáticas 3"}};


    Context context;


    public ExpandableListViewAdapter(Context context){

        this.context=context;
    }


    @Override
    public int getGroupCount() {
        return nombresGrupos.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return nombresHijos[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return nombresGrupos[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return nombresHijos[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        TextView txtView = new TextView(context);
        txtView.setText(nombresGrupos[groupPosition]);
        txtView.setPadding(80,50,0,50);
        txtView.setTextSize(24);
        txtView.setTextColor(Color.BLACK);
        return txtView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final TextView txtView = new TextView(context);
        txtView.setText(nombresHijos[groupPosition][childPosition]);
        txtView.setPadding(80,50,0,50);
        txtView.setTextSize(22);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(context, txtView.getText().toString(),Toast.LENGTH_SHORT).show();
            }

        });


        return txtView;


    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
