package com.PG.GreenHouse.UI.FactoryWeighment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.GreenHouse.Adapters.HeadLessGrading.HeadLessGrading_2_Grid;
import com.PG.GreenHouse.Adapters.ValueEditionDetailsAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetails;
import com.PG.GreenHouse.UI.ValueEditionDetails;
import com.PG.GreenHouse.UI.ValueEditionDetailsInserted;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.PG.GreenHouse.model.FactoryWeighment.Codes;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentCodes;
import com.PG.GreenHouse.model.GettingVeriatyCodes;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FactoryWeighmentDetails extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView txt_value_edt_dts_weight_btn_save,txt_value_edt_weight_btn_complete,txt_value_edt_weight_date_time,
            txt_value_edt_weight_group_name,txt_value_edt_weight_total_tare_wt,txt_value_edt_weight_net_weight,txt_ftwt_det_materialGroupName
            ,txt_ftwt_det_veriaty_name;
    private RecyclerView value_edt_weight_recycler_view;
    private Toolbar toolbar;
    private ImageView back_button_val_edt_det;
    private EditText txt_value_edt_weight_no_nets,txt_value_edt_weight_tare_weight,edt_value_edt_total_weight_kgs;
    private Spinner spinner_val_edt_det;
    private LinearLayout spinner_layout;
    private FTLotNumbers processes_data;
    private String TAG;

    private Context mContext;
    private ApiService apiService;
    private ArrayAdapter<String> countAdapter;
    private SharedPreferenceConfig config;

    private String numberOfNets = "0.0";
    private float tareWeight = 0;
    private float totalWeight = 0;
    private float totTareWeight = 0;
    private float netTotalWeight = 0;
    private String count_code;
    private String count;
    private String material_code;
    private String varaity_code;
    private int position;
    private boolean validate;

    private ArrayList<ActualCodes> codes =  new ArrayList<>();

    private ArrayList<ActualCodes> factoryWeighmentCodes;

    private HeadLessGrading_2_Grid valueEditionDetailsAdapter;
    private ArrayList<ValueEditionDetaillsModel> valueEditionDetaillsModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_weighment_details);

        TAG = FactoryWeighmentDetails.class.getSimpleName();
        config=new SharedPreferenceConfig(this);
        mContext=FactoryWeighmentDetails.this;
        processes_data=(FTLotNumbers) getIntent().getSerializableExtra("process");

        txt_value_edt_dts_weight_btn_save=findViewById(R.id.txt_ftwt_det_dts_weight_btn_save);
        txt_value_edt_weight_btn_complete=findViewById(R.id.txt_ftwt_det_weight_btn_complete);
        value_edt_weight_recycler_view=findViewById(R.id.ftwt_det_weight_recycler_view);
        back_button_val_edt_det=findViewById(R.id.back_button_ftwt_det);
        spinner_layout=findViewById(R.id.spinner_layout);
        txt_ftwt_det_materialGroupName=findViewById(R.id.txt_ftwt_det_materialGroupName);
        txt_ftwt_det_veriaty_name=findViewById(R.id.txt_ftwt_det_veriaty_name);

        txt_ftwt_det_materialGroupName.setText(processes_data.getMaterial_Group_Name());
        txt_ftwt_det_veriaty_name.setText(processes_data.getProduct_Variety_Name());

        //textViews
        txt_value_edt_weight_date_time=findViewById(R.id.txt_ftwt_det_weight_date_time);
        txt_value_edt_weight_no_nets=findViewById(R.id.txt_ftwt_det_weight_no_nets);
        txt_value_edt_weight_tare_weight=findViewById(R.id.txt_ftwt_det_weight_tare_weight);
        edt_value_edt_total_weight_kgs=findViewById(R.id.edt_ftwt_det_total_weight_kgs);
        txt_value_edt_weight_total_tare_wt=findViewById(R.id.txt_ftwt_det_weight_total_tare_wt);
        txt_value_edt_weight_net_weight=findViewById(R.id.txt_ftwt_det_weight_net_weight);
       // txt_ftwt_det_variety_count=findViewById(R.id.txt_ftwt_det_variety_count);

       // txt_ftwt_det_variety_count.setText(processes_data.getVarietycount());

        spinner_val_edt_det=findViewById(R.id.spinner_ftwt_det);

        setSpinner();
        CountDownTimer newtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                String saveCurrentDate;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                saveCurrentDate=currentDate.format(c.getTime());
                txt_value_edt_weight_date_time.setText(saveCurrentDate);
            }
            public void onFinish() {

            }
        };
        newtimer.start();

        txt_value_edt_dts_weight_btn_save.setOnClickListener(this);
        txt_value_edt_weight_btn_complete.setOnClickListener(this);
        back_button_val_edt_det.setOnClickListener(this);

        txt_value_edt_weight_no_nets.addTextChangedListener(new FactoryWeighmentDetails.GenericTextWatcher(txt_value_edt_weight_no_nets));
        txt_value_edt_weight_tare_weight.addTextChangedListener(new FactoryWeighmentDetails.GenericTextWatcher(txt_value_edt_weight_tare_weight));

        edt_value_edt_total_weight_kgs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String manualTotalWeight = editable.toString().trim();
                if (manualTotalWeight != null && !manualTotalWeight.isEmpty()) {
                    totalWeight = Float.parseFloat(manualTotalWeight);
                    calculateWeight();
                }

            }
        });
    }

    private void clearText() {
        edt_value_edt_total_weight_kgs.setText("");
        txt_value_edt_weight_total_tare_wt.setText("");
        txt_value_edt_weight_net_weight.setText("");
        edt_value_edt_total_weight_kgs.requestFocus();

    }

    private void setSpinner() {
        final List<String> list = new ArrayList<>();
        list.clear();
        list.add("Select count");
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<FactoryWeighmentCodes> call=apiService.ftgetCodes(processes_data.getLot_No(),config.readLoginEmpId());
            call.enqueue(new Callback<FactoryWeighmentCodes>() {
                @Override
                public void onResponse(Call<FactoryWeighmentCodes> call, Response<FactoryWeighmentCodes> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());

                            for (int i=0;i<response.body().getData().getVariety_Count().size();i++){
                                list.add(response.body().getData().getVariety_Count().get(i).getVcount());
                            }
                            codes=response.body().getData().getVariety_Count();
                            factoryWeighmentCodes=response.body().getData().getVariety_Count();
                            countAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list);
                            spinner_val_edt_det.setVisibility(View.VISIBLE);
                            spinner_val_edt_det.setAdapter(countAdapter);
                            txt_ftwt_det_materialGroupName.setText(response.body().getData().getMaterial_Group_Name());
                            txt_ftwt_det_veriaty_name.setText(response.body().getData().getProduct_Variety_Name());
                            material_code=response.body().getData().getFk_Material_Group_Code();
                            varaity_code=response.body().getData().getProduct_Variety_Code();
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
                public void onFailure(Call<FactoryWeighmentCodes> call, Throwable t) {
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

        spinner_val_edt_det.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_ftwt_det_dts_weight_btn_save:
                if (count != "Select count") {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout.setBackground(getResources().getDrawable(R.drawable.white_border));
                    }
                    if (doValidation()) {
                        AppUtils.showCustomOkCancelDialog(this, "", "Do you want to change count?", "No", "Yes",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        count_code=codes.get(position-1).getVcode();
                                        ValueEditionDetaillsModel detaillsModel = new ValueEditionDetaillsModel();
                                        detaillsModel.setTime(txt_value_edt_weight_date_time.getText().toString());
                                        detaillsModel.setNo_of_nets(Integer.parseInt(txt_value_edt_weight_no_nets.getText().toString()));
                                        detaillsModel.setTotal_weight(Float.parseFloat(edt_value_edt_total_weight_kgs.getText().toString()));
                                        detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_value_edt_weight_total_tare_wt.getText().toString()));
                                        detaillsModel.setNet_weight(Float.parseFloat(txt_value_edt_weight_net_weight.getText().toString()));
                                        detaillsModel.setCummulative_weight(0);
                                        detaillsModel.setGroup_person(count);
                                        detaillsModel.setCount_code(count_code);

                                        valueEditionDetaillsModel.add(detaillsModel);
                                        valueEditionDetailsAdapter = new HeadLessGrading_2_Grid(getApplicationContext(), valueEditionDetaillsModel, "FW");
                                        value_edt_weight_recycler_view.setHasFixedSize(true);
                                        value_edt_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        value_edt_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);
                                        clearText();
                                        edt_value_edt_total_weight_kgs.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                final InputMethodManager imm = (InputMethodManager) edt_value_edt_total_weight_kgs.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                                imm.showSoftInput(edt_value_edt_total_weight_kgs, InputMethodManager.SHOW_IMPLICIT);
                                                edt_value_edt_total_weight_kgs.requestFocus(); // needed if you have more then one input
                                            }
                                        });
                                          spinner_val_edt_det.setSelection(position);

                                    }
                                },
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        count_code=codes.get(position-1).getVcode();
                                        ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
                                        detaillsModel.setTime(txt_value_edt_weight_date_time.getText().toString());
                                        detaillsModel.setNo_of_nets(Integer.parseInt(txt_value_edt_weight_no_nets.getText().toString()));
                                        detaillsModel.setTotal_weight(Float.parseFloat(edt_value_edt_total_weight_kgs.getText().toString()));
                                        detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_value_edt_weight_total_tare_wt.getText().toString()));
                                        detaillsModel.setNet_weight(Float.parseFloat(txt_value_edt_weight_net_weight.getText().toString()));
                                        detaillsModel.setCummulative_weight(0);
                                        detaillsModel.setGroup_person(count);
                                        detaillsModel.setCount_code(count_code);

                                        valueEditionDetaillsModel.add(detaillsModel);
                                        valueEditionDetailsAdapter=new HeadLessGrading_2_Grid(getApplicationContext(),valueEditionDetaillsModel,"FW");
                                        value_edt_weight_recycler_view.setHasFixedSize(true);
                                        value_edt_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        value_edt_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);

                                        clearText();
                                        spinner_val_edt_det.setSelection(0);
                                    }
                                });
                    } else {
                        AppUtils.showToast(mContext, "please check fields");
                    }

                } else {
                    AppUtils.showToast(mContext, "please select count");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                    }
                }


                break;

            case R.id.txt_ftwt_det_weight_btn_complete:
                if (valueEditionDetaillsModel.size()!=0){
                    Bundle b = new Bundle();
                    b.putSerializable("objNames", (Serializable) valueEditionDetaillsModel);
                    b.putSerializable("objNames1", (Serializable) factoryWeighmentCodes);
                    Intent insertDetails=new Intent(FactoryWeighmentDetails.this, FactoryWeighmentDetailsInserted.class);
                    insertDetails.putExtras(b);
                    insertDetails.putExtra("process",processes_data);
                    insertDetails.putExtra("material_name",material_code);
                    insertDetails.putExtra("varaity_name",varaity_code);
                    startActivity(insertDetails);
                }else {
                    AppUtils.showToast(mContext,"please fill details");
                }
                break;

            case R.id.back_button_ftwt_det:
                onBackPressed();
                break;
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        count=parent.getSelectedItem().toString();
        this.position=position;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Handling multiple edit text
    class GenericTextWatcher implements TextWatcher {

        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d("TextEmpty", "1: " + charSequence.toString() + "," + i + "," + i1 + "," + i2);
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.d("TextEmpty", "2: " + charSequence.toString() + "," + i + "," + i1 + "," + i2);
        }

        public void afterTextChanged(Editable editable) {
            String text;
            if (editable.toString() != null && !editable.toString().equals("")) {
                text = editable.toString();
            } else {
                text = "0.0";
            }
            switch (view.getId()) {
                case R.id.txt_ftwt_det_weight_no_nets:
                    numberOfNets = text;
                    calculateWeight();
                    break;
                case R.id.txt_ftwt_det_weight_tare_weight:
                    tareWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;

                case R.id.edt_ftwt_det_total_weight_kgs:
                    Log.i(TAG, "afterTextChanged: " + text);
                    totalWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;
            }
        }
    }


    boolean doValidation() {
        validate = true;
        if (txt_value_edt_weight_no_nets.getText().toString().trim().length() == 0) {
            validate = false;
            txt_value_edt_weight_no_nets.setError("Enter Number Of Net");
            txt_value_edt_weight_no_nets.requestFocus();

        } else if (txt_value_edt_weight_tare_weight.getText().toString().trim().length() == 0) {
            validate = false;
            txt_value_edt_weight_tare_weight.requestFocus();
            txt_value_edt_weight_tare_weight.setError("Enter Tare Weight");

        } else if (edt_value_edt_total_weight_kgs.getText().toString().trim().length() == 0) {
            validate = false;
            edt_value_edt_total_weight_kgs.requestFocus();
            edt_value_edt_total_weight_kgs.setError("Enter Tare Weight");

        }else if (txt_value_edt_weight_net_weight.getText().toString().trim().length() == 0) {
            validate = false;
            txt_value_edt_weight_net_weight.requestFocus();
            // AppUtils.showToast(mContext,"Total tare wait cannot be less than total weight");
            Toast.makeText(mContext,"Total weight should be greater than total tare weight",Toast.LENGTH_SHORT).show();

        }

        return validate;
    }

    private void calculateWeight() {
        totTareWeight = Float.parseFloat(numberOfNets) * tareWeight;
        txt_value_edt_weight_total_tare_wt.setText(String.valueOf(Math.round(totTareWeight * 100.0) / 100.0));

        Log.i(TAG, "calculateWeight: " + totalWeight);

        if (totalWeight > totTareWeight) {
            Log.i(TAG, "calculateWeight: Inside " + totalWeight);
            netTotalWeight = totalWeight-totTareWeight;
            txt_value_edt_weight_net_weight.setText(String.valueOf(Math.round(netTotalWeight * 100.0) / 100.0));
        } else {
            txt_value_edt_weight_net_weight.setText("");
        }

        Log.d("TextType", "total: " + txt_value_edt_weight_total_tare_wt + " NetWeight: " + txt_value_edt_weight_net_weight);
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        AppUtils.showCustomOkCancelDialog(this, "", "Do you want to go back without saving weights?", "No", "Yes",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FactoryWeighmentDetails.super.onBackPressed();
                    }
                });
    }


}
