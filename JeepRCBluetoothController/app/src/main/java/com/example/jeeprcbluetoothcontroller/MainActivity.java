package com.example.jeeprcbluetoothcontroller;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.jeeprcbluetoothcontroller.Config.MY_PERMISSIONS_REQUEST_CODE_BT;
import static com.example.jeeprcbluetoothcontroller.Config.PERMISSIONS;

public class MainActivity extends AppCompatActivity {

    private JeepRcController jeepRcController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.jeepRcController = new JeepRcController();

        boolean userHasPermissionsTmp = jeepRcController.hasPermissions(this, PERMISSIONS);
        jeepRcController.setUserHasPermissions(userHasPermissionsTmp);

        if(jeepRcController.isUserHasPermissions())
        {
            defineLayout();
            defineSensors();
        }
        else
            jeepRcController.requestPermissionsToUser(this);

    }

    private void defineLayout() {

    }

    private void defineSensors() {
        BluetoothAdapter bluetoothAdapterTmp = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapterTmp == null) {
            // Device doesn't support Bluetooth
            finish();
        }

        //Request to enable the bluetooth
        if (!bluetoothAdapterTmp.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, MY_PERMISSIONS_REQUEST_CODE_BT);
        }

        /*
        //if the bluetooth wasn't turned on we need close the app
        if (!bluetoothAdapterTmp.isEnabled()) {
            throwAlertBuilder(getString(R.string.bluetooth_off_title),getString(R.string.bluetooth_off_desc));
        }
         */


        jeepRcController.setBluetoothAdapter(bluetoothAdapterTmp);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int i=0; i<=grantResults.length-1; i++)
        {
            if(grantResults[i] == PackageManager.PERMISSION_DENIED)
            {
                throwAlertBuilder(getString(R.string.permissions_denied),getString(R.string.permissions_denied_desc));
                return;
            }
        }

        defineLayout();
        defineSensors();
    }

    public void throwAlertBuilder(String titleMessage, String descMessage){
        new AlertDialog.Builder(this)
                .setTitle(titleMessage)
                .setMessage(descMessage)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
