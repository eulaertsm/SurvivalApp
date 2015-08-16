package model;

/**
 * Created by maxim on 15/08/15.
 */
public class Item {
    private int myID;
    private String item;
    private int destID;

    public Item(){}

    public Item(String item, int destID){
        this.item = item;
        this.destID = destID;
    }

    public String getItem() {
        return item;
    }

    public int getMyID() {
        return myID;
    }

    public void setMyID(int myID) {
        this.myID = myID;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Item(String item) {
        this.item = item;
    }

    public int getDestID() {
        return destID;
    }

    public void setDestID(int destID) {
        this.destID = destID;
    }
}
