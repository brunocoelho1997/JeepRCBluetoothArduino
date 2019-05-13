package com.example.jeeprcbluetoothcontroller;

import android.Manifest;

public class Config {

    public static final int MY_PERMISSIONS_REQUEST_CODE = 1;
    public static final String[] PERMISSIONS = {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static final int MY_PERMISSIONS_REQUEST_CODE_BT = 2;

}
