package com.example.jeeprcbluetoothcontroller;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

import static com.example.jeeprcbluetoothcontroller.Config.MY_PERMISSIONS_REQUEST_CODE_BT;
import static com.example.jeeprcbluetoothcontroller.Config.PERMISSIONS;

public class MainActivity extends AppCompatActivity {

    private JeepRcController jeepRcController;

    //layout components
    private Button btnStartDiscoring;
    private Button btnTurnLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.jeepRcController = new JeepRcController(getApplicationContext());

        boolean userHasPermissionsTmp = jeepRcController.hasPermissions(this, PERMISSIONS);
        jeepRcController.setUserHasPermissions(userHasPermissionsTmp);

        if(jeepRcController.isUserHasPermissions())
        {
            defineSensors();
            defineLayout();
        }
        else
            jeepRcController.requestPermissionsToUser(this);
    }

    private void defineLayout() {
        btnStartDiscoring = findViewById(R.id.btn_start_discoring);
        btnStartDiscoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jeepRcController.verifyIfBluetoothIsEnabled(MainActivity.this);
                String result = jeepRcController.startDiscoveringDevices(MainActivity.this);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }
        });

        btnTurnLeft = findViewById(R.id.btn_turn_left);
        btnTurnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = jeepRcController.turnLeft();
                if(!result)
                    Toast.makeText(getApplicationContext(), "Error with connection with the RC.", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void defineSensors() {
        BluetoothAdapter bluetoothAdapterTmp = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapterTmp == null) {
            // Device doesn't support Bluetooth
            finish();
        }
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

        defineSensors();
        defineLayout();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //jeepRcController.stopDiscoveringDevices(this);
    }
}
