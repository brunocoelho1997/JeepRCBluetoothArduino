package com.example.jeeprcbluetoothcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class AcceptThread extends Thread {
    private static final String TAG = "AcceptThread";

    private final BluetoothServerSocket mmServerSocket;

    private BluetoothAdapter bluetoothAdapter;
    private ConnectedThread connectedThread;

    public AcceptThread(BluetoothAdapter bluetoothAdapter, ConnectedThread connectedThread) {
        this.bluetoothAdapter = bluetoothAdapter;
        this.connectedThread = connectedThread;

        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(Config.bluetoothRCControllerDeviceName, UUID.fromString(Config.RC_UUID));

            Log.e(TAG, "Bluetooth server socket configured to accept the connection.");


        } catch (IOException e) {
            Log.d(TAG, "Socket's listen() method failed", e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {

                Log.d(TAG, "Bluetooth server socket waiting for a bluetooth request.");

                socket = mmServerSocket.accept();

                Log.d(TAG, "Bluetooth server socket accepted.");

            } catch (IOException e) {
                Log.e(TAG, "Socket's accept() method failed", e);
                break;
            }

            if (socket != null) {

                try {
                    // A connection was accepted. Perform work associated with
                    // the connection in a separate thread.

                    Log.d(TAG, "Will be managed the connected socket.");

                    manageMyConnectedSocket(socket);

                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void manageMyConnectedSocket(BluetoothSocket mmSocket) {
        this.connectedThread = new ConnectedThread(mmSocket);
        connectedThread.run();
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}
