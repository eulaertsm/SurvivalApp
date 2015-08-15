package model;

import java.util.ArrayList;

/**
 * Created by maxim on 11/08/15.
 */
public class MainListItem {

    private String country;

    private ArrayList<String> Items = new ArrayList<String>();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MainListItem(String country, ArrayList<String> Items) {
        this.country = country;
        this.Items = Items;
    }

    public ArrayList<String> getItems() {
        return Items;
    }

    public void setItems(ArrayList<String> items) {
        Items = items;
    }
}
