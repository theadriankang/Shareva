package com.example.shareva.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shareva.Fragments.CurrentShareboxTabFragment;
import com.example.shareva.Fragments.UpcomingShareboxTabFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CurrentShareboxTabFragment();
            case 1:
                return new UpcomingShareboxTabFragment();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
