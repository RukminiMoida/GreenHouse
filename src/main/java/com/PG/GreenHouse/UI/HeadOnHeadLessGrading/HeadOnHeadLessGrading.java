package com.PG.GreenHouse.UI.HeadOnHeadLessGrading;

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

import com.PG.GreenHouse.Adapters.HeadLessGrading.HeadLessGradingGridAdapter;
import com.PG.GreenHouse.Adapters.HeadOnHeadlessAdapters.HeadOnHeadlessGridAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.InterFace.HeadOnHeadLessRadioClick;
import com.PG.GreenHouse.InterFace.HeadlessGradinRadioClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGrading;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetails;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetailsInserted;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.HeadLessGrading.LotNumbersStatus;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.PG.GreenHouse.model.headOnHeadLessGrading.GetLotNumbers_HOHL_status;
import com.PG.GreenHouse.model.headOnHeadLessGrading.Lot_details;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadOnHeadLessGrading extends BaseActivity implements HeadOnHeadLessRadioClick {

    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;
    private Context context;

    private ApiService apiService;
    private HeadOnHeadlessGridAdapter adapter;

    private SharedPreferenceConfig config;
    private Lot_details lotNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_on_head_less_grading);

        context= HeadOnHeadLessGrading.this;
        config=new SharedPreferenceConfig(this);

        valueEdition_recycler_view_bsd_lots=findViewById(R.id.HOHL_recycler_view_bsd_lots);
        value_edition_next_btn=findViewById(R.id.HOHL_next_btn);
        back_button_val_edt=findViewById(R.id.back_button_HOHL);

        value_edition_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lotNumbers!=null){
                    Intent val_details=new Intent(HeadOnHeadLessGrading.this, HOHL_weights.class);
                    val_details.putExtra("process",lotNumbers);
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
            Call<GetLotNumbers_HOHL_status> call=apiService.getlotNum_hohl(config.readLoginEmpId());
            call.enqueue(new Callback<GetLotNumbers_HOHL_status>() {
                @Override
                public void onResponse(Call<GetLotNumbers_HOHL_status> call, Response<GetLotNumbers_HOHL_status> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            if (response.body().getLot_No().size()!=0){
                                adapter=new HeadOnHeadlessGridAdapter(context, (HeadOnHeadLessRadioClick) context,response.body().getLot_No());
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
                                    Intent goback=new Intent(HeadOnHeadLessGrading.this, MenuActivity.class);
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
                public void onFailure(Call<GetLotNumbers_HOHL_status> call, Throwable t) {
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
    public void onRadioClickFT(Lot_details processes_data) {
        this.lotNumbers=processes_data;

    }
}
