package com.PG.GreenHouse.UI;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.GreenHouse.Adapters.GridViewAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.LoginActivity;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.LocationPlacement.LocationPlacement;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.UsbDriver;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.GettingProcesses;
import com.PG.GreenHouse.model.LoginResponse;
import com.PG.GreenHouse.model.Process_Location;
import com.PG.GreenHouse.model.Processes_data;
import com.felhr.usbserial.UsbSerialDevice;
import com.felhr.usbserial.UsbSerialInterface;
import com.google.android.things.pio.PeripheralManager;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueEdition extends BaseActivity implements OnRadioButtonClick {

    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;

    //Adapters
    private GridViewAdapter adapter;
    private Context mContext;

    String YOUR_DEVICE_NAME;
    byte[] DATA;
    int TIMEOUT;

    //Strings
    String name,fullName;

    //services
    private ApiService apiService;
    private Processes_data processes_data;

    private Process_Location location;


    //
    public static final int targetVendorID = 6790;
    public static final int targetProductID = 29987;
    public  UsbManager manager;
    public  UsbDeviceConnection usbDeviceConnection;
    public UsbInterface usbInterfaceFound = null;
    public  UsbEndpoint endpointOut = null;
    public  UsbEndpoint endpointIn = null;
    public  UsbDevice usbdevice,device_details;
    public  UsbEndpoint listusbendpoint;

    public static final String PREFS_NAME = "LoginPrefs";
    HashMap<String, UsbDevice> devicelist= null;
    int selectedendpoint;
    static int Coil_No;
    private static final int VID = 6790;
    private static final int PID = 29987;
    private static UsbDriver Usb_Driver_class;
    ActionBar actionbar;
    //UsbConnectionHandler connectionHandler;
    public static UsbDriver USB_Driver_Child;
    public static boolean Communication_Failed,Frame_Ok,Total_Frame_Decoded;
    static byte[] Communication_Byte;
    public SharedPreferences loginpreferences;
    public SharedPreferences.Editor loginpreferenceseditor;
    public boolean savelogin;
    public String Password;
    Button sample_button;
    public EditText receive_Data;
    CheckBox remember_me;
    Typeface custom_font;
    String i = "";
    Intent i2;
    CheckBox show_password;
    String ProcID;
    String User_Name_string,password_string;
    TextView title,username_txt,password_txt,company_name;
    ByteBuffer buffer;
    Button signin;
    EditText dialog_username,dialog_password,dialog_confirm;

    static byte[] sample;
    static boolean Communication_Ok;
    public static float []Wave_Form_Data=new float[1500];
    public static float []Wave_Form_Data_1=new float[1500];
    public static float Respsonse_Time,Drive_Voltage;
    static int Sequence_No,Response_Time;
    private char string_to_receive;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_edition);

        mContext=ValueEdition.this;
        //Initializing Variables
        valueEdition_recycler_view_bsd_lots=findViewById(R.id.valueEdition_recycler_view_bsd_lots);
        value_edition_next_btn=findViewById(R.id.value_edition_next_btn);
        back_button_val_edt=findViewById(R.id.back_button_val_edt);


       // receive_Data = (EditText)findViewById(R.id.edit_receive);
      //  signin = (Button)findViewById(R.id.button1);


        UsbManager usbManager = getSystemService(UsbManager.class);
        Map<String, UsbDevice> connectedDevices = usbManager.getDeviceList();
        for (UsbDevice device : connectedDevices.values()) {
            if (device.getVendorId() == 0x2341 && device.getProductId() == 0x0001) {
                Log.i("dhf", "Device found: " + device.getDeviceName());
                startSerialConnection(usbManager, device);
                break;
            }
        }
