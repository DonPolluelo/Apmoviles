package com.example.uniup.uniup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    String[] nombresGrupos= {"Lunes","Martes","Miércoles","Jueves","Viernes"};
    String[][] nombresHijos = {{"ramo1","ramo2"},{"ramo3","ramo4"},{"ramo5","ramo6"},{"ramo7","ramo8"},{"ramo9","ramo10"}};

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
        txtView.setPadding(100,0,0,0);
        txtView.setTextSize(30);
        txtView.setTextColor(Color.BLUE);
        return txtView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final TextView txtView = new TextView(context);
        txtView.setText(nombresHijos[groupPosition][childPosition]);
        txtView.setPadding(100,0,0,0);
        txtView.setTextSize(24);
        txtView.setTextColor(Color.RED);

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
