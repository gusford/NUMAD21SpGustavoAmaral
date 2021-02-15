package com.example.numad21sp_gustavoamaral;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener {

    private final int imageSource;
    private final String itemName;

    //Constructor
    public ItemCard(int imageSource, String itemName) {
        this.imageSource = imageSource;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void onItemClick(int position) {
        // do something
    }
}