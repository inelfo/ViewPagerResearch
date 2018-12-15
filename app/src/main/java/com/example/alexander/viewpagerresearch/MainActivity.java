package com.example.alexander.viewpagerresearch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.alexander.viewpagerresearch.permissions.IPermissionsModel;
import com.example.alexander.viewpagerresearch.permissions.PermissionItem;
import com.example.alexander.viewpagerresearch.permissions.Permissions;
import com.example.alexander.viewpagerresearch.permissions.PermissionsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PAGE_COUNT = 100;
    private static final String TAG = "myLogs";
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    IPermissionsModel permissionsModel = new PermissionsModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<PermissionItem> items = permissionsModel.getItems();

        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), items);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
               // Log.d(TAG, "onPageScrolled: " + i + " " + v  + " " + i1);

            }

            @Override
            public void onPageSelected(int position) {
              //  Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            //    Log.d(TAG, "onPageScrollStateChanged Fragment: " + i );


            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        List<PermissionItem> items;

        public MyFragmentPagerAdapter(FragmentManager fm, List<PermissionItem> items) {
            super(fm);
            this.items = items;
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentPager.newInstance(position, items.get(position));
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }


    }
}
