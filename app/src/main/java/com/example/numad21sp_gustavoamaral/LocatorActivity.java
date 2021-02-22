package com.example.numad21sp_gustavoamaral;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// Displays phone's longitude & latitude
public class LocatorActivity extends AppCompatActivity {
    public static final int REQUEST_PERMISSION_CODE = 123;

    TextView positionView;
    LocationManager lm;
    LocationListener updatePositionViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);

        positionView = findViewById(R.id.coordinatesTextView);

        // Initialize location manager
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // This listener binds location data with positionView
        updatePositionViewListener = location -> {
            String sb = "Latitude: " +
                    location.getLatitude() +
                    "\n" +
                    "Longitude: " +
                    location.getLongitude() +
                    "\n";
            positionView.setText(sb);
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        // If we don't have it already, request location permission
        if (!haveLocationPermission())
            RequestPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Deactivate listener
        if (haveLocationPermission())
            PauseLocationRequests();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Activate listener
        if (haveLocationPermission())
            BeginLocationRequests();
    }

    // Prompt user for location permission
    void RequestPermission() {
        new AlertDialog.Builder(this)
                .setTitle("Access to location services required")
                .setMessage("Grant permission?")

                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // Permission request followed by onRequestPermissionsResult callback
                    ActivityCompat.requestPermissions(LocatorActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_PERMISSION_CODE);
                })

                .setNegativeButton(
                        android.R.string.no,
                        (dialog, which) -> positionView.setText("Please enable location services."))
                .show();
    }

    // Handle permission change
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (haveLocationPermission())
                    BeginLocationRequests();
                else
                    positionView.setText("Please enable location services.");
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void BeginLocationRequests() {
        positionView.setText("Requesting position...");

        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000,
                10,
                updatePositionViewListener);
    }

    void PauseLocationRequests() {
        positionView.setText("Position request paused.");

        lm.removeUpdates(updatePositionViewListener);
    }

    boolean haveLocationPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }
}