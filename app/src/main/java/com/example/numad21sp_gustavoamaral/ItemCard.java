package com.example.numad21sp_gustavoamaral;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener {

    private final int itemID;
    private final String itemName;

    public ItemCard(int itemID, String itemName) {
        this.itemID = itemID;
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void onItemClick(int position) {
        // do something
    }
}