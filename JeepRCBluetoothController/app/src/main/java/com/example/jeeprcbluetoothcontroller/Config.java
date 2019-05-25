package com.example.jeeprcbluetoothcontroller;

import android.Manifest;

public class Config {

    public static final int MY_PERMISSIONS_REQUEST_CODE = 1;
    public static final String[] PERMISSIONS = {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static final int MY_PERMISSIONS_REQUEST_CODE_BT = 2;


    public static final String bluetoothRCDeviceName = "DESKTOP-R4QJLEB";
    public static final String bluetoothRCDeviceHardwareAddress = "30:E3:7A:7F:CC:8F"; // MAC address


    public static final String bluetoothRCControllerDeviceName = "RCControllerAndroid";
    public static final String RC_UUID = "93eec396-77fc-11e9-8f9e-2a86e4085a59"; // RC_UUID is the app's UUID string, also used in the server code.

}
