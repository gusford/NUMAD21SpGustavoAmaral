package com.example.numad21sp_gustavoamaral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ButtonGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_grid);
    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.buttonReturnHome:
                break;
            case R.id.buttonA:
                break;
            case R.id.buttonB:
                break;
            case R.id.buttonC:
                break;
            case R.id.buttonD:
                break;
            case R.id.buttonE:
                break;
            case R.id.buttonF:
                break;
        }

    }
}