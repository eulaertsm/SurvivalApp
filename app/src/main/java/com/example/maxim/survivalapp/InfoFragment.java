package com.example.maxim.survivalapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import model.RSSItem;
import responder.RSSResponder;

/**
 * Created by Maxim on 8/08/15.
 */
public class InfoFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private InfoItemAdapter mAdapter;
    private ArrayList<RSSItem> mItems;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static InfoFragment newInstance(int sectionNumber) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public InfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            new RSSReader().execute();
        } else {
            mItems = savedInstanceState.getParcelableArrayList("key");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_frag, container, false);
        ListView mListView = (ListView) rootView.findViewById(R.id.RSSFeedList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mItems.get(position).getLink()));
                startActivity(i);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Main2Activity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    //GetRSSFeed method
    private class RSSReader extends AsyncTask<Void,Void,ArrayList<RSSItem>> {
        private ProgressDialog p;

        @Override
        protected ArrayList<RSSItem> doInBackground(Void... params) {
            RSSResponder responder = new RSSResponder();
            ArrayList<RSSItem> items;
            responder.fetchXML();
            items = responder.getItems();
            mItems = items;
            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<RSSItem> items) {
            mAdapter = new InfoItemAdapter(getActivity(),items);
            Log.i("maxim", items.size()+" items aanwezig");
            ListView mListView = (ListView) getActivity().findViewById(R.id.RSSFeedList);
            mListView.setAdapter(mAdapter);
            p.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = ProgressDialog.show(getActivity(), "", getString(R.string.loading));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", mItems);
        super.onSaveInstanceState(outState);
    }
}