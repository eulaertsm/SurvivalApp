package com.example.maxim.survivalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maxim on 14/08/15.
 */
public class ChecklistAdapter extends BaseAdapter {

    public static final String ITEM_COLUMN = "Item";
    public static final String AMOUNT_COLUMN = "Amount";

    private ArrayList<HashMap<String, String>> CheckList;
    private Context context;

    public ChecklistAdapter(ArrayList<HashMap<String, String>> checkList, Context context) {
        CheckList = checkList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.CheckList.size();
    }

    @Override
    public Object getItem(int position) {
        return CheckList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView TxtAmount;
        TextView TxtItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater mImflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            convertView = mImflater.inflate(R.layout.checklist_item, null);

            holder = new ViewHolder();
            holder.TxtItem = (TextView) convertView.findViewById(R.id.item);
            holder.TxtAmount = (TextView) convertView.findViewById(R.id.amount);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map = CheckList.get(position);
        holder.TxtItem.setText(map.get(ITEM_COLUMN));
        holder.TxtAmount.setText(map.get(AMOUNT_COLUMN));

        return convertView;
    }
}
