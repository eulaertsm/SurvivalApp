package com.example.maxim.survivalapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maxim on 15/08/15.
 */
public class SQLiteHelperItem extends SQLiteOpenHelper {


    public SQLiteHelperItem (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SURVIVAL_TABLE = "CREATE TABLE " + SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME + " ( " +
                SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_ITEM + " TEXT, " +
                " FOREIGN KEY ("+SurvivalappItemContract.SurvivalItemEntry.TASK_SUR+") REFERENCES "+SurvivalappContract.SurvivalEntry.TABLE_NAME+" ("+SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALID + "));";
        db.execSQL(CREATE_SURVIVAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String UPGRADE_TABLE = "DROP TABLE IF EXISTS " + SurvivalappContract.SurvivalEntry.TABLE_NAME;
        db.execSQL(UPGRADE_TABLE);
        this.onCreate(db);
    }

    //for external use
    public SQLiteDatabase getDB() {
        return this.getReadableDatabase();
    }
}