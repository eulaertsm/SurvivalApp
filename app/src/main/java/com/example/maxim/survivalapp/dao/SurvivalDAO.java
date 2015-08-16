package com.example.maxim.survivalapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.maxim.survivalapp.db.SQLiteHelper;
import com.example.maxim.survivalapp.db.SurvivalappContract;
import com.example.maxim.survivalapp.db.SurvivalappItemContract;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Destination;
import model.Item;

/**
 * Created by maxim on 15/08/15.
 */
public class SurvivalDAO {

    private SQLiteHelper helper;

    public SurvivalDAO(SQLiteHelper helper) { this.helper = helper; }

    public List<Destination> getAllDestinations(){
        List<Destination> destinations = new LinkedList<Destination>();


        String query = "SELECT  * FROM " + SurvivalappContract.SurvivalEntry.TABLE_NAME;


        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        Destination dest = null;
        if (cursor.moveToFirst()) {
            do {
                dest = new Destination();
                dest.setMyID(Integer.parseInt(cursor.getString(0)));
                dest.setDestination(cursor.getString(1));

                destinations.add(dest);
            } while (cursor.moveToNext());
        }

        return destinations;
    }


    public List<Item> getAllItemsById(int id) {
        List<Item> items = new LinkedList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String[] COLUMNS_ITEM = {SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID, SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_ITEM, SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALID};

        Cursor cursor =
                db.query(SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME,
                        COLUMNS_ITEM,
                        " survivalid = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        null,
                        null);


        Item item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new Item();
                item.setMyID(Integer.parseInt(cursor.getString(0)));
                item.setItem(cursor.getString(1));

                items.add(item);
            } while (cursor.moveToNext());
        }


        return items;
    }

    public Destination getDestinationByID(int id) {
        String[] COLUMNS_DEST = {SurvivalappContract.SurvivalEntry.CULUMN_NAME_SURVIVALID, SurvivalappContract.SurvivalEntry.CULUMN_NAME_DESTINATION};
        SQLiteDatabase db = helper.getReadableDatabase();


        Cursor cursor =
                db.query(SurvivalappContract.SurvivalEntry.TABLE_NAME,
                        COLUMNS_DEST,
                        " survivalid = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);


        if (cursor != null) {
            cursor.moveToFirst();
        } else {
            Log.d("Database", "Destination not found");
            return null;
        }

        Destination dest = null;


        if (cursor != null) {
            dest = new Destination();
            dest.setMyID(Integer.parseInt(cursor.getString(0)));
            dest.setDestination(cursor.getString(1));
        }


        return dest;
    }

    public void saveDestination(Destination d){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SurvivalappContract.SurvivalEntry.CULUMN_NAME_DESTINATION, d.getDestination());


        db.insert(SurvivalappContract.SurvivalEntry.TABLE_NAME,
                null,
                values);


        db.close();

    }

    public void saveItem(Item t){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_ITEM, t.getItem());
        values.put(SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALID, t.getDestID());


        db.insert(SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME,
                null,
                values);


        db.close();
    }

    public int deleteDestination(Destination d){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        int i = db.delete(SurvivalappContract.SurvivalEntry.TABLE_NAME,SurvivalappContract.SurvivalEntry.CULUMN_NAME_SURVIVALID + " = ?",new String[] { String.valueOf(d.getMyID()) });
        db.close();

        ArrayList<Item> items = d.getItems();
        for(int j = 0; j < items.size(); i++){
            //deleteItem
            deleteItem(items.get(j));
        }

        return i;
    }

    public int deleteItem(Item t){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        int i = db.delete(SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME,SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID + " = ?",new String[] { String.valueOf(t.getMyID()) });
        db.close();

        return i;
    }

}
