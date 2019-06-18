package com.PG.GreenHouse.UI.HeadOnHeadLessGrading;

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

import com.PG.GreenHouse.Adapters.HeadLessGrading.HeadLessGradingDetailsInsertedAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetailsInserted;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.PG.GreenHouse.model.HeadLessGrading.InsertionData;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.PG.GreenHouse.model.Status;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;
import com.PG.GreenHouse.model.headOnHeadLessGrading.InsertDataHOHL;
import com.PG.GreenHouse.model.headOnHeadLessGrading.Lot_details;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HOHL_details_inserting extends BaseActivity {

    //widgets
    private RecyclerView recycler_view_ho_hl_details;
    private ImageView back_button_val_edt_inserted;
    private HeadLessGradingDetailsInsertedAdapter adapter;
    private Lot_details processes_data;
    private TextView tot_no_nets,tot_net_weight,tot_weight;
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
    private ArrayList<String> VAP_Group_Emp_id=new ArrayList<>();
    private ArrayList<String> Variety_Count_Code=new ArrayList<>();

    private Context context;
    private SharedPreferenceConfig config;
    private ArrayList<ActualCodes> detaillsModels1;
    private String materialCode,VarietyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hohl_details_inserting);

        config=new SharedPreferenceConfig(this);
        context= HOHL_details_inserting.this;

        Bundle b = getIntent().getExtras();
        ArrayList<ValueEditionDetaillsModel> detaillsModels = (ArrayList<ValueEditionDetaillsModel>) b.getSerializable("objNames");
        processes_data=(Lot_details) getIntent().getSerializableExtra("process");

        recycler_view_ho_hl_details=findViewById(R.id.recycler_view_HOHL_inserted);
        back_button_val_edt_inserted=findViewById(R.id.back_button_HOHL_inserted);
        btnValue_edition_Upload=findViewById(R.id.btnHOHL_inserted_Upload);

        tot_no_nets=findViewById(R.id.tot_no_nets);
        tot_net_weight=findViewById(R.id.tot_net_weight);
        tot_weight=findViewById(R.id.tot_weight);

        adapter=new HeadLessGradingDetailsInsertedAdapter(this,detaillsModels,"HOHL");
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
           // Variety_Count_Code.add(detaillsModels.get(i).getGroup_person());
            VAP_Group_Emp_id.add(detaillsModels.get(i).getTeam_no());

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
        InsertDataHOHL jsonData=new InsertDataHOHL(
                config.readLoginEmpId(),
                VAP_Net_Weight,
                VAP_No_of_Nets,
                VAP_Net_Tare_Wt,
                VAP_Total_Tare_Weight,
                VAP_Total_Weight,
                date_time,
                processes_data.getVcc(),
                VAP_Group_Emp_id,
                processes_data.getLot_date(),
                processes_data.getLotNo(),
                processes_data.getMaterialGroupCode(),
                processes_data.getPvc());

        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("hohl data ",json);

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<Status> call=apiService.insertHOHLDetails(json);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                Log.e("status","entered");
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE2)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(context,response.body().getMessage());
                        Intent goback=new Intent(HOHL_details_inserting.this, MenuActivity.class);
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
