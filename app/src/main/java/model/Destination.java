package model;

import java.util.ArrayList;

/**
 * Created by maxim on 11/08/15.
 */
public class Destination {

    private int myID;

    private String destination;

    private ArrayList<Item> Items = new ArrayList<Item>();

    public String getDestination() {
        return destination;
    }

    public int getMyID() {
        return myID;
    }

    public void setMyID(int myID) {
        this.myID = myID;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Destination(){}

    public Destination(String destination, ArrayList<Item> Items) {
        this.destination = destination;
        this.Items = Items;
    }

    public Destination(int id, String destination) {
        this.myID = id;
        this.destination = destination;
    }

    public Destination(String destination) {
        this.destination = destination;
    }

    public ArrayList<Item> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Item> items) {
        Items = items;
    }

    public void addItems(Item item) { this.Items.add(item); }

}
