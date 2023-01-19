package com.example.shareva;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.shareva.Fragments.ForYouFragment;
import com.example.shareva.Fragments.MainFragment;
import com.example.shareva.Fragments.MeFragment;
import com.example.shareva.Fragments.ShareFragment;
import com.example.shareva.Fragments.ShareboxFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initWidget();

    }

    private void initWidget() {
        chipNavigationBar = findViewById(R.id.menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_main, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();

        bottomMenu();

    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i) {
                    case R.id.bottom_nav_main:
                        fragment = new MainFragment();
                        break;
                    case R.id.bottom_nav_share:
                        fragment = new ShareFragment();
                        break;
                    case R.id.bottom_nav_foryou:
                        fragment = new ForYouFragment();
                        break;
                    case R.id.bottom_nav_sharebox:
                        fragment = new ShareboxFragment();
                        break;
                    case R.id.bottom_nav_me:
                        fragment = new MeFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}