package com.PG.GreenHouse.UI.HeadLessGrading;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentGridAdapter;
import com.PG.GreenHouse.Adapters.HeadLessGrading.HeadLessGradingGridAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.InterFace.HeadlessGradinRadioClick;
import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighment;
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighmentDetails;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentGridModel;
import com.PG.GreenHouse.model.HeadLessGrading.LotNumbersStatus;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadLessGrading extends BaseActivity implements HeadlessGradinRadioClick{

    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;
    private Context context;

    private HeadLessGradingGridAdapter adapter;
    private ApiService apiService;

    private SharedPreferenceConfig config;
    private Lot_numbers lotNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_less_grading);

        context= HeadLessGrading.this;
        config=new SharedPreferenceConfig(this);

        valueEdition_recycler_view_bsd_lots=findViewById(R.id.h_l_g_recycler_view_bsd_lots);
        value_edition_next_btn=findViewById(R.id.h_l_g_next_btn);
        back_button_val_edt=findViewById(R.id.back_button_h_l_g);

        value_edition_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lotNumbers!=null){
                    Intent val_details=new Intent(HeadLessGrading.this, HeadLessGradingDetails.class);
                    val_details.putExtra("process",lotNumbers);
                    val_details.putExtra("status","HL");
                    startActivity(val_details);
                }else {
                    AppUtils.showToast(context,"Please select existing lot number");
                }
            }
        });

        back_button_val_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        callService();
    }

    private void callService() {
        if (AppUtils.isNetworkAvailable(context)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<LotNumbersStatus> call=apiService.HeadLeassLotData(config.readLoginEmpId());
            call.enqueue(new Callback<LotNumbersStatus>() {
                @Override
                public void onResponse(Call<LotNumbersStatus> call, Response<LotNumbersStatus> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            if (response.body().getLot_No().size()!=0){
                                adapter=new HeadLessGradingGridAdapter(context, (HeadlessGradinRadioClick) context,response.body().getLot_No());
                                valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
                                valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(context));
                                valueEdition_recycler_view_bsd_lots.setAdapter(adapter);
                            }
                            //  Log.e("locations",response.body().getLocations().toString());

                        }
                        else {
                            value_edition_next_btn.setVisibility(View.GONE);
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(HeadLessGrading.this, MenuActivity.class);
                                    goback.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(goback);
                                    finish();
                                }
                            });
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<LotNumbersStatus> call, Throwable t) {
                    Log.e("status",t.toString());
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
    public void onRadioClickFT(Lot_numbers processes_data) {
        this.lotNumbers=processes_data;
    }
}
