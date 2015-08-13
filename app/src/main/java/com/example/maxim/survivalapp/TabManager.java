package com.example.maxim.survivalapp;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Maxim on 10/08/15.
 */
public class TabManager {
    public static void initialize(Activity activity, int defaultIndex, String[] names, int[] fragments) {

        int n = names.length;
        int i = 0;

        TabListener f = (TabListener) activity.getFragmentManager().findFragmentById(fragments[i]);

        if (null != f) {

            ActionBar b = activity.getActionBar();
            b.removeAllTabs();

            for (; i < n; i++) {
                f = (TabListener) activity.getFragmentManager().findFragmentById(fragments[i]);
                ActionBar.Tab t = b.newTab().setText(names[i]).setTag(f).setTabListener(f);
                b.addTab(t);
            }
            b.getTabAt(defaultIndex).select();
        }
    }

    public static void loadTabFragments(Activity activity, Bundle b) {

    }
}
