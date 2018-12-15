package com.example.alexander.viewpagerresearch.permissions;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.example.alexander.viewpagerresearch.R;

import java.util.ArrayList;
import java.util.List;

public class PermissionsModel implements IPermissionsModel{

    @Override
    public List<PermissionItem> getItems() {
        final List<PermissionItem> items = new ArrayList<>();

        items.add(new PermissionItem(R.string.label_imei, R.string.desc_imei, Manifest.permission.READ_PHONE_STATE)); // todo tmp, move this item
        items.add(new PermissionItem(R.string.label_location, R.string.desc_location, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION));
        items.add(new PermissionItem(R.string.label_do_not_disturb, R.string.desc_do_not_disturb, IPermissionAction.RC_DO_NOT_DISTURB, new IPermissionAction() {
            @Override
            public boolean hasPermission(Context ctx) {
                final NotificationManager nManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
                return nManager != null && nManager.isNotificationPolicyAccessGranted();
            }

            @Override
            public void requestPermission(Activity ctx) {
                ctx.startActivityForResult(new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS), RC_DO_NOT_DISTURB);
            }
        }));
        items.add(new PermissionItem(R.string.label_apps_usage, R.string.desc_apps_usage, IPermissionAction.RC_STATS, new IPermissionAction() {
            @Override
            public boolean hasPermission(Context ctx) {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    return false;
                }
                final AppOpsManager appOps = (AppOpsManager) ctx.getSystemService(Context.APP_OPS_SERVICE);
                final int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), ctx.getPackageName());
                return (mode == AppOpsManager.MODE_ALLOWED) || ctx.checkCallingOrSelfPermission(Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED;
            }

            @Override
            @TargetApi(23)
            public void requestPermission(final Activity ctx) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ctx.startActivityForResult(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS), RC_STATS);
                }
            }
        }));
        items.add(new PermissionItem(R.string.label_accessibility, R.string.desc_accessibility, IPermissionAction.RC_ACCESSIBILITY, new IPermissionAction() {
            private final Permissions p = new Permissions();

            @Override
            public boolean hasPermission(final Context ctx) {
                return p.hasAccessibilityPermission(ctx);
            }

            @Override
            public void requestPermission(final Activity ctx) {
                ctx.startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), RC_ACCESSIBILITY);
            }
        }));
        items.add(new PermissionItem(R.string.label_onTop, R.string.desc_onTop, IPermissionAction.RC_ON_TOP, new IPermissionAction() {
            @Override
            public boolean hasPermission(Context ctx) {
                return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(ctx);
            }

            @Override
            @TargetApi(23)
            public void requestPermission(final Activity ctx) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ctx.startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + ctx.getPackageName())), RC_ON_TOP);
                }
            }
        }));
        return items;
    }
}
