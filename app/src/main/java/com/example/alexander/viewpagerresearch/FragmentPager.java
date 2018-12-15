package com.example.alexander.viewpagerresearch;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class FragmentPager extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    static final String TAG = "myLogs";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 12;

    int pageNubmer;
    int backColor;

    public static FragmentPager newInstance(int page) {
        FragmentPager fragmentPager = new FragmentPager();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        fragmentPager.setArguments(arguments);
        return fragmentPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageNubmer = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        Log.d(TAG, "onCreate Fragment: " + pageNubmer );

        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpager_layout, null);

        TextView tvPage = view.findViewById(R.id.text);
        tvPage.setText("Page " + pageNubmer);
        tvPage.setBackgroundColor(backColor);

        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);


        Log.d(TAG, "onCreateView Fragment: " + pageNubmer );

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach Fragment: " + pageNubmer );
    }

    @Override
    public void onDetach() {
        super.onDetach();
       Log.d(TAG, "onDetach Fragment: " + pageNubmer );

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Fragment: " + pageNubmer );

    }

    @Override
    public void onStop() {
        super.onStop();
      //  Log.d(TAG, "onStop Fragment: "  + pageNubmer);


    }
}