//        value_edition_next_btn.setOnClickListener(new View.OnClickListener()
//        {
//
//            @SuppressWarnings("static-access")
//            @Override
//            public void onClick(View v)
//            {
//                Communication_Byte=new byte[1];
//
//                if(Check_Devices_Available()==true)
//                {
//                    int Packet_Size = USB_Driver_Child.Data_In_End_Point.getMaxPacketSize();
//                    Toast.makeText(ValueEdition.this,""+Packet_Size, Toast.LENGTH_LONG).show();
//                    Receive.start();
//                    Communication_Ok=false;
//                    for(int i=0;(i<5 && Communication_Ok!=true);i++)
//                        Send_Communication_Check_Command();
//
//                    if(Communication_Ok)
//                        Toast.makeText(ValueEdition.this, "Communication Successfully Established", Toast.LENGTH_LONG).show();
//                    else
//                        Toast.makeText(ValueEdition.this, "Communication Failure", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    void startSerialConnection(UsbManager usbManager, UsbDevice device) {
        UsbDeviceConnection connection = usbManager.openDevice(device);
        UsbSerialDevice serial = UsbSerialDevice.createUsbSerialDevice(device, connection);
        UsbSerialInterface.UsbReadCallback mCallback = (data) -> {
            String dataStr = null;
            try {
                dataStr = new String(data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.i("dsf", "Data received: " + dataStr);
        };
        serial.read(mCallback);
    }

    public boolean  Check_Devices_Available()
    {
        Usb_Driver_class = new UsbDriver(this, VID, PID);

        if(USB_Driver_Child.Device_Exception==0)
        {

            if(USB_Driver_Child.USB_Device_Connection==null || USB_Driver_Child.Data_Out_End_Point==null)
                return false;

            Toast.makeText(ValueEdition.this,"Device Found", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(USB_Driver_Child.Device_Exception==1)
        {
            Toast.makeText(ValueEdition.this,"No Devices Attached ", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(USB_Driver_Child.Device_Exception==2)
        {
            Toast.makeText(ValueEdition.this,"Device Found,But No End Points", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(USB_Driver_Child.Device_Exception==3)
        {
            Toast.makeText(ValueEdition.this,"Unable to Open Device", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;   //un known exception
    }

    private static void Send_Communication_Check_Command()
    {
        long i,j;

        Communication_Byte[0]='&';
        UsbDriver.USB_Device_Connection.bulkTransfer(UsbDriver.Data_Out_End_Point,Communication_Byte, 1, 0);
        for(i=0;(i<1000 && Communication_Ok!=true) ;i++)
            for(j=0;(j<1000 && Communication_Ok!=true);j++);
    }

    Thread Receive  = new Thread(new Runnable()
    {

        @SuppressWarnings("unused")
        @Override
        public void run()
        {
            ByteBuffer buffer = ByteBuffer.allocate(1);
            UsbRequest mUsbrequest = new UsbRequest();
            mUsbrequest.initialize(Usb_Driver_class.USB_Device_Connection, Usb_Driver_class.Data_In_End_Point);
            while(true){
                mUsbrequest.queue(buffer, 1);
                if(Usb_Driver_class.USB_Device_Connection.requestWait() == mUsbrequest){
                    byte dataReceive = buffer.get(0);
                    Log.d("Message", "Data to receive:"+dataReceive);

                        string_to_receive += (char)dataReceive;

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                value_edition_next_btn.setText(string_to_receive);

                            }
                        });

                }
            }




        }
    });


    //   callService();

//        PeripheralManager manager = PeripheralManager.getInstance();
//        List<String> deviceList = manager.getUartDeviceList();
//        if (deviceList.isEmpty()) {
//            Log.e("tag", "No UART port available on this device.");
//          Toast.makeText(getApplicationContext(),"No UART port available on this device.",Toast.LENGTH_LONG).show();
//        } else {
//            Log.e("tag", "List of available devices: " + deviceList);
//
//            Toast.makeText(getApplicationContext(),"List of available devices: ."+deviceList,Toast.LENGTH_LONG).show();
//        }

//        adapter=new GridViewAdapter(this,this);
//        valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
//        valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(this));
//        valueEdition_recycler_view_bsd_lots.setAdapter(adapter);




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                Log.e("weight", "onActivityResult: "+data.getStringExtra("weight"));
//                tvTotalWeight.setText(data.getStringExtra("weight"));
//                totalWeight = Math.round(Double.parseDouble(tvTotalWeight.getText().toString().trim()));
//
//                Log.i(TAG, "onActivityResult: Total Weight"+totalWeight);
//                calculateWeight();
            }
        }
    }

    private void callService() {
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GettingProcesses> call=apiService.getProcesses();
            call.enqueue(new Callback<GettingProcesses>() {
                @Override
                public void onResponse(Call<GettingProcesses> call, Response<GettingProcesses> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());

                            for (int i=0;i<response.body().getLocations().size();i++){
                                Log.e("locations",response.body().getLocations().get(i).getOffice_name());
                            }
                          //  Log.e("locations",response.body().getLocations().toString());
                            adapter=new GridViewAdapter(mContext, (OnRadioButtonClick) mContext,response.body().getData());
                            valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
                            valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(mContext));
                            valueEdition_recycler_view_bsd_lots.setAdapter(adapter);
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(mContext,"",response.body().getMessage(),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<GettingProcesses> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(mContext,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(mContext,getString(R.string.error_network));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRadioClick(Processes_data processes_data) {
        this.processes_data=processes_data;
    }

    @Override
    public void onRadioClickFT(FTLotNumbers processes_data) {

    }


}
