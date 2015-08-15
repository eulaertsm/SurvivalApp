package com.example.maxim.survivalapp.db;

import android.provider.BaseColumns;

/**
 * Created by maxim on 15/08/15.
 */
public final class SurvivalappContract {

    public SurvivalappContract() {
    }

    public static abstract class SurvivalEntry implements BaseColumns {
        public static final String TABLE_NAME = "Survival";
        public static final String CULUMN_NAME_SURVIVALID = "survivalid";
        public static final String CULUMN_NAME_DESTINATION = "destionation";

    }

}
