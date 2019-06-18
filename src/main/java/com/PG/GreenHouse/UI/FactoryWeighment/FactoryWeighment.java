package com.PG.GreenHouse.UI.FactoryWeighment;

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
import com.PG.GreenHouse.Adapters.GridViewAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetailsInserted;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.UI.ValueEdition;
import com.PG.GreenHouse.UI.ValueEditionDetails;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentGridModel;
import com.PG.GreenHouse.model.GettingProcesses;
import com.PG.GreenHouse.model.Processes_data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FactoryWeighment extends BaseActivity implements OnRadioButtonClick {
    //Widgets
    private RecyclerView valueEdition_recycler_view_bsd_lots;
    private TextView value_edition_next_btn;
    private ImageView back_button_val_edt;
    private Context context;

    private FactoryWeighmentGridAdapter adapter;
    private ApiService apiService;

    private SharedPreferenceConfig config;
    private FTLotNumbers lotNumbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_weighment);

        context=FactoryWeighment.this;
        config=new SharedPreferenceConfig(this);

        valueEdition_recycler_view_bsd_lots=findViewById(R.id.ftwt_recycler_view_bsd_lots);
        value_edition_next_btn=findViewById(R.id.ftwt_next_btn);
        back_button_val_edt=findViewById(R.id.back_button_ftwt);

        value_edition_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lotNumbers!=null){
                    Intent val_details=new Intent(FactoryWeighment.this,FactoryWeighmentDetails.class);
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
            Call<FactoryWeighmentGridModel> call=apiService.ftLotDetails(config.readLoginEmpId());
            call.enqueue(new Callback<FactoryWeighmentGridModel>() {
                @Override
                public void onResponse(Call<FactoryWeighmentGridModel> call, Response<FactoryWeighmentGridModel> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(context,response.body().getMessage());

                            //  Log.e("locations",response.body().getLocations().toString());
                            adapter=new FactoryWeighmentGridAdapter(context, (OnRadioButtonClick) context,response.body().getLot_Numbers());
                            valueEdition_recycler_view_bsd_lots.setHasFixedSize(true);
                            valueEdition_recycler_view_bsd_lots.setLayoutManager(new LinearLayoutManager(context));
                            valueEdition_recycler_view_bsd_lots.setAdapter(adapter);
                        }
                        else {
                            value_edition_next_btn.setVisibility(View.GONE   );
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(context, "", response.body().getMessage(), "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent goback=new Intent(FactoryWeighment.this, MenuActivity.class);
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
                public void onFailure(Call<FactoryWeighmentGridModel> call, Throwable t) {
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
    public void onRadioClick(Processes_data processes_data) {

    }

    @Override
    public void onRadioClickFT(FTLotNumbers processes_data) {
        this.lotNumbers=processes_data;
    }

}
