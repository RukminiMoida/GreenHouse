package com.PG.GreenHouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.UI.MenuActivity;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity  {

    //widgets
    private EditText edt_user_name, edt_password;
    private Button btn_submit;

    private Context mContext;
    private ApiService apiService;
    private SharedPreferenceConfig sharedPreferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = LoginActivity.this;

        sharedPreferenceConfig=new SharedPreferenceConfig(this);

        if (sharedPreferenceConfig.readLoginPrefernce().contains("logged in")){
            Intent login = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(login);
            finish();
        }

        btn_submit = findViewById(R.id.btn_submit);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_password = findViewById(R.id.edt_password);

        //navigating to menu
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callService();
            }
        });

    }

    private void callService() {
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<LoginResponse> call=apiService.agentLogin(edt_user_name.getText().toString().trim(),edt_password.getText().toString().trim());
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            Log.e("status",response.body().getData().getUser_Emp_Id());
                            sharedPreferenceConfig.writeLoginEmpId(response.body().getData().getUser_Emp_Id());
                            AppUtils.showToast(mContext,response.body().getMessage());
                            sharedPreferenceConfig.writeLoginPreference("logged in");
                            Intent login = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(login);
                            finish();
                        }else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(mContext,"",response.body().getMessage(),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
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


}
