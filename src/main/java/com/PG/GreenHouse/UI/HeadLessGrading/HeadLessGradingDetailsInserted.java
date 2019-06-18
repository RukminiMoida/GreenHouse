package com.PG.GreenHouse.UI.HeadLessGrading;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentDetailsInsertion;
import com.PG.GreenHouse.Adapters.HeadLessGrading.HeadLessGradingDetailsInsertedAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighmentDetailsInserted;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.PG.GreenHouse.model.FactoryWeighment.FTInsertionData;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.HeadLessGrading.InsertionData;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.PG.GreenHouse.model.HeadOnGrading.InsertDataHeadon;
import com.PG.GreenHouse.model.Status;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadLessGradingDetailsInserted extends BaseActivity {


    //widgets
    private RecyclerView recycler_view_ho_hl_details;
    private ImageView back_button_val_edt_inserted;
    private HeadLessGradingDetailsInsertedAdapter adapter;
    private Lot_numbers processes_data;
    private TextView tot_no_nets,tot_net_weight,tot_weight,toolbar_heading_hl_details_inserted;
    private FloatingActionButton btnValue_edition_Upload;
    private Button valEdtNextLot;

    private ArrayList<String> list=new ArrayList<>();
    private int no_of_nets;
    private float tot_net_wt,tot_wt;

    private ApiService apiService;

    private ArrayList<String> date_time=new ArrayList<>();
    private ArrayList<String> VAP_No_of_Nets=new ArrayList<>();
    private ArrayList<String> VAP_Net_Tare_Wt=new ArrayList<>();
    private ArrayList<String> VAP_Total_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Total_Tare_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Net_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Group_code=new ArrayList<>();
    private ArrayList<String> Variety_Code=new ArrayList<>();
    private ArrayList<String> GradeCode=new ArrayList<>();

    private Context context;
    private SharedPreferenceConfig config;
    private ArrayList<ActualCodes> detaillsModels1;
    private String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_less_grading_details_inserted);

        config=new SharedPreferenceConfig(this);
        context= HeadLessGradingDetailsInserted.this;

        Bundle b = getIntent().getExtras();
        ArrayList<ValueEditionDetaillsModel> detaillsModels = (ArrayList<ValueEditionDetaillsModel>) b.getSerializable("objNames");
        processes_data=(Lot_numbers) getIntent().getSerializableExtra("process");
        status=getIntent().getExtras().get("status").toString();


        recycler_view_ho_hl_details=findViewById(R.id.recycler_view_h_l_g_d_i_inserted);
        back_button_val_edt_inserted=findViewById(R.id.back_button_h_l_g_d_i_inserted);
        btnValue_edition_Upload=findViewById(R.id.btnh_l_g_d_i_inserted_Upload);
        toolbar_heading_hl_details_inserted=findViewById(R.id.toolbar_heading_hl_details_inserted);

        tot_no_nets=findViewById(R.id.tot_no_nets);
        tot_net_weight=findViewById(R.id.tot_net_weight);
        tot_weight=findViewById(R.id.tot_weight);

        if (status.contains("HL")){
            toolbar_heading_hl_details_inserted.setText("HeadLess Grading Summary");
        }else if (status.contains("HO")){
            toolbar_heading_hl_details_inserted.setText("HeadOn Grading Summary");
        }

        adapter=new HeadLessGradingDetailsInsertedAdapter(this,detaillsModels,"HLG");
        recycler_view_ho_hl_details.setHasFixedSize(true);
        recycler_view_ho_hl_details.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view_ho_hl_details.setAdapter(adapter);

        back_button_val_edt_inserted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        for (int i=0;i<detaillsModels.size();i++){
            date_time.add(detaillsModels.get(i).getTime());
            VAP_No_of_Nets.add(String.valueOf(detaillsModels.get(i).getNo_of_nets()));
            VAP_Net_Tare_Wt.add(String.valueOf(detaillsModels.get(i).getTotal_weight()));
            VAP_Total_Weight.add(String.valueOf(detaillsModels.get(i).getTotal_weight()));
            VAP_Total_Tare_Weight.add(String.valueOf(detaillsModels.get(i).getTotal_tare_weight()));
            VAP_Net_Weight.add(String.valueOf(detaillsModels.get(i).getNet_weight()));
            VAP_Group_code.add(detaillsModels.get(i).getGroupCode());
            Variety_Code.add(detaillsModels.get(i).getVarietyCode());
            GradeCode.add(detaillsModels.get(i).getGradeCode());

            no_of_nets=no_of_nets+detaillsModels.get(i).getNo_of_nets();
            tot_net_wt=tot_net_wt+detaillsModels.get(i).getNet_weight();
            tot_wt=tot_wt+detaillsModels.get(i).getTotal_weight();
        }

        BigDecimal bd = new BigDecimal(tot_wt).setScale(2, RoundingMode.HALF_UP);
        tot_wt= (float) bd.doubleValue();
        BigDecimal bd1 = new BigDecimal(tot_net_wt).setScale(2, RoundingMode.HALF_UP);
        tot_net_wt= (float) bd1.doubleValue();
        tot_no_nets.setText(String.valueOf(no_of_nets));
        tot_net_weight.setText(String.valueOf(tot_net_wt));
        tot_weight.setText(String.valueOf(tot_wt));

        btnValue_edition_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showCustomOkCancelDialog(context, "",getString(R.string.upload), "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (AppUtils.isNetworkAvailable(context)){
                                    callService();
                                }
                                else {
                                    AppUtils.showToast(context,getString(R.string.error_network));
                                }
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
            }
        });
    }

    private void callService() {
        if (status.contains("HL")) {
            InsertionData jsonData = new InsertionData(
                    processes_data.getLot_No(),
                    processes_data.getLot_Date(),
                    VAP_Group_code,
                    Variety_Code,
                    GradeCode,
                    date_time,
                    VAP_No_of_Nets,
                    VAP_Total_Weight,
                    VAP_Net_Tare_Wt,
                    VAP_Total_Tare_Weight,
                    VAP_Net_Weight,
                    config.readLoginEmpId(),
                    processes_data.getFk_Variety_Count_Code());

            Gson gson = new Gson();
            String json = gson.toJson(jsonData);
            Log.e("ft data ", json);

            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<Status> call=apiService.HeadLeassInsertData(json);
            call.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    Log.e("status","entered");
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE2)){
                            Log.e("status",response.body().getMessage());
                            AppUtils.showToast(context,response.body().getMessage());
                            Intent goback=new Intent(HeadLessGradingDetailsInserted.this, MenuActivity.class);
                            goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(goback);
                            finish();

                        }else {
                            Log.e("status","not success");
                            AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                        }
                    }
                    else {
                        Log.e("status","response null");
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });

        } else if (status.contains("HO")) {
                InsertDataHeadon jsonData = new InsertDataHeadon(
                        processes_data.getLot_No(),
                        processes_data.getLot_Date(),
                        VAP_Group_code,
                        Variety_Code,
                        GradeCode,
                        date_time,
                        VAP_No_of_Nets,
                        VAP_Total_Weight,
                        VAP_Net_Tare_Wt,
                        VAP_Total_Tare_Weight,
                        VAP_Net_Weight,
                        config.readLoginEmpId(),
                        processes_data.getFk_Variety_Count_Code());

                Gson gson = new Gson();
                String json = gson.toJson(jsonData);
                Log.e("Head On data ", json);

            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<Status> call=apiService.insert_h_on(json);
            call.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    Log.e("status","entered");
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE2)){
                            Log.e("status",response.body().getMessage());
                            AppUtils.showToast(context,response.body().getMessage());
                            Intent goback=new Intent(HeadLessGradingDetailsInserted.this, MenuActivity.class);
                            goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(goback);
                            finish();

                        }else {
                            Log.e("status","not success");
                            AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                        }
                    }
                    else {
                        Log.e("status","response null");
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Log.e("status",t.toString());
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    AppUtils.showCustomOkDialog(context,
                            "",
                            getString(R.string.error_default),
                            "OK", null);
                }
            });

        }


        }

}
