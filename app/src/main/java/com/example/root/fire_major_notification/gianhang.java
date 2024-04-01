package com.example.root.fire_major_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;import com.google.android.material.tabs.TabLayout;

public class gianhang extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gianhang);
        String sdata = getIntent().getStringExtra("user");

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpage);

        tabLayout.setupWithViewPager(viewPager);

        gianhangAdapter gh = new gianhangAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        gh.addfragment(new gianA(), "GIAN HANG A");
        gh.addfragment(new gianB(), "GIAN HANG B");
        gh.addfragment(new gianC(), "GIAN HANG C");
        viewPager.setAdapter(gh);
    }
}