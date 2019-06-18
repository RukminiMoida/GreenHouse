package com.PG.GreenHouse.UI.Site_Weightment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.GreenHouse.Adapters.SiteWeigmentInsertionAdapter;
import com.PG.GreenHouse.Adapters.ValueEditionInsertDetailsAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.LocalDataBase.DbHelper;
import com.PG.GreenHouse.LoginActivity;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.UI.ValueEdition;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.LoginResponse;
import com.PG.GreenHouse.model.SiteWTInsertResponce;
import com.PG.GreenHouse.model.SiteWeighmentInsertionData;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiteWeighmentDetailsIserted extends BaseActivity {

    private RecyclerView recycler_view_SiteWeighment;
    private SiteWeigmentInsertionAdapter adapter;

    private ImageView back_button_val_edt_inserted,back_button_siteWeighment_inserted;
    private TextView tot_no_nets,tot_net_weight,tot_weight;
    private FloatingActionButton btnValue_edition_Upload;

    private int no_of_nets;
    private float tot_net_wt,tot_wt;

    private ArrayList<String> date_time=new ArrayList<>();
    private ArrayList<String> VAP_No_of_Nets=new ArrayList<>();
    private ArrayList<String> VAP_Net_Tare_Wt=new ArrayList<>();
    private ArrayList<String> VAP_Total_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Total_Tare_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Net_Weight=new ArrayList<>();
    private ArrayList<String> VAP_Group_Emp_id=new ArrayList<>();
    private ArrayList<String> Variety_Count_Code=new ArrayList<>();

    private String scheduleNo,enquiryNO;

    private SharedPreferenceConfig config;
    private Context context;
    private DbHelper dbHelper;

    private ApiService apiService;
    private static DecimalFormat df = new DecimalFormat("0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_site_weighment_details_iserted);

        context=SiteWeighmentDetailsIserted.this;
        dbHelper=new DbHelper(this);

        Bundle b = getIntent().getExtras();
        ArrayList<ValueEditionDetaillsModel> detaillsModels = (ArrayList<ValueEditionDetaillsModel>) b.getSerializable("objNames");

        scheduleNo=getIntent().getExtras().getString("schedule_no");
        enquiryNO=getIntent().getExtras().getString("enquiryNo");

        config=new SharedPreferenceConfig(this);
        recycler_view_SiteWeighment=findViewById(R.id.recycler_view_SiteWeighment);
        back_button_val_edt_inserted=findViewById(R.id.back_button_siteWeighment_inserted);
        btnValue_edition_Upload=findViewById(R.id.btnSiteWeighment_Upload);

        tot_no_nets=findViewById(R.id.SiteWeighmenttot_no_nets);
        tot_net_weight=findViewById(R.id.SiteWeighmenttot_net_weight);
        tot_weight=findViewById(R.id.SiteWeighmenttot_weight);
        back_button_siteWeighment_inserted=findViewById(R.id.back_button_siteWeighment_inserted);

        back_button_siteWeighment_inserted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        adapter=new SiteWeigmentInsertionAdapter(this,detaillsModels);
        recycler_view_SiteWeighment.setHasFixedSize(true);
        recycler_view_SiteWeighment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view_SiteWeighment.setAdapter(adapter);

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
            VAP_Group_Emp_id.add(detaillsModels.get(i).getGroup_person());
            Variety_Count_Code.add(detaillsModels.get(i).getCount_code());

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

        Log.e("scheduleNo",scheduleNo);
        Log.e("enquiry no",enquiryNO);
        Log.e("pond",config.readSiteWeighmentPond_no());
        Log.e("farm location",config.readSiteWeighmentFarmLocation());
        Log.e("map location",config.readSiteWeighmentFarmMapData());

        SiteWeighmentInsertionData insertionData=new SiteWeighmentInsertionData(
                config.readSiteWeighmentPond_no(),
                config.readSiteWeighmentFarmLocation(),
                config.readSiteWeighmentLatitude()+","+config.readSiteWeighmentLongitude(),
                scheduleNo,
                enquiryNO,
                VAP_No_of_Nets,
                VAP_Total_Weight,
                VAP_Net_Tare_Wt,
                VAP_Total_Tare_Weight,
                VAP_Net_Weight,
                config.readLoginEmpId(),
                date_time,
                Variety_Count_Code);
        Gson gson = new Gson();
        final String json = gson.toJson(insertionData);
        Log.e("data ",json);

        btnValue_edition_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtils.isNetworkAvailable(context)){
                    AppUtils.showCustomOkCancelDialog(context, "", getString(R.string.upload), "Ok", "Cancel",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                    AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
                    apiService= AppUrl.getApiClient().create(ApiService.class);
                    Call<SiteWTInsertResponce> call=apiService.insertSiteData(json);
                    call.enqueue(new Callback<SiteWTInsertResponce>() {
                        @Override
                        public void onResponse(Call<SiteWTInsertResponce> call, Response<SiteWTInsertResponce> response) {
                            AppUtils.dismissCustomProgress(mCustomProgressDialog);
                            if (response.body()!=null){
                                if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                                    AppUtils.showToast(context,response.body().getMessage());
                                    Intent intent=new Intent(SiteWeighmentDetailsIserted.this,MenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Log.e("status",response.body().getMessage());
                                    AppUtils.showCustomOkDialog(context,"",response.body().getMessage(),"OK",null);
                                }
                            }
                            else {
                                AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                            }
                        }
                        @Override
                        public void onFailure(Call<SiteWTInsertResponce> call, Throwable t) {
                            Log.e("status",t.toString());
                            AppUtils.dismissCustomProgress(mCustomProgressDialog);
                            AppUtils.showCustomOkDialog(context,
                                    "",
                                    getString(R.string.error_default),
                                    "OK", null);
                        }
                    });

                                }
                            },
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });

                }else {
                    for (int i=0;i<VAP_No_of_Nets.size();i++){

                        if (dbHelper.insertSiteWeighmentDetails(config.readLoginEmpId(),
                                config.readSiteWeighmentPond_no(),
                                config.readSiteWeighmentFarmLocation(),
                                config.readSiteWeighmentLatitude()+","+config.readSiteWeighmentLongitude(),
                                scheduleNo,
                                enquiryNO,
                                VAP_No_of_Nets.get(i),
                                VAP_Total_Weight.get(i),
                                VAP_Net_Tare_Wt.get(i),
                                VAP_Total_Tare_Weight.get(i),
                                VAP_Net_Weight.get(i),
                                date_time.get(i),
                                Variety_Count_Code.get(i)

                        )){
                            Toast.makeText(getApplicationContext(),"Data inserted locally",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SiteWeighmentDetailsIserted.this,MenuActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    }

                }
            }
        });




    }
}
