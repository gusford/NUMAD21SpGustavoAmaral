package com.example.numad21sp_gustavoamaral;

import android.widget.Toast;

public class URLCard implements CardClickable {
    private final int id;
    private final String url;

    public URLCard(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void onClick(int position) {
    }
}