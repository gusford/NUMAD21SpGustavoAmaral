package com.example.numad21sp_gustavoamaral;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {
    private final ArrayList<URLCard> urlList;
    private CardClickable listener;

    public RviewAdapter(ArrayList<URLCard> urlList) {
        this.urlList = urlList;
    }

    public void setOnItemClickListener(CardClickable listener) {
        this.listener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        URLCard currentItem = urlList.get(position);

        holder.urlName.setText(currentItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }
}
