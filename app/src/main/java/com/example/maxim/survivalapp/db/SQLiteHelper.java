package com.example.maxim.survivalapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maxim on 15/08/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "SurvivalAppDB";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SURVIVAL_TABLE = "CREATE TABLE " + SurvivalappContract.SurvivalEntry.TABLE_NAME + " ( " +
                SurvivalappContract.SurvivalEntry.CULUMN_NAME_SURVIVALID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SurvivalappContract.SurvivalEntry.CULUMN_NAME_DESTINATION + " TEXT )";

        db.execSQL(CREATE_SURVIVAL_TABLE);

        String CREATE_SURVIVALITEM_TABLE = "CREATE TABLE " + SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME + " ( " +
                SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_ITEM + " TEXT,"+
                SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALID + " INTEGER )";
        db.execSQL(CREATE_SURVIVALITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String UPGRADE_TABLE = "DROP TABLE IF EXISTS " + SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME;
        db.execSQL(UPGRADE_TABLE);

        String UPGRADE_TABLE2 = "DROP TABLE IF EXISTS " + SurvivalappContract.SurvivalEntry.TABLE_NAME;
        db.execSQL(UPGRADE_TABLE2);
        this.onCreate(db);
    }

    //for external use
    public SQLiteDatabase getDB() {
        return this.getReadableDatabase();
    }
}
