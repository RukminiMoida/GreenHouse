package com.PG.GreenHouse.Utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;

public class UsbDriver
{

    private final Context mApplicationContext;
    private final UsbManager mUsbManager;
    @SuppressWarnings("unused")
   // private final UsbConnectionHandler mConnectionHandler;
    private final int VID;
    private final int PID;
    protected static final String ACTION_USB_PERMISSION = "ch.serverbox.android.USB";
    public static int Device_Exception;
    public static UsbDevice Device_Details;
    public static UsbEndpoint Data_In_End_Point = null;
    public static UsbEndpoint Data_Out_End_Point = null;
    public static UsbDeviceConnection USB_Device_Connection;

    public UsbDriver(Activity parentActivity , int vid, int pid)
    {
        mApplicationContext = parentActivity.getApplicationContext();
        mUsbManager = (UsbManager) mApplicationContext.getSystemService(Context.USB_SERVICE);
        VID = 6790;
        PID = 29987;
        Device_Exception = 0;
        //  init();
        Check_Devices();
    }

    private void Check_Devices()
    {
        @SuppressWarnings("unused")
        int j=0;
        HashMap<String, UsbDevice> devlist = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviter = devlist.values().iterator();
        Device_Details = null;
        if (devlist.size() != 0)
        {

            while (deviter.hasNext())
            {
                Device_Details = deviter.next();

                if (Device_Details.getVendorId() == VID && Device_Details.getProductId() == PID)
                {
                    if (!mUsbManager.hasPermission(Device_Details))
                    {
                        onPermissionDenied(Device_Details);
                    }
                    else
                    {
                        UsbDeviceConnection conn = mUsbManager.openDevice(Device_Details);
                        if (!conn.claimInterface(Device_Details.getInterface(0), true))
                        {
                            return;
                        }

                        conn.controlTransfer(0x40, 0, 0, 0, null, 0, 0);// reset
                        conn.controlTransfer(0x40, 0, 1, 0, null, 0, 0);// clear Rx
                        conn.controlTransfer(0x40, 0, 2, 0, null, 0, 0);// clear Tx
                        conn.controlTransfer(0x40, 0x03, 0x809C, 0, null, 0, 0);
                        USB_Device_Connection=conn;
                        Data_In_End_Point = null;
                        Data_Out_End_Point = null;

                        UsbInterface usbIf = Device_Details.getInterface(0);
                        for (int i = 0; i < usbIf.getEndpointCount(); i++)
                        {
                            if (usbIf.getEndpoint(i).getType() == UsbConstants.USB_ENDPOINT_XFER_BULK)
                            {
                                if (usbIf.getEndpoint(i).getDirection() == UsbConstants.USB_DIR_IN)

                                    Data_In_End_Point = usbIf.getEndpoint(i);
                                else
                                    Data_Out_End_Point = usbIf.getEndpoint(i);
                            }
                        }
                        if (Data_In_End_Point == null || Data_Out_End_Point == null)

                            Device_Exception = 2;
                    }
                    break;
                }j++;
            }
            if (Device_Details == null)
            {
                Device_Exception = 3;
                return;
            }
        }
        else
        {
            Device_Exception = 1;
            return;
        }

    }
    public void onPermissionDenied(UsbDevice d)
    {
        UsbManager usbman = (UsbManager) mApplicationContext.getSystemService(Context.USB_SERVICE);

        PendingIntent pi = PendingIntent.getBroadcast(mApplicationContext, 0, new Intent(ACTION_USB_PERMISSION), 0);

        mApplicationContext.registerReceiver(mPermissionReceiver,new IntentFilter(ACTION_USB_PERMISSION));

        usbman.requestPermission(d, pi);
    }


    private class PermissionReceiver extends BroadcastReceiver
    {
        private final IPermissionListener mPermissionListener;

        public PermissionReceiver(IPermissionListener permissionListener)
        {
            mPermissionListener = permissionListener;
        }

        @Override
        public void onReceive(Context context, Intent intent)
        {
            mApplicationContext.unregisterReceiver(this);

            if (intent.getAction().equals(ACTION_USB_PERMISSION))
            {
                if (!intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false))
                {
                    mPermissionListener.onPermissionDenied((UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE));
                }
                else
                {
                    l("Permission granted");
                    UsbDevice dev = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (dev != null)
                    {
                        if (dev.getVendorId() == VID && dev.getProductId() == PID)
                        {
                            Check_Devices() ;
                        }
                    }
                    else
                    {
                        e("device not present!");
                    }
                }
            }
        }

    }

    // MAIN LOOP

    // END MAIN LOOP
    private BroadcastReceiver mPermissionReceiver = new PermissionReceiver(new IPermissionListener()
    {
        @Override
        public void onPermissionDenied(UsbDevice d)
        {
            l("Permission denied on " + d.getDeviceId());
        }
    });

    private static interface IPermissionListener
    {
        void onPermissionDenied(UsbDevice d);
    }

    public final static String TAG = "USBController";

    private void l(Object msg)
    {
        Log.d(TAG, ">==<" + msg.toString() + " >==<");
    }

    private void e(Object msg)
    {
        Log.e(TAG, ">==< " + msg.toString() + " >==<");
    }
}