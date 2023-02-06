package com.example.shareva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WatchDetail_Activity extends AppCompatActivity {

    private ImageView imgView_watch;
    private TextView txtView_watchName, txtView_desc, txtView_moreDesc;

    Intent intent;

    private String watchName, watchDesc, watchMoreDesc;
    private int watchImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_detail);

        intent = getIntent();

        initWidget();

        getIntentData();


    }

    private void getIntentData() {

        watchName = intent.getStringExtra("Watch Name");
        watchDesc = intent.getStringExtra("Watch Desc");
        watchMoreDesc = intent.getStringExtra("Watch More Desc");

        watchImage = intent.getIntExtra("Watch Image", 0);

        initUI();
    }

    private void initUI() {
        txtView_watchName.setText(watchName);
        txtView_desc.setText(watchDesc);
        txtView_moreDesc.setText(watchMoreDesc);

        imgView_watch.setImageResource(watchImage);
    }

    private void initWidget() {

        imgView_watch = findViewById(R.id.imgView_watch);

        txtView_watchName = findViewById(R.id.txtView_watchName);
        txtView_desc = findViewById(R.id.txtView_desc);
        txtView_moreDesc = findViewById(R.id.txtView_moreDesc);
    }
}