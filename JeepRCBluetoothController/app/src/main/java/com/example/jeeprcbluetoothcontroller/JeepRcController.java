package com.example.jeeprcbluetoothcontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import static com.example.jeeprcbluetoothcontroller.Config.PERMISSIONS;

public class JeepRcController {

    private boolean userHasPermissions;
    private BluetoothAdapter bluetoothAdapter;


    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void requestPermissionsToUser(Context context) {
        if(!hasPermissions(context, PERMISSIONS)){
            ActivityCompat.requestPermissions((Activity)context, PERMISSIONS, Config.MY_PERMISSIONS_REQUEST_CODE);
        }
    }

    public boolean isUserHasPermissions() {
        return userHasPermissions;
    }

    public void setUserHasPermissions(boolean userHasPermissions) {
        this.userHasPermissions = userHasPermissions;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public void setBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }
}
