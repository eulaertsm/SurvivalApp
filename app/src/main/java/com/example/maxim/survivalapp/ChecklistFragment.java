package com.example.maxim.survivalapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.View.OnClickListener;

import com.example.maxim.survivalapp.dao.SurvivalDAO;
import com.example.maxim.survivalapp.db.SQLiteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Item;

/**
 * Created by Maxim on 8/08/15.
 */
public class ChecklistFragment extends Fragment{
//    private List<HashMap<String, String>> CheckList = new ArrayList<HashMap<String, String>>();
//    public static final String ITEM_COLUMN = "Item";
//    public static final String AMOUNT_COLUMN = "Amount";
private Activity activity;
    private static final String ARG_SECTION_NUMBER = "section_number";
    List<Item> listItem;
    MainList2Adapter adapter;
    private SurvivalDAO dao;
    private SQLiteHelper db;
    private int destID;
    private ListView lview;
    private EditText edit;


    public static ChecklistFragment newInstance(int sectionNumber) {
        ChecklistFragment fragment = new ChecklistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ChecklistFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checklist, container, false);
        lview = (ListView) view.findViewById(R.id.listview);

        Button btn = (Button) view.findViewById(R.id.btnAdd);

        Activity a = (Activity) view.getContext();
        Intent intent = a.getIntent();
        int value = intent.getIntExtra("Destination", 0);
        destID = value;

        db = new SQLiteHelper(view.getContext());
        dao = new SurvivalDAO(db);

        listItem = dao.getAllItemsById(value);

        adapter = new MainList2Adapter(view.getContext(), android.R.layout.simple_list_item_1, listItem);
        lview.setAdapter(adapter);

        edit = (EditText) view.findViewById(R.id.txtItem);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.saveItem(new Item(edit.getText().toString(), destID));
                listItem = dao.getAllItemsById(destID);
                edit.setText("");
                adapter = new MainList2Adapter(v.getContext(), android.R.layout.simple_list_item_1, listItem);
                lview.setAdapter(adapter);
            }
        });

       /* OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) v.findViewById(R.id.txtItem);
                listItem.add(edit.getText().toString());
                edit.setText("");
                //adapter.notifyDataSetChanged();
                adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, listItem);
                ListView lview = (ListView) v.findViewById(R.id.listview);
                lview.setAdapter(adapter);
            }
        };
        btn.setOnClickListener(listener);*/

       /* String[] from = new String[] {"item", "amount"};
        int[] to = new int[] { R.id.item1, R.id.item2};
        fillList();
        //ChecklistAdapter adapter = new ChecklistAdapter(CheckList, this.getActivity());
        SimpleAdapter adapter = new SimpleAdapter(inflater.getContext(), CheckList, R.layout.checklist_item, from, to);
        lview.setAdapter(adapter)*/;
        return view;
    }

    /*private void fillList(){
        HashMap<String, String> list = new HashMap<String, String>();

        list.put("item", "item column key");
        list.put("amount", "aumount column key");
        CheckList.add(list);
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Main2Activity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }



}
