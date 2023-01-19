package com.example.shareva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class SuccessfulListingActivity extends AppCompatActivity {

    private MaterialButton btn_completeSuccessfulListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_listing);
        getSupportActionBar().hide();

        initWidget();
        pageDirectories();
    }

    private void pageDirectories() {
        btn_completeSuccessfulListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessfulListingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initWidget() {
        // Material Button
        btn_completeSuccessfulListing = findViewById(R.id.btn_completeSuccessfulListing);
    }
}