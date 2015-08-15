package com.example.maxim.survivalapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Maxim on 8/08/15.
 */
public class ChecklistFragment extends Fragment implements OnClickListener{
//    private List<HashMap<String, String>> CheckList = new ArrayList<HashMap<String, String>>();
//    public static final String ITEM_COLUMN = "Item";
//    public static final String AMOUNT_COLUMN = "Amount";
    private static final String ARG_SECTION_NUMBER = "section_number";
    ArrayList<String> listItem = new ArrayList<String>();
    ArrayAdapter<String> adapter;

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
        ListView lview = (ListView) view.findViewById(R.id.listview);

        Button btn = (Button) view.findViewById(R.id.btnAdd);

        adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, listItem);

        btn.setOnClickListener(this);

        lview.setAdapter(adapter);

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

    @Override
    public void onClick(View v) {
        EditText edit = (EditText) v.findViewById(R.id.txtItem);
        String txt = edit.getText().toString();
        listItem.add(txt);
        //edit.setText("");
        //adapter.notifyDataSetChanged();
    }
}
