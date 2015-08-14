package com.example.maxim.survivalapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Maxim on 8/08/15.
 */
public class ChecklistFragment extends Fragment {
    private ArrayList<HashMap<String, String>> CheckList;
    public static final String ITEM_COLUMN = "Item";
    public static final String AMOUNT_COLUMN = "Amount";
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ChecklistFragment newInstance(int sectionNumber) {
        ChecklistFragment fragment = new ChecklistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checklist, container, false);
        ListView lview = (ListView)view.findViewById(R.id.listview);
        fillList();
        ChecklistAdapter adapter = new ChecklistAdapter(CheckList, this.getActivity());
        lview.setAdapter(adapter);
        return lview;
    }

    private void fillList(){
        CheckList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> temp1 = new HashMap<String, String>();
        temp1.put(ITEM_COLUMN, "item column key");
        temp1.put(AMOUNT_COLUMN, "aumount column key");
        CheckList.add(temp1);
    }
}
