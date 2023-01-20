package com.example.shareva.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shareva.Adapters.ForYouAdapter;
import com.example.shareva.Model.ForYouItems;
import com.example.shareva.R;

import java.util.ArrayList;


public class ForYouFragment extends Fragment {

    private ImageView btn_filter;

    private RecyclerView rv_forYou;

    private Context mContext;

    private ForYouAdapter forYouAdapter;

    private final ArrayList<ForYouItems> forYouItemsArrayList = new ArrayList<>();

    int[] forYouItemsPic = {R.drawable.giorgio_armani, R.drawable.calvin_klein_jeans, R.drawable.yunnig, R.drawable.sandro,
            R.drawable.lovefreya, R.drawable.diane_von_furstenberg, R.drawable.ph5, R.drawable.yunni_g};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_for_you, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        //ImageView
        btn_filter = v.findViewById(R.id.btn_filter);

        //RecyclerView
        rv_forYou = v.findViewById(R.id.rv_forYou);

        initUI();
    }

    private void initUI() {

        //for better performance of recyclerview.

        rv_forYou.setHasFixedSize(true);

        forYouAdapter = new ForYouAdapter(getContext(), forYouItemsArrayList);
        rv_forYou.setAdapter(forYouAdapter);

        //layout to contain recyclerview
        GridLayoutManager llm = new GridLayoutManager(mContext, 2);
        llm.setSmoothScrollbarEnabled(true);
//        llm.setSpanSizeLookup(new MySpansizeLookup(5,1,2));
        // orientation of linearlayoutmanager.
//        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_forYou.setLayoutManager(llm);

        new LoadForYouItem().execute();
    }

    ForYouItems forYouItems;

    class LoadForYouItem extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] itemName = getResources().getStringArray(R.array.forYouItem_name);
                String[] itemDesc = getResources().getStringArray(R.array.forYouItem_desc);

                for (int i = 0 ; i < itemName.length; i++)
                {
                    forYouItems = new ForYouItems();
                    forYouItems.setImage(forYouItemsPic[i]);
                    forYouItems.setItemName(itemName[i]);
                    forYouItems.setItemDesc(itemDesc[i]);
                    forYouItemsArrayList.add(forYouItems);
                    forYouItems = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

//            pgbPopulardestination.setVisibility(View.GONE);

            if (forYouItemsArrayList != null && forYouItemsArrayList.size() > 0) {
                forYouAdapter = new ForYouAdapter(mContext, forYouItemsArrayList);
                rv_forYou.setAdapter(forYouAdapter);
                forYouAdapter.notifyDataSetChanged();
            }
        }
    }


}