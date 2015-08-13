package com.example.maxim.survivalapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.MainListItem;

/**
 * Created by maxim on 11/08/15.
 */
public class MainListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MainListItem> mItems;

    public MainListAdapter(Context context, ArrayList<MainListItem> items) {
        this.mItems = items;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mItems.size();
    }

    @Override
    public Object getItem(int position) {
        if(!(position >= this.getCount())) {
            return this.mItems.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void deleteItem(int position){
        this.mItems.remove(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_frag, null);
        }


        return convertView;
    }

}
