package com.example.alexander.viewpagerresearch.permissions;

import android.content.Context;

public interface IOnPermissionChange {

    long BORDER_TIME = 30 * 1000; // half minute



    void onPermissionGranted(Context ctx);


}
