package com.example.alexander.viewpagerresearch.permissions;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

public class Permissions {

    public boolean hasAccessibilityPermission(Context ctx) {
        int accessibilityEnabled = 0;
        final ContentResolver contentResolver = ctx.getContentResolver();
        try {
            accessibilityEnabled = Settings.Secure.getInt(contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED);
        }
        catch (Settings.SettingNotFoundException e) {/*ignore*/}
        if (accessibilityEnabled == 1) {
            final String settingValue = Settings.Secure.getString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            final android.text.TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(ctx.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
