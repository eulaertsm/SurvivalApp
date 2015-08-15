package model;

/**
 * Created by maxim on 15/08/15.
 */
public class Item {
    private int myID = 0;
    private String item;

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
}
