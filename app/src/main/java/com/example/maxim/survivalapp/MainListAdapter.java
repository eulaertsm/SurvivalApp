package com.example.maxim.survivalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.Destination;

public class MainListAdapter extends ArrayAdapter<Destination> {

    private ArrayList<Destination> items;

    public MainListAdapter(Context context, int textViewResourceId, ArrayList<Destination> items){
        super(context, textViewResourceId, items);
        this.items = items;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = li.inflate(android.R.layout.simple_list_item_1, parent, false);

        TextView text = (TextView) row.findViewById(android.R.id.text1);
        text.setText(items.get(position).getDestination());

        return row;
    }
}