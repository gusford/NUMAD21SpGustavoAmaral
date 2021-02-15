package com.example.numad21sp_gustavoamaral;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

// URLCard is a clickable ListItem which opens up browser with URL when clicked
public class URLCard implements CardClickable {
    private final int id;
    private final String url;

    public URLCard(int id, String url) {
        // Basic URL parsing
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

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
    public void onClick(Context context, int position) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
}