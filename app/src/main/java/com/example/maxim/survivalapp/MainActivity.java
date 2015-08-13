package com.example.maxim.survivalapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    ArrayList<String> listItem = new ArrayList<String>();

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                listItem.add(edit.getText().toString());
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        btn.setOnClickListener(listener);

        setListAdapter(adapter);
    }

   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    private void restoreInstanceState(Bundle savedInstanceState){
        saveState(savedInstanceState);
    }

    private void saveState(Bundle state){
        //TODO
    }

    private void doCreate(Bundle savedInstanceState){
        setContentView(R.layout.activity_main);

        //if (null != savedInstanceState) restoreInstanceState(savedInstanceState);

        ActionBar bar = getActionBar();
        //bar.setDisplayShowTitleEnabled(false);
        //bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String[] names = {"Checklist", "Info"};
        int fragments[] = { R.id.check_frag, R.id.info_frag };
        TabManager.initialize(this, 0, names, fragments);
    }
*/


}
