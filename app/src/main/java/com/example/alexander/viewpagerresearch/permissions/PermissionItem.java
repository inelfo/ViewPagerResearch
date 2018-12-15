package com.example.alexander.viewpagerresearch.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;

import com.mdgd.commons.utilities.PermissionsUtil;

public class PermissionItem {

    private final String[] permissions;
    private final IPermissionAction action;
    public final int rcCode;
    private final int label;
    private final int description;
    private boolean isExpanded = false;
    private boolean isActionDone = false;
    private final IOnPermissionChange onPermissionAction;

    public PermissionItem(int label, int description, String... permissions) {
        this.label = label;
        this.description = description;
        this.permissions = permissions;
        this.rcCode = IPermissionAction.RC_PERMISSIONS;
        this.action = null;
        this.onPermissionAction = null;
    }

    public PermissionItem(int label, int description, IOnPermissionChange onPermissionAction, String... permissions) {
        this.label = label;
        this.description = description;
        this.permissions = permissions;
        this.rcCode = IPermissionAction.RC_PERMISSIONS;
        this.action = null;
        this.onPermissionAction = onPermissionAction;
    }

    public PermissionItem(int label, int description, int rcCode, IPermissionAction action) {
        this.label = label;
        this.description = description;
        this.permissions = null;
        this.action = action;
        this.rcCode = rcCode;
        this.onPermissionAction = null;
    }

    public int getLabel() {
        return label;
    }

    public int getDescription() {
        return description;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public IPermissionAction getAction() {
        return action;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean isExpanded) {
        this.isExpanded = isExpanded;
    }

    public boolean hasPermission(Activity activity){
        final String[] permissions = getPermissions();
        final IPermissionAction action = getAction();
        if(permissions != null){
            boolean hasPermission = true;
            for(String permission : permissions){
                hasPermission = hasPermission && activity.checkPermission(permission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
            }
            return hasPermission;
        }
        else if(action != null){
            return action.hasPermission(activity);
        }
        return false;
    }

    public void requestPermission(Activity activity) {
        final String[] permissions = getPermissions();
        final IPermissionAction action = getAction();
        if(permissions != null){
            PermissionsUtil.requestPermissionsIfNeed(activity, IPermissionAction.RC_PERMISSIONS, permissions);
        }
        else if(action != null){
            action.requestPermission(activity);
        }
    }



}
