package com.example.root.fire_major_notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class gianhangAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments = new ArrayList<>();
    private final ArrayList<String> fragmentT = new ArrayList<>();

    public gianhangAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addfragment(Fragment fragment, String t) {
        fragments.add(fragment);
        fragmentT.add(t);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return fragmentT.get(position);
    }
}
