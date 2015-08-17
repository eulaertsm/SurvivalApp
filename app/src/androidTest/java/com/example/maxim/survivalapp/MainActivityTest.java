package com.example.maxim.survivalapp;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by maxim on 17/08/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity activity;
    private Instrumentation instrumentation;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.instrumentation = getInstrumentation();
        this.activity = getActivity();

        setActivityInitialTouchMode(true);

        MainActivity mClickFunActivity = getActivity();
        Button mClickMeButton = (Button)
                mClickFunActivity
                        .findViewById(R.id.btnAdd);
        TextView mInfoTextView = (TextView)
                mClickFunActivity.findViewById(R.id.txtItem);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();


    }

}
