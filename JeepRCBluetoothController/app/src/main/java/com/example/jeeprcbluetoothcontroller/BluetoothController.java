package com.example.jeeprcbluetoothcontroller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.example.jeeprcbluetoothcontroller.Config.MY_PERMISSIONS_REQUEST_CODE_BT;

public class BluetoothController {
    private static String TAG = "BluetoothController";
    private BluetoothAdapter bluetoothAdapter;

    String bluetoothAddressArduino;
    private BluetoothSocket mmSocket;
    private BluetoothDevice mmDevice;
    private boolean isBtConnected = false;
    private Context context;
    private int lastThrootlePercentageTmp;

    public BluetoothController(Context context) {
        this.context = context;
    }

    public void verifyIfBluetoothIsEnabled(Context context){
        //if the bluetooth is not enabled
        if (!bluetoothAdapter.isEnabled()) {
            //Request to enable the bluetooth
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ((Activity)context).startActivityForResult(enableBtIntent, MY_PERMISSIONS_REQUEST_CODE_BT);
        }
    }

    public String startDiscoveringDevices(Context context){

        //verify again if the bluetooth is enabled... just for precaution
        if(!bluetoothAdapter.isEnabled())
        {
            Log.d("startDiscoringDevices", "Bluetooth isn't enabled.");
            return "Bluetooth isn't enabled.";
        }

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                Log.d("BluetoothController", "paired device: " + bt.getName() + "\n" + bt.getAddress());

                if(bt.getName().equals("HC-06"))
                {
                    String info = bt.getAddress();
                    String address = info.substring(info.length() - 17);
                    this.bluetoothAddressArduino = address;

                    //start the connection with the bt device
                    new BluetoothConnection().execute();

                    return "Starting the connection with the Jeep...";
                }
            }
            return "Didn't find a paired device with name HC-06. It's necessary to pair the Android with the device. Pin: 1234";
        }

        return "No Paired Bluetooth Devices Found.";
    }


    public boolean turnLeft(){return sendCommand(Config.steeringLeft);}

    public boolean turnRight(){return sendCommand(Config.steeringRight);}

    public boolean setDGear(){return sendCommand(Config.dGear);}

    public boolean setRGear(){return sendCommand(Config.rGear);}

    public boolean throotle(int throotlePercentage) {

        if(Math.abs(throotlePercentage - lastThrootlePercentageTmp) > Config.throotleBias || throotlePercentage==0 || throotlePercentage==100) //if the throotle percentage equals 0 ou 100 ignore the bias
        {
            lastThrootlePercentageTmp = throotlePercentage;
            return sendCommand("" + throotlePercentage);
        }
        return true;
    }

    public boolean sendCommand(String command) {

        try {
            if(mmSocket == null)
                return false;

            String commandTmp = command + "-";
            mmSocket.getOutputStream().write(commandTmp.getBytes());

            Log.d(TAG, "Sent value to android");

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Does not exist a connectedThread alive. Error: " + e);
            return false;
        }
    }

    private class BluetoothConnection extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            Toast.makeText(context, "Connecting with the bluetooth rc...", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (mmSocket == null || !isBtConnected)
                {
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    mmDevice = bluetoothAdapter.getRemoteDevice(bluetoothAddressArduino);//connects to the device's address and checks if it's available
                    mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(Config.RC_UUID));//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    mmSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
                Log.d("BluetoothController" , "error: " + e.getMessage());

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
                Toast.makeText(context, "Error with connection with the bluetooth RC. Verify if the RC is on.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context, "Connetion initilizated with success.", Toast.LENGTH_LONG).show();


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
}
