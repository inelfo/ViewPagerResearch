package com.example.alexander.viewpagerresearch.permissions;

public abstract class OnPermissionChangeDefImpl implements  IOnPermissionChange {

    private final String prefsKey;

    OnPermissionChangeDefImpl(String prefsKey) {
        this.prefsKey = prefsKey;
    }


}
