package com.example.alexander.viewpagerresearch.permissions;

import android.app.Activity;
import android.content.Context;

public interface IPermissionAction {

    int RC_PERMISSIONS = 1000;
    int RC_ACCESSIBILITY = 1001;
    int RC_STATS = 1002;
    int RC_DO_NOT_DISTURB = 1003;
    int RC_ON_TOP = 1004;

    boolean hasPermission(final Context ctx);

    void requestPermission(final Activity ctx);
}
