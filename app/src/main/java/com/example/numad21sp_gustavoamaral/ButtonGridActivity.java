package com.example.numad21sp_gustavoamaral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class ButtonGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_grid);
    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.buttonA:
                SetSelectedButton("A");
                break;
            case R.id.buttonB:
                SetSelectedButton("B");
                break;
            case R.id.buttonC:
                SetSelectedButton("C");
                break;
            case R.id.buttonD:
                SetSelectedButton("D");
                break;
            case R.id.buttonE:
                SetSelectedButton("E");
                break;
            case R.id.buttonF:
                SetSelectedButton("F");
                break;

            case R.id.buttonReturnHome:
                finish();
                break;
        }

    }

    private void SetSelectedButton(String buttonString)
    {
        TextView textView = findViewById(R.id.buttonPressedTextView);

        if (textView != null)
            textView.setText("Pressed: " + buttonString);
    }
}