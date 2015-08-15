package com.example.maxim.survivalapp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.maxim.survivalapp.db.SQLiteHelper;
import com.example.maxim.survivalapp.db.SQLiteHelperItem;
import com.example.maxim.survivalapp.db.SurvivalappContract;
import com.example.maxim.survivalapp.db.SurvivalappItemContract;

import java.text.ParseException;
import java.util.ArrayList;

import model.Destination;
import model.Item;

/**
 * Created by maxim on 15/08/15.
 */
public class SurvivalDAO {

    private SQLiteHelper helper;
    private SQLiteHelperItem helper2;

    public SurvivalDAO(SQLiteHelper helper) { this.helper = helper; }

    public ArrayList<Destination> getAllDestinations() throws ParseException {
        ArrayList<Destination> Items = new ArrayList<Destination>();
        Destination m = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        String SELECT_ALL = "SELECT * FROM " + SurvivalappContract.SurvivalEntry.TABLE_NAME;
        Cursor c = db.rawQuery(SELECT_ALL, null);
        if(c.getCount()>0){
            while (c.moveToNext()) {
                m = new Destination(c.getString(1));
                m.setMyID(c.getInt(0));
                m.setItems(getAllItemsById(m.getMyID()));
            }
        }

        return Items;
    }


    public ArrayList<Item> getAllItemsById(int PID) throws ParseException {
        ArrayList<Item> Items = new ArrayList<Item>();
        Item i = null;
        SQLiteDatabase db = this.helper2.getReadableDatabase();
        String SELECT_ALL = "SELECT * FROM " + SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME +
                " WHERE " + SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID + " = " + PID;
        Cursor c = db.rawQuery(SELECT_ALL, null);
        if(c.getCount()>0){
            while (c.moveToNext()) {
                i = new Item(c.getString(1));
                i.setMyID(c.getInt(0));
            }
        }

        return Items;
    }

    public Destination getDestinationsByID(int id) throws ParseException {
        Destination m = null;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        String SELECT_ALL = "SELECT * FROM " + SurvivalappContract.SurvivalEntry.TABLE_NAME +
        " WHERE " + SurvivalappContract.SurvivalEntry.CULUMN_NAME_SURVIVALID + " = " + id;
        Cursor c = db.rawQuery(SELECT_ALL, null);
        if(c.getCount()>0){
            while (c.moveToNext()) {
                m = new Destination(c.getString(1));
                m.setMyID(c.getInt(0));
                m.setItems(getAllItemsById(m.getMyID()));
            }
        }

        return m;
    }

    public int saveDestination(Destination d){
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int i = 0;
        values.put(SurvivalappContract.SurvivalEntry.CULUMN_NAME_SURVIVALID,d.getMyID());
        values.put(SurvivalappContract.SurvivalEntry.CULUMN_NAME_DESTINATION,d.getDestination());
        //call saveItem
        ArrayList<Item> items = d.getItems();
        for(int j = 0; j < items.size(); i++){
            saveItem(items.get(j));
        }

        if(d.getMyID() != 0){
            i = db.update(SurvivalappContract.SurvivalEntry.TABLE_NAME,values, SurvivalappContract.SurvivalEntry.CULUMN_NAME_SURVIVALID + " = ?",new String[] { String.valueOf(d.getMyID()) });
        } else {
            i = (int) db.insert(SurvivalappContract.SurvivalEntry.TABLE_NAME,null, values);
        }
        db.close();

        return i;
    }

    public int saveItem(Item t){
        SQLiteDatabase db = this.helper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        int i = 0;
        values.put(SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID, t.getMyID());
        values.put(SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_ITEM, t.getItem());

        if(t.getMyID() != 0){
            i = db.update(SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME,values, SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID + " = ?",new String[] { String.valueOf(t.getMyID()) });
        } else {
            i = (int) db.insert(SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME,null, values);
        }
        db.close();

        return i;
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
        SQLiteDatabase db = this.helper2.getWritableDatabase();
        int i = db.delete(SurvivalappItemContract.SurvivalItemEntry.TABLE_NAME,SurvivalappItemContract.SurvivalItemEntry.CULUMN_NAME_SURVIVALITEMID + " = ?",new String[] { String.valueOf(t.getMyID()) });
        db.close();

        return i;
    }

}
