package com.example.numad21sp_gustavoamaral;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Random;

public class LinkCollectorActivity extends AppCompatActivity {
    private ArrayList<URLCard> urlCardList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        // Render the list view
        createRecyclerView();

        // Hook AddURL button
        findViewById(R.id.buttonAddURL).setOnClickListener(v -> addURLToList(0));
    }

    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.urlList);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(urlCardList);
        rviewAdapter.setOnItemClickListener((context, position) -> {
            urlCardList.get(position).onClick(context, position);
            rviewAdapter.notifyItemChanged(position);
        });

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

    private void addURLToList(int position) {
        // Grab URL from TextInputLayout
        TextInputLayout inputText = findViewById(R.id.newURLTextInput);
        String newURL = inputText.getEditText().getText().toString();

        // Basic URL Validation
        boolean isValidURL = newURL.endsWith(".com");

        if (isValidURL)
        {
            // Basic URL parsing
            if (!newURL.startsWith("http://") && !newURL.startsWith("https://"))
                newURL = "http://" + newURL;

            urlCardList.add(position, new URLCard(Math.abs(new Random().nextInt(100000)), newURL));
            rviewAdapter.notifyItemInserted(position);

            Toast.makeText(LinkCollectorActivity.this, newURL + " successfully added!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(LinkCollectorActivity.this, newURL + " uses invalid format!", Toast.LENGTH_SHORT).show();
        }
    }
}