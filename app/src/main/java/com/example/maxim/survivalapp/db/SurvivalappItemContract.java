package com.example.maxim.survivalapp.db;

import android.provider.BaseColumns;

/**
 * Created by maxim on 15/08/15.
 */
public final class SurvivalappItemContract {
    public SurvivalappItemContract() {
    }

    public static abstract class SurvivalItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "SurvivalItem";
        public static final String CULUMN_NAME_SURVIVALITEMID = "survivalitemid";
        public static final String CULUMN_NAME_ITEM = "item";
        public static final String CULUMN_NAME_SURVIVALID = "survivalid";

    }
}
