package com.tyltcodeworld.louis.projetsade05;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.CheckBox;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.UUID;


public class Control extends ActionBarActivity {

    Button btnDis, btnlup, btnup, btnrup, btnleft, btnstop, btnright, btnlown, btndown, btnrown;
    CheckBox cboxactiv;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private Switch Switcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(BluetoothPair.EXTRA_ADDRESS); //receive the address of the bluetooth Pair

        //view of the lControl
        setContentView(R.layout.activity_control);

        //call the widgtes
        btnDis = (Button) findViewById(R.id.button4);
        btnlup = (Button) findViewById(R.id.lup);
        btnup = (Button) findViewById(R.id.up);
        btnrup = (Button) findViewById(R.id.rup);
        btnleft = (Button) findViewById(R.id.left);
        btnstop = (Button) findViewById(R.id.stop);
        btnright = (Button) findViewById(R.id.right);
        btnlown = (Button) findViewById(R.id.lown);
        btndown = (Button) findViewById(R.id.down);
        btnrown = (Button) findViewById(R.id.rown);



        cboxactiv = (CheckBox) findViewById(R.id.Switcher1);


        new ConnectBT().execute(); //Call the class to connect

        //commands to be sent to bluetooth


        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect(); //close connection
            }
        });

        btnlup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lup();
            }
        });
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up();
            }
        });
        btnrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rup();
            }
        });

        btnleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left();
            }
        });
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        btnright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                right();
            }
        });

        btnlown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lown();
            }
        });
        btndown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down();
            }
        });
        btnrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rown();
            }
        });


        cboxactiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write("o".toString().getBytes());
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                    }

                    else {
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write("f".toString().getBytes());
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                    }
            }
        });


    }




    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout

    }

    private void lup()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("e".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void up()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("z".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void rup()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("r".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void left()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("q".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void stop()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("a".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void right()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("d".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void lown()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("w".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }
    }
    private void down()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("s".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }

        }
    }
    private void rown()
    {

        if (btSocket!=null) {
            try {
                btSocket.getOutputStream().write("c".toString().getBytes());
            } catch (IOException e) {
                msg("Error");
            }
        }

    }












    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(Control.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
