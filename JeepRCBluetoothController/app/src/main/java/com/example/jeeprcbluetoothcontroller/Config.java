package com.example.jeeprcbluetoothcontroller;

import android.Manifest;

public class Config {

    public static final int MY_PERMISSIONS_REQUEST_CODE = 1;
    public static final String[] PERMISSIONS = {Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static final int MY_PERMISSIONS_REQUEST_CODE_BT = 2;

    public static final String bluetoothRCControllerDeviceName = "RCControllerAndroid";
    public static final String RC_UUID = "00001101-0000-1000-8000-00805F9B34FB"; // RC_UUID is the app's UUID string, also used in the server code.


    //selected gear
    public static String dGear= "1";
    public static String rGear = "2";

    //turn left/right
    public static String steeringLeft = "3";
    public static String steeringRight = "4";

    public static int throotleBias = 50;
    public static int MIN_THROOTLE= 10;
    public static int MAX_THROOTLE= 110;



}
