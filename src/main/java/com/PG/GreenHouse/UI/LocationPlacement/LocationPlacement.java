package com.PG.GreenHouse.UI.LocationPlacement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentDetailsInsertion;
import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentGridAdapter;
import com.PG.GreenHouse.Adapters.LocAdapters.PutAway_scanner_adapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.InterFace.ScannedInterface;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighment;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentGridModel;
import com.PG.GreenHouse.model.LocationPlacement.BarcodeResponce;
import com.PG.GreenHouse.model.LocationPlacement.BarcodeResults;
import com.PG.GreenHouse.model.Status;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.hardware.usb.UsbManager.EXTRA_PERMISSION_GRANTED;

public class LocationPlacement extends BaseActivity implements ScannedInterface {

    //declaring variables
    private GifImageView loc_place_scanner_line;
    private RecyclerView scanning_recyclerView;
    private EditText scanResult;

    private PutAway_scanner_adapter adapter;
    private Context context;
    private ApiService apiService;
    private String loc_no,PP_Number;
    private SharedPreferenceConfig config;
    private ArrayList<BarcodeResults> barcodeResults=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_placement);

        context=LocationPlacement.this;
        config=new SharedPreferenceConfig(this);

        loc_place_scanner_line=findViewById(R.id.loc_place_scanner_line);
        scanning_recyclerView=findViewById(R.id.scanning_recyclerView);
        scanResult=findViewById(R.id.scanResult);

        scanResult.requestFocus();


        gettingAllPendingResults();

        scanResult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scanResult.post(new Runnable() {
                    @Override
                    public void run() {

                        //  AppUtils.hideKeyboard(context,scanResult);
                        final InputMethodManager imm = (InputMethodManager) scanResult.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        //scanResult.requestFocus(); // needed if you have more then one input
                    }
                });

                return false;
            }
        });

        scanResult.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (scanResult.getText().toString()!=null){
                        callService(scanResult.getText().toString());
                    }
                }

            }
        });

    }

    private void gettingAllPendingResults() {

        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<BarcodeResponce> call=apiService.getAllBarcodeResults();
            call.enqueue(new Callback<BarcodeResponce>() {
                @Override
                public void onResponse(Call<BarcodeResponce> call, Response<BarcodeResponce> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            if (response.body().getPalatte_Details().size()!=0){
                                adapter=new PutAway_scanner_adapter(getApplicationContext(),response.body().getPalatte_Details(), (ScannedInterface) context);
                                scanning_recyclerView.setHasFixedSize(true);
                                scanning_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                //  Log.e("locations",response.body().getLocations().toString());
                                scanning_recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else {
                                Log.e("palate details","empty");
                            }

                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<BarcodeResponce> call, Throwable t) {
                    Log.e("status",t.toString());
                    scanResult.setText("");
                    scanResult.requestFocus();
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(context,getString(R.string.error_network));
        }
    }

    private void callService(String s) {
        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<BarcodeResponce> call=apiService.scanbarcode(s);
            call.enqueue(new Callback<BarcodeResponce>() {
                @Override
                public void onResponse(Call<BarcodeResponce> call, Response<BarcodeResponce> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());
                            scanResult.setText("");
                            scanResult.requestFocus();

                            if (response.body().getPalatte_Details().size()!=0){
                                adapter=new PutAway_scanner_adapter(getApplicationContext(),response.body().getPalatte_Details(),(ScannedInterface) context);
                                scanning_recyclerView.setHasFixedSize(true);
                                scanning_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                //  Log.e("locations",response.body().getLocations().toString());
                                scanning_recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }else {
                                scanResult.setText("");
                                scanResult.requestFocus();
                                Log.e("palate details","empty");
                            }


                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            scanResult.setText("");
                            scanResult.requestFocus();
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    }
                    else {
                        scanResult.setText("");
                        scanResult.requestFocus();
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<BarcodeResponce> call, Throwable t) {
                    Log.e("status",t.toString());
                    scanResult.setText("");
                    scanResult.requestFocus();
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(context,getString(R.string.error_network));
        }
    }

    @Override
    public void onButtonClick(String pp_num, String locNum) {
        AppUtils.showCustomOkCancelDialog(context, "",context.getString(R.string.upload), "YES", "NO",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveBarcodeDetails(pp_num,locNum) ;
                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });




    }

    private void saveBarcodeDetails(String pp_num, String locNum) {

        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<BarcodeResponce> call=apiService.saveScanDetails(pp_num,locNum,config.readLoginEmpId());
            call.enqueue(new Callback<BarcodeResponce>() {
                @Override
                public void onResponse(Call<BarcodeResponce> call, Response<BarcodeResponce> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());
                            scanResult.setText("");
                            scanResult.requestFocus();


                            adapter=new PutAway_scanner_adapter(getApplicationContext(),response.body().getPalatte_Details(),(ScannedInterface) context);
                            scanning_recyclerView.setHasFixedSize(true);
                            scanning_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            //  Log.e("locations",response.body().getLocations().toString());
                            scanning_recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();



                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            scanResult.setText("");
                            scanResult.requestFocus();
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                        }
                    }
                    else {
                        scanResult.setText("");
                        scanResult.requestFocus();
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<BarcodeResponce> call, Throwable t) {
                    Log.e("status",t.toString());
                    scanResult.setText("");
                    scanResult.requestFocus();
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });
        }else {
            AppUtils.showToast(context,getString(R.string.error_network));
        }
    }
}
