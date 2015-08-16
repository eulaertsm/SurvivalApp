package com.example.maxim.survivalapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.maxim.survivalapp.dao.SurvivalDAO;
import com.example.maxim.survivalapp.db.SQLiteHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Destination;

public class MainActivity extends Activity {

    //ArrayList<Destination> listItemDB = new ArrayList<Destination>();
    private List<Destination> listItem;

    private MainListAdapter adapter;

    private SQLiteHelper db;
    private SurvivalDAO dao;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnAdd);
        lv = (ListView) findViewById(R.id.main_list);

        db = new SQLiteHelper(this);
        dao = new SurvivalDAO(db);

        listItem = dao.getAllDestinations();

        adapter = new MainListAdapter(this, android.R.layout.simple_list_item_1, listItem);
        lv.setAdapter(adapter);
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.txtItem);
                dao.saveDestination(new Destination(edit.getText().toString()));
                listItem = dao.getAllDestinations();
                edit.setText("");
                adapter = new MainListAdapter(v.getContext(), android.R.layout.simple_list_item_1, listItem);
                lv.setAdapter(adapter);
            }
        };

        btn.setOnClickListener(listener);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Destination Destination = listItem.get(position);
                Intent MyIntent = new Intent(MainActivity.this, Main2Activity.class);
                MyIntent.putExtra("Destination", Destination.getMyID());
                MainActivity.this.startActivity(MyIntent);
            }
        });
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
