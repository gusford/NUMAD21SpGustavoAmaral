package com.example.numad21sp_gustavoamaral;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class LinkCollectorActivity extends AppCompatActivity {
    private ArrayList<URLCard> urlCardList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        // Retrieve any past data
        initListData(savedInstanceState);

        // Render the list view
        createRecyclerView();

        // Hook AddURL button
        findViewById(R.id.buttonAddURL).setOnClickListener(v -> {
            addItem(0);
        });
    }

    void initListData(Bundle savedInstanceState)
    {
        // The first time this Activity is opened, show placeholder items
        if (savedInstanceState == null || !savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            URLCard item1 = new URLCard(0, "http://www.google.com");
            URLCard item2 = new URLCard(1, "http://www.amazon.com");
            URLCard item3 = new URLCard(2, "http://www.chess.com");
            urlCardList.add(item1);
            urlCardList.add(item2);
            urlCardList.add(item3);
        }
//        TODO: If it was opened before, retrieve stored items; clean this
//        else {
//            if (urlCardList == null || urlCardList.size() == 0) {
//
//                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);
//
//                // Retrieve keys we stored in the instance
//                for (int i = 0; i < size; i++) {
//                    Integer imgId = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
//                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
//                    String itemDesc = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");
//                    boolean isChecked = savedInstanceState.getBoolean(KEY_OF_INSTANCE + i + "3");
//
//                    // We need to make sure names such as "XXX(checked)" will not duplicate
//                    // Use a tricky way to solve this problem, not the best though
//                    if (isChecked) {
//                        itemName = itemName.substring(0, itemName.lastIndexOf("("));
//                    }
//                    LinkCard linkCard = new LinkCard(imgId, itemName);
//
//                    urlCardList.add(linkCard);
//                }
//            }
//        }

    }

    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.urlList);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(urlCardList);
        rviewAdapter.setOnItemClickListener(position -> {
            urlCardList.get(position).onClick(position);
            rviewAdapter.notifyItemChanged(position);
        });

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    // TODO: Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = urlCardList == null ? 0 : urlCardList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put image information id into instance
            outState.putInt(KEY_OF_INSTANCE + i + "0", urlCardList.get(i).getImageSource());
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "1", urlCardList.get(i).getItemName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "2", urlCardList.get(i).getItemDesc());
            // put isChecked information into instance
            outState.putBoolean(KEY_OF_INSTANCE + i + "3", urlCardList.get(i).getStatus());
        }
        super.onSaveInstanceState(outState);
    }

    private void addItem(int position) {
        // TODO: Grab URL from textbox

        // TODO: Validate URL
        boolean validURL = true;

        if (validURL)
        {
            urlCardList.add(position, new URLCard(Math.abs(new Random().nextInt(100000)), "[URL]"));
            rviewAdapter.notifyItemInserted(position);

            Toast.makeText(LinkCollectorActivity.this, "URL successfully added!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LinkCollectorActivity.this, "URL failed!", Toast.LENGTH_SHORT).show();
        }
    }
}