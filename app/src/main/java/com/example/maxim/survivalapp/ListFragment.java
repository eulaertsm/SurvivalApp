package com.example.maxim.survivalapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import model.MainListItem;

/**
 * Created by Maxim on 7/08/15.
 */
public class ListFragment extends Fragment {

    private List mainListItemList;


   /* ArrayList<String> list = new ArrayList<String>();

    ArrayList<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.setHasOptionsMenu(true);

        if(null != savedInstanceState)
            restoreState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       final ListView list = (ListView) inflater.inflate(R.layout.list_frag, container, false);

        attachAdapter(list);
        list.setOnItemClickListener(this);

        return list;
    }

    private void attachAdapter(ListView list) {
        // Make a trivial adapter that loads an array of strings
        ArrayAdapter<String> numbers = new ArrayAdapter<String>(
                list.getContext().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                new String [] {
                        "one", "two", "three", "four", "five", "six"
                });

        // tell the list to use it
        list.setAdapter(numbers);
        // l.setOnItemClickListener(this);
    }

    // Factor this out of methods that get saved state
    private void restoreState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
        // As an example of sending data to our fragments, we will create a bundle
        // with an int and a string, based on which view was clicked
        Bundle b = new Bundle();
        int ordinal = position + 1;
        b.putInt("place", ordinal);
        b.putString("placeName", Integer.toString(ordinal));
        TabManager.loadTabFragments(getActivity(), b);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.s_menu, menu);
    }*/

    private ArrayList<MainListItem> mListItems;
    private View mCurrentView;
    private int mCurrentPosition;
    private MainListAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListItems.add(new MainListItem("Example 1"));
        mListItems.add(new MainListItem("Example 2"));
        mListItems.add(new MainListItem("Example 3"));

        final RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.list_frag, container, false);
        final ListView mListView = (ListView) rl.findViewById(R.id.listItem);

        if(this.mListItems.size() > 0){
            mAdapter = new MainListAdapter(this.getActivity(), this.mListItems);
            mListView.setAdapter(mAdapter);
        }

        Button btn =  (Button) rl.findViewById(R.id.btnAdd);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) rl.findViewById(R.id.txtItem);
                mListItems.add(new MainListItem(edit.getText().toString()));
                edit.setText("");
                mAdapter.notifyDataSetChanged();
            }
        };

        btn.setOnClickListener(listener);
        return rl;
    }

}
