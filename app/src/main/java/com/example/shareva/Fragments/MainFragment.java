package com.example.shareva.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.shareva.Adapters.WatchAdapter;
import com.example.shareva.R;
import com.example.shareva.SpaceItemDecoration;
import com.example.shareva.WatchDataBaseHelper;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private Context mContext;

    private androidx.recyclerview.widget.RecyclerView rv_watchListing;

    ArrayList<String> watch, size, type, desc, verification;
    WatchDataBaseHelper watchDataBaseHelper;
    WatchAdapter watchAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        rv_watchListing = v.findViewById(R.id.rv_watchListing);

        initUI();

    }

    private void initUI() {

        watchDataBaseHelper = new WatchDataBaseHelper(mContext);
        watch = new ArrayList<>();
        size = new ArrayList<>();
        type = new ArrayList<>();
        desc = new ArrayList<>();
        verification = new ArrayList<>();

        watchAdapter = new WatchAdapter(mContext, watch, size, type, desc, verification);
        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        rv_watchListing.addItemDecoration(new SpaceItemDecoration(spaceInPixels));
        rv_watchListing.setAdapter(watchAdapter);
        rv_watchListing.setLayoutManager(new LinearLayoutManager(mContext));
        displayData();
    }

    private void displayData() {
        Cursor cursor = watchDataBaseHelper.getAllData();
        if (cursor.getCount()==0)
        {
            Toast.makeText(mContext, "No existing watch listing record", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                watch.add(cursor.getString(1));
                size.add(cursor.getString(2));
                type.add(cursor.getString(3));
                desc.add(cursor.getString(4));
                verification.add(cursor.getString(5));

            }
        }
    }
}