package com.example.maxim.survivalapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import model.Destination;

public class MainActivity extends ListActivity {

    //ArrayList<Destination> listItemDB = new ArrayList<Destination>();
    private ArrayList<Destination> listItem = new ArrayList<Destination>();

    private MainListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);

        //TODO adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem.);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                listItem.add(new Destination(edit.getText().toString()));
                edit.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        btn.setOnClickListener(listener);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Destination Destination = this.listItem.get(position);
        Intent MyIntent = new Intent(MainActivity.this, Main2Activity.class);
        MyIntent.putExtra("Destination", (Serializable) Destination);
        MainActivity.this.startActivity(MyIntent);
    }



     @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
    }

    /*
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
