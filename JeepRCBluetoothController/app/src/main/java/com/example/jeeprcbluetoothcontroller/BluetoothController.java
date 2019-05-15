package com.example.jeeprcbluetoothcontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.Set;

import static com.example.jeeprcbluetoothcontroller.Config.MY_PERMISSIONS_REQUEST_CODE_BT;

public class BluetoothController {
    private BluetoothAdapter bluetoothAdapter;
    private BroadcastReceiver bluetoothBroadcastReceiver;

    public BluetoothController() {
        this.bluetoothBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                    //discovery starts, we can show progress dialog or perform other tasks
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    //discovery finishes, dismis progress dialog
                } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    //bluetooth device found
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address

                    Log.d("startDiscoveringDevices", "DEVICE HAS BEEN FOUND: Name: " + deviceName + "; DeviceHardwareAddress:" + deviceHardwareAddress);

                    if(deviceName.equals(Config.bluetoothRCDeviceName) && deviceHardwareAddress.equals(Config.bluetoothRCDeviceHardwareAddress))
                    {
                        //TODO: start a connection with the RC JEEP
                    };
                }
            }
        };
    }

    public void verifyIfBluetoothIsEnabled(Context context){
        //if the bluetooth is not enabled
        if (!bluetoothAdapter.isEnabled()) {
            //Request to enable the bluetooth
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ((Activity)context).startActivityForResult(enableBtIntent, MY_PERMISSIONS_REQUEST_CODE_BT);
        }
    }

    public boolean verifyIfConnectedWithRC(){
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                if(deviceName.equals(Config.bluetoothRCDeviceName) && deviceHardwareAddress.equals(Config.bluetoothRCDeviceHardwareAddress))
                    return true;
            }
            return false;
        }
        return false;
    }

    public void startDiscoveringDevices(Context context){
        // Register for broadcasts when a device is discovered.

        if(!bluetoothAdapter.isEnabled())
        {
            Log.d("startDiscoringDevices", "Bluetooth isn't enabled.");
            return;
        }

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        ((Activity)context).registerReceiver(bluetoothBroadcastReceiver, filter);
        bluetoothAdapter.startDiscovery();
    }

    public void stopDiscoveringDevices(Context context){

        try{
            if(bluetoothBroadcastReceiver != null)
            {
                context.unregisterReceiver(bluetoothBroadcastReceiver);
                Log.d("stopDiscoveringDevices", "Unregistered the bluetoothBroadcastReceiver");
            }
        }catch (IllegalArgumentException ex){
            Log.d("stopDiscoveringDevices", "The bluetooth Broadcast Receiver was not registered.");
        }
    }

    /*
        Getters and Setters
     */
    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }

    public void setBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public BroadcastReceiver getBluetoothBroadcastReceiver() {
        return bluetoothBroadcastReceiver;
    }

    public void setBluetoothBroadcastReceiver(BroadcastReceiver bluetoothBroadcastReceiver) {
        this.bluetoothBroadcastReceiver = bluetoothBroadcastReceiver;
    }
}
