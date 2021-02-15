package com.example.numad21sp_gustavoamaral;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

public class LinkCollectorActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_link_collector);
        mTextView = (TextView) findViewById(R.id.text);

//
//        view.findViewById(R.id.aboutButton).setOnClickListener(
//                view12 -> NavHostFragment.findNavController(HomeFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment));
    }
}