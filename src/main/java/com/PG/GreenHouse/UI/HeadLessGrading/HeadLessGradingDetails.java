package com.PG.GreenHouse.UI.HeadLessGrading;

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
import android.view.WindowManager;
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
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighmentDetails;
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighmentDetailsInserted;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetGradeCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetGrades;
import com.PG.GreenHouse.model.HeadLessGrading.GetGroupCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetVarietyDetails;
import com.PG.GreenHouse.model.HeadLessGrading.GroupCodes;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.PG.GreenHouse.model.HeadLessGrading.VarietyCodes;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;
import com.PG.GreenHouse.model.headOnHeadLessGrading.HOHL_location;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadLessGradingDetails extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView txt_value_edt_dts_weight_btn_save,txt_value_edt_weight_btn_complete,txt_value_edt_weight_date_time,
            txt_value_edt_weight_group_name,txt_value_edt_weight_total_tare_wt,txt_value_edt_weight_net_weight
            ,txt_h_l_g_lot_no,txt_h_l_g_count,toolbar_heading_HeadLess_det;
    private RecyclerView value_edt_weight_recycler_view;
    private Toolbar toolbar;
    private ImageView back_button_val_edt_det;
    private EditText txt_value_edt_weight_no_nets,txt_value_edt_weight_tare_weight,edt_value_edt_total_weight_kgs;
    private Spinner spinner_val_edt_det,spinner_h_l_VarietyName,spinner_h_l_Grade;
    private LinearLayout spinner_layout,spinner_layout_h_l_VarietyName,spinner_layout_h_l_Grade;
    private Lot_numbers processes_data;
    private String TAG;

    private Context mContext;
    private ApiService apiService;
    private ArrayAdapter<String> countAdapter;
    private ArrayAdapter<String> varietyAdapter;
    private ArrayAdapter<String> gradeAdapter;
    private SharedPreferenceConfig config;

    private String numberOfNets = "0.0";
    private float tareWeight = 0;
    private float totalWeight = 0;
    private float totTareWeight = 0;
    private float netTotalWeight = 0;
    private String count_code,gradeCode;
    private String count,varietyName,gradeNO;
    private String material_code;
    private String varaity_code,status;
    private int position,variety_position,gradePosition;
    private boolean validate;

    private ArrayList<GroupCodes> codes =  new ArrayList<>();
    private ArrayList<VarietyCodes> varietyDetails =  new ArrayList<>();
    private ArrayList<GetGradeCodes> gradeCodes =  new ArrayList<>();


    private HeadLessGrading_2_Grid valueEditionDetailsAdapter;
    private ArrayList<ValueEditionDetaillsModel> valueEditionDetaillsModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_less_grading_details);

        TAG = FactoryWeighmentDetails.class.getSimpleName();
        config=new SharedPreferenceConfig(this);
        mContext=HeadLessGradingDetails.this;
        processes_data=(Lot_numbers) getIntent().getSerializableExtra("process");
        status=getIntent().getExtras().get("status").toString();

        Log.e("tag",status);


        txt_value_edt_dts_weight_btn_save=findViewById(R.id.txt_h_l_g_dts_weight_btn_save);
        txt_value_edt_weight_btn_complete=findViewById(R.id.txt_h_l_g_weight_btn_complete);
        value_edt_weight_recycler_view=findViewById(R.id.h_l_g_weight_recycler_view);
        back_button_val_edt_det=findViewById(R.id.back_button_h_l_g_d);
        spinner_layout=findViewById(R.id.spinner_layout_h_l_g);
        spinner_layout_h_l_VarietyName=findViewById(R.id.spinner_layout_h_l_VarietyName);
        spinner_layout_h_l_Grade=findViewById(R.id.spinner_layout_h_l_Grade);
        txt_h_l_g_count=findViewById(R.id.txt_h_l_g_count);
        toolbar_heading_HeadLess_det=findViewById(R.id.toolbar_heading_HeadLess_det);
        if (status.contains("HL")){
            toolbar_heading_HeadLess_det.setText("HeadLess Grading Weights");
        }else if (status.contains("HO")){
            toolbar_heading_HeadLess_det.setText("HeadOn Grading Weights");
        }


        //textViews
        txt_value_edt_weight_date_time=findViewById(R.id.txt_h_l_g_weight_date_time);
        txt_value_edt_weight_no_nets=findViewById(R.id.txt_h_l_g_weight_no_nets);
        txt_value_edt_weight_tare_weight=findViewById(R.id.txt_h_l_g_weight_tare_weight);
        edt_value_edt_total_weight_kgs=findViewById(R.id.edt_h_l_g_total_weight_kgs);
        txt_value_edt_weight_total_tare_wt=findViewById(R.id.txt_h_l_g_weight_total_tare_wt);
        txt_value_edt_weight_net_weight=findViewById(R.id.txt_h_l_g_weight_net_weight);
        txt_h_l_g_lot_no=findViewById(R.id.txt_h_l_g_lot_no);



        spinner_val_edt_det=findViewById(R.id.spinner_h_l_g);
        spinner_h_l_VarietyName=findViewById(R.id.spinner_h_l_VarietyName);
        spinner_h_l_Grade=findViewById(R.id.spinner_h_l_Grade);

        setSpinner();
        txt_h_l_g_lot_no.setText(processes_data.getLot_No()+" /"+AppUtils.dateFormat(processes_data.getLot_Date()));
        txt_h_l_g_count.setText(processes_data.getVariety_Count());
//        txt_ftwt_det_materialGroupName.setText(processes_data.getMaterial_Group_Name());
//        txt_ftwt_det_veriaty_name.setText(processes_data.getProduct_Variety_Name());

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

        txt_value_edt_weight_no_nets.addTextChangedListener(new HeadLessGradingDetails.GenericTextWatcher(txt_value_edt_weight_no_nets));
        txt_value_edt_weight_tare_weight.addTextChangedListener(new HeadLessGradingDetails.GenericTextWatcher(txt_value_edt_weight_tare_weight));

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

        spinner_h_l_VarietyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                varietyName = parent.getSelectedItem().toString();
                variety_position = position;

                if (varietyName != "Select Variety") {
                    if (AppUtils.isNetworkAvailable(mContext)) {
                        varaity_code = varietyDetails.get(position - 1).getFP_Variety_Code();
                        callServiceForGrade(varaity_code);
                    } else {
                        AppUtils.showToast(mContext, getString(R.string.error_network));
                    }
                }
                else {
                    spinner_h_l_Grade.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_h_l_Grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gradeNO = parent.getSelectedItem().toString();
                gradePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void callServiceForGrade(String varaity_code) {
        final List<String> list1 = new ArrayList<>();
        list1.clear();
        list1.add("Select Grade");

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<GetGrades> call=apiService.hlGetGradeCodes(varaity_code);
        call.enqueue(new Callback<GetGrades>() {
            @Override
            public void onResponse(Call<GetGrades> call, Response<GetGrades> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        AppUtils.showToast(mContext,response.body().getMessage());

                        for (int i=0;i<response.body().getCodes().size();i++){
                            list1.add(response.body().getCodes().get(i).getFP_Production_Grade_No());
                        }
                        gradeCodes=response.body().getCodes();
                        gradeAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list1);
                        spinner_h_l_Grade.setVisibility(View.VISIBLE);
                        spinner_h_l_Grade.setAdapter(gradeAdapter);
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
            public void onFailure(Call<GetGrades> call, Throwable t) {
                Log.e("status",t.toString());
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                AppUtils.showCustomOkDialog(mContext,
                        "",
                        getString(R.string.error_default),
                        "OK", null);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_h_l_g_dts_weight_btn_save:
                if (count != "Select Group") {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout.setBackground(getResources().getDrawable(R.drawable.white_border));
                    }
                    if (varietyName != "Select Variety") {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_h_l_VarietyName.setBackground(getResources().getDrawable(R.drawable.stroke_back_ground_gray));
                        }
                        if (gradeNO != "Select Grade") {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                spinner_layout_h_l_Grade.setBackground(getResources().getDrawable(R.drawable.stroke_back_ground_gray));
                            }
                            if (doValidation()) {
                                AppUtils.showCustomOkCancelDialog(this, "", getString(R.string.next_count_alert), "No", "Yes",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                count_code=codes.get(position-1).getFP_Group_Code();
                                                varaity_code=varietyDetails.get(variety_position-1).getFP_Variety_Code();
                                                gradeCode=gradeCodes.get(gradePosition-1).getFP_Production_Grade_Code();
                                                ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
                                                detaillsModel.setTime(txt_value_edt_weight_date_time.getText().toString());
                                                detaillsModel.setNo_of_nets(Integer.parseInt(txt_value_edt_weight_no_nets.getText().toString()));
                                                detaillsModel.setTotal_weight(Float.parseFloat(edt_value_edt_total_weight_kgs.getText().toString()));
                                                detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_value_edt_weight_total_tare_wt.getText().toString()));
                                                detaillsModel.setNet_weight(Float.parseFloat(txt_value_edt_weight_net_weight.getText().toString()));
                                                detaillsModel.setGroupName(count);
                                                detaillsModel.setGroupCode(count_code);
                                                detaillsModel.setGradeNo(gradeNO);
                                                detaillsModel.setCount_code(txt_h_l_g_count.getText().toString());
                                                detaillsModel.setGradeCode(gradeCode);
                                                detaillsModel.setVarietyCode(varaity_code);
                                                detaillsModel.setVarietyName(varietyName);

                                                valueEditionDetaillsModel.add(detaillsModel);
                                                valueEditionDetailsAdapter=new HeadLessGrading_2_Grid(getApplicationContext(),valueEditionDetaillsModel,"HLG");
                                                value_edt_weight_recycler_view.setHasFixedSize(true);
                                                value_edt_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                value_edt_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);
                                                clearText();

                                                edt_value_edt_total_weight_kgs.requestFocus();

                                                edt_value_edt_total_weight_kgs.post(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        final InputMethodManager imm = (InputMethodManager) edt_value_edt_total_weight_kgs.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                                        imm.showSoftInput(edt_value_edt_total_weight_kgs, InputMethodManager.SHOW_IMPLICIT);
                                                        edt_value_edt_total_weight_kgs.requestFocus(); // needed if you have more then one input
                                                    }
                                                });
//                                                getWindow().setSoftInputMode(
//                                                        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//                                                AppUtils.showkeyboard(mContext,edt_value_edt_total_weight_kgs);
//                                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                                                imm.showSoftInput(edt_value_edt_total_weight_kgs, InputMethodManager.SHOW_IMPLICIT);

                                                spinner_h_l_Grade.setSelection(gradePosition);

                                            }
                                        },
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                count_code=codes.get(position-1

                                                ).getFP_Group_Code();
                                                varaity_code=varietyDetails.get(variety_position-1).getFP_Variety_Code();
                                                gradeCode=gradeCodes.get(gradePosition-1).getFP_Production_Grade_Code();
                                                ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
                                                detaillsModel.setTime(txt_value_edt_weight_date_time.getText().toString());
                                                detaillsModel.setNo_of_nets(Integer.parseInt(txt_value_edt_weight_no_nets.getText().toString()));
                                                detaillsModel.setTotal_weight(Float.parseFloat(edt_value_edt_total_weight_kgs.getText().toString()));
                                                detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_value_edt_weight_total_tare_wt.getText().toString()));
                                                detaillsModel.setNet_weight(Float.parseFloat(txt_value_edt_weight_net_weight.getText().toString()));
                                                detaillsModel.setGroupName(count);
                                                detaillsModel.setGroupCode(count_code);
                                                detaillsModel.setGradeNo(gradeNO);
                                                detaillsModel.setCount_code(txt_h_l_g_count.getText().toString());
                                                detaillsModel.setGradeCode(gradeCode);
                                                detaillsModel.setVarietyCode(varaity_code);
                                                detaillsModel.setVarietyName(varietyName);


                                                valueEditionDetaillsModel.add(detaillsModel);
                                                valueEditionDetailsAdapter=new HeadLessGrading_2_Grid(getApplicationContext(),valueEditionDetaillsModel,"HLG");
                                                value_edt_weight_recycler_view.setHasFixedSize(true);
                                                value_edt_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                                value_edt_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);

                                                clearText();
                                                spinner_h_l_Grade.setSelection(0);
                                            }
                                        });
                            } else {
                                AppUtils.showToast(mContext, "please check fields");
                            }
                        }
                        else {
                            AppUtils.showToast(mContext, "please select Grade");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                spinner_layout_h_l_Grade.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                            }
                        }

                    }
                    else {
                        AppUtils.showToast(mContext, "please select Variety");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_h_l_VarietyName.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                        }
                    }

                } else {
                    AppUtils.showToast(mContext, "please select count");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        spinner_layout.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                    }
                }
                break;

            case R.id.txt_h_l_g_weight_btn_complete:

                    if (valueEditionDetaillsModel.size()!=0){
                        Bundle b = new Bundle();
                        b.putSerializable("objNames", (Serializable) valueEditionDetaillsModel);
                        Intent insertDetails=new Intent(HeadLessGradingDetails.this, HeadLessGradingDetailsInserted.class);
                        insertDetails.putExtras(b);
                        insertDetails.putExtra("process",processes_data);
                        insertDetails.putExtra("status",status);
                        startActivity(insertDetails);

                    }else {
                        AppUtils.showToast(mContext,"please fill details");
                    }



                break;

            case R.id.back_button_h_l_g_d:
                onBackPressed();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        count = parent.getSelectedItem().toString();
        this.position = position;

        if (count != "Select Group") {
            if (AppUtils.isNetworkAvailable(mContext)) {
                count_code = codes.get(position - 1).getFP_Group_Code();
                callServiceForVariety(count_code);
            } else {
                AppUtils.showToast(mContext, getString(R.string.error_network));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void callServiceForVariety(String group_id) {

        final List<String> list1 = new ArrayList<>();
        list1.clear();
        list1.add("Select Variety");

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<GetVarietyDetails> call=apiService.hlGetVarityCodes(group_id);
        call.enqueue(new Callback<GetVarietyDetails>() {
            @Override
            public void onResponse(Call<GetVarietyDetails> call, Response<GetVarietyDetails> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        AppUtils.showToast(mContext,response.body().getMessage());

                        for (int i=0;i<response.body().getCodes().size();i++){
                            list1.add(response.body().getCodes().get(i).getFP_Variety_Name());
                        }
                        varietyDetails=response.body().getCodes();
                        varietyAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list1);
                        spinner_h_l_VarietyName.setVisibility(View.VISIBLE);
                        spinner_h_l_VarietyName.setAdapter(varietyAdapter);
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
            public void onFailure(Call<GetVarietyDetails> call, Throwable t) {
                Log.e("status",t.toString());
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                AppUtils.showCustomOkDialog(mContext,
                        "",
                        getString(R.string.error_default),
                        "OK", null);
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
        list.add("Select Group");

        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<GetGroupCodes> call=apiService.hlGetFinishedGroups();
            call.enqueue(new Callback<GetGroupCodes>() {
                @Override
                public void onResponse(Call<GetGroupCodes> call, Response<GetGroupCodes> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());

                            for (int i=0;i<response.body().getCodes().size();i++){
                                list.add(response.body().getCodes().get(i).getFP_Group_Name());
                            }
                            codes=response.body().getCodes();
                            countAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list);
                            spinner_val_edt_det.setVisibility(View.VISIBLE);
                            spinner_val_edt_det.setAdapter(countAdapter);
                        }
                        else {
                            Log.e("status",response.body().getMessage());
                            AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                        }
                    }
                    else {
                        AppUtils.showCustomOkDialog(mContext,"",getResources().getString(R.string.error_default),"OK",null);
                    }
                }

                @Override
                public void onFailure(Call<GetGroupCodes> call, Throwable t) {
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
                case R.id.txt_h_l_g_weight_no_nets:
                    numberOfNets = text;
                    calculateWeight();
                    break;
                case R.id.txt_h_l_g_weight_tare_weight:
                    tareWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;

                case R.id.edt_h_l_g_total_weight_kgs:
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
            Toast.makeText(mContext,"Total Weight should  be greater than total tare weight",Toast.LENGTH_SHORT).show();

        }

        return validate;
    }

    private void calculateWeight() {
        totTareWeight = Float.parseFloat(numberOfNets) * tareWeight;
        txt_value_edt_weight_total_tare_wt.setText(String.valueOf(Math.round(totTareWeight * 100.0) / 100.0));

        Log.i(TAG, "calculateWeight: " + totalWeight);

        if (totalWeight >totTareWeight) {
            Log.i(TAG, "calculateWeight: Inside " + totalWeight);
            netTotalWeight =  totalWeight-totTareWeight;
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
                        HeadLessGradingDetails.super.onBackPressed();
                    }
                });
    }
}
