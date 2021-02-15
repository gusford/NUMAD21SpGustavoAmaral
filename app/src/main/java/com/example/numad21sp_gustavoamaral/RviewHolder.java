package com.example.numad21sp_gustavoamaral;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView urlName;

    public RviewHolder(View itemView, final CardClickable listener) {
        super(itemView);
        urlName = itemView.findViewById(R.id.item_name);

        itemView.setOnClickListener(v -> {
            if (listener == null)
                return;

            int position = getLayoutPosition();

            // Skip no position
            if (position == RecyclerView.NO_POSITION)
                return;

            listener.onClick(itemView.getContext(), position);
        });
    }
}