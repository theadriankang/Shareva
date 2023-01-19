package com.example.shareva.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.example.shareva.R;
import com.example.shareva.SuccessfulListingActivity;
import com.google.android.material.button.MaterialButton;

public class ShareFragment extends Fragment {

    private Context mContext;
    private MaterialButton btn_listItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        btn_listItem = view.findViewById(R.id.btn_listItem);


        mContext = getActivity();

        pageDirectories();


        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return view;
    }

    private void pageDirectories() {


        // List Item
        btn_listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SuccessfulListingActivity.class);
                startActivity(intent);
            }
        });
    }

}