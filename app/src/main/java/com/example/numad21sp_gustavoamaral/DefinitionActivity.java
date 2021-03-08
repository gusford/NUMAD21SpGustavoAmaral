package com.example.numad21sp_gustavoamaral;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Collections.replaceAll;

public class DefinitionActivity extends AppCompatActivity {
    private TextInputLayout queryInput;
    private Button searchButton;
    private TextView definitionText;

    private Handler apiRequestHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        // Cache UI elements
        queryInput = findViewById(R.id.queryInput);
        searchButton = findViewById(R.id.querySearch);
        definitionText = findViewById(R.id.definitionText);

        // Hook search button
        searchButton.setOnClickListener(v -> new Thread(new runnableAPIRequest()).start());
    }

    class runnableAPIRequest implements Runnable {
        @Override
        public void run() {
            try {
                // Grab query from UI
                Editable queryText = queryInput.getEditText().getText();

                // Prepare API call
                URL url = new URL(String.format("https://www.dictionaryapi.com/api/v3/references/collegiate/json/%s?key=5b329d94-036b-4ffb-8909-33e0e6430dce", queryText));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                // Perform API call
                conn.connect();

                // Translate response into JSON
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                JSONArray jsonArray = new JSONArray(sb.toString());

                // Skip if JSON is empty
                if (jsonArray.get(0) instanceof String) {
                    apiRequestHandler.post(() -> {
                        definitionText.setText("Word search failed! Similar words found:\n\n" + jsonArray.toString());
                    });
                    return;
                }

                // Parse JSON
                final String response = jsonArray
                        .getJSONObject(0)
                        .getJSONArray("def")
                        .getJSONObject(0)
                        .getJSONArray("sseq")
                        .getJSONArray(0)
                        .getJSONArray(0)
                        .getJSONObject(1)
                        .getJSONArray("dt")
                        .getJSONArray(0)
                        .getString(1)
                        .replaceAll("\\{.*?\\}", "");

                // relay data back to UI
                apiRequestHandler.post(() -> {
                    definitionText.setText(response);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

