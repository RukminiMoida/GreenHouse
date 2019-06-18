package com.PG.GreenHouse.UI.HeadOnHeadLessGrading;

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
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighmentDetails;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetails;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGradingDetailsInserted;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.FactoryWeighment.ActualCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetCodes;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;
import com.PG.GreenHouse.model.headOnHeadLessGrading.Getcodes_Grouphead;
import com.PG.GreenHouse.model.headOnHeadLessGrading.GroupHeads;
import com.PG.GreenHouse.model.headOnHeadLessGrading.HOHL_location;
import com.PG.GreenHouse.model.headOnHeadLessGrading.Lot_details;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HOHL_weights extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private TextView txt_value_edt_dts_weight_btn_save,txt_value_edt_weight_btn_complete,txt_value_edt_weight_date_time,
            txt_value_edt_weight_group_name,txt_value_edt_weight_total_tare_wt,txt_value_edt_weight_net_weight,txt_ftwt_det_materialGroupName
            ,txt_ftwt_det_veriaty_name,txt_h_l_g_lot_no,txt_h_l_g_count,txt_HOHL_weight_Location;
    private RecyclerView value_edt_weight_recycler_view;
    private Toolbar toolbar;
    private ImageView back_button_val_edt_det;
    private EditText txt_value_edt_weight_no_nets,txt_value_edt_weight_tare_weight,edt_value_edt_total_weight_kgs;
    private Spinner spinner_HOHL_group_head;
    private LinearLayout spinner_layout,spinner_layout_HOHL_groupHead;
    private Lot_details processes_data;
    private String TAG;

    private Context mContext;
    private ApiService apiService;
    private ArrayAdapter<String> countAdapter;
    private ArrayAdapter<String> groupHeadAdapter;
    private SharedPreferenceConfig config;

    private String numberOfNets = "0.0";
    private float tareWeight = 0;
    private float totalWeight = 0;
    private float totTareWeight = 0;
    private float netTotalWeight = 0;
    private String count_code,group_id;
    private String count,group_name;
    private String material_code;
    private String varaity_code;
    private int position,group_position;
    private boolean validate;

    private ArrayList<ActualCodes> codes =  new ArrayList<>();
    private ArrayList<GroupHeads> groupHeads =  new ArrayList<>();

    private HeadLessGrading_2_Grid valueEditionDetailsAdapter;
    private ArrayList<ValueEditionDetaillsModel> valueEditionDetaillsModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hohl_weights);
        TAG = FactoryWeighmentDetails.class.getSimpleName();
        config=new SharedPreferenceConfig(this);
        mContext= HOHL_weights.this;
        processes_data=(Lot_details) getIntent().getSerializableExtra("process");

        txt_value_edt_dts_weight_btn_save=findViewById(R.id.txt_HOHL_dts_weight_btn_save);
        txt_value_edt_weight_btn_complete=findViewById(R.id.txt_HOHL_weight_btn_complete);
        value_edt_weight_recycler_view=findViewById(R.id.HOHL_weight_recycler_view);
        back_button_val_edt_det=findViewById(R.id.back_button_HOHL_d);
      //  spinner_layout=findViewById(R.id.spinner_layout_HOHL);
        spinner_layout_HOHL_groupHead=findViewById(R.id.spinner_layout_HOHL_groupHead);
        txt_ftwt_det_materialGroupName=findViewById(R.id.txt_MG_HOHL_d);
        txt_ftwt_det_veriaty_name=findViewById(R.id.txt_VN_HOHL_d);
        txt_HOHL_weight_Location=findViewById(R.id.txt_HOHL_weight_Location);

        //textViews
        txt_value_edt_weight_date_time=findViewById(R.id.txt_HOHL_weight_date_time);
        txt_value_edt_weight_no_nets=findViewById(R.id.txt_HOHL_weight_no_nets);
        txt_value_edt_weight_tare_weight=findViewById(R.id.txt_HOHL_weight_tare_weight);
        edt_value_edt_total_weight_kgs=findViewById(R.id.edt_HOHL_total_weight_kgs);
        txt_value_edt_weight_total_tare_wt=findViewById(R.id.txt_HOHL_weight_total_tare_wt);
        txt_value_edt_weight_net_weight=findViewById(R.id.txt_HOHL_weight_net_weight);
        txt_h_l_g_lot_no=findViewById(R.id.txt_lot_no_HOHL_d);
        txt_h_l_g_count=findViewById(R.id.txt_count_HOHL_d);


      //  spinner_val_edt_det=findViewById(R.id.spinner_HOHL);
        spinner_HOHL_group_head=findViewById(R.id.spinner_HOHL_group_head);

       setSpinner();
        txt_h_l_g_count.setText(processes_data.getVariety_Count());
        txt_h_l_g_lot_no.setText(processes_data.getLotNo()+"/"+AppUtils.dateFormat(processes_data.getLot_date()));
        txt_ftwt_det_materialGroupName.setText(processes_data.getMaterial_Group_Name());
        txt_ftwt_det_veriaty_name.setText(processes_data.getProduct_Variety_Name());

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

        txt_value_edt_weight_no_nets.addTextChangedListener(new HOHL_weights.GenericTextWatcher(txt_value_edt_weight_no_nets));
        txt_value_edt_weight_tare_weight.addTextChangedListener(new HOHL_weights.GenericTextWatcher(txt_value_edt_weight_tare_weight));

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

        spinner_HOHL_group_head.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                group_name=parent.getSelectedItem().toString();
                group_position=i;

                if (group_name!="Select Group Head"){
                    if (AppUtils.isNetworkAvailable(mContext)){
                        group_id=groupHeads.get(group_position-1).getEmp_id();
                         callServiceForLocation(group_id);
                    }
                    else {
                        AppUtils.showToast(mContext,getString(R.string.error_network));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void callServiceForLocation(String group_id) {
        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);
        Call<HOHL_location> call=apiService.getLocation(group_id);
        call.enqueue(new Callback<HOHL_location>() {
            @Override
            public void onResponse(Call<HOHL_location> call, Response<HOHL_location> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        AppUtils.showToast(mContext,response.body().getMessage());
                        txt_HOHL_weight_Location.setText(response.body().getLocation());
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
            public void onFailure(Call<HOHL_location> call, Throwable t) {
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
      //  final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> list1 = new ArrayList<>();

//        list.clear();
//        list.add("Select count");
        list1.clear();
        list1.add("Select Group Head");
        if (AppUtils.isNetworkAvailable(mContext)){
            AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
            apiService= AppUrl.getApiClient().create(ApiService.class);
            Call<Getcodes_Grouphead> call=apiService.getCodes_heads(processes_data.getVcc(),processes_data.getLotNo());
            call.enqueue(new Callback<Getcodes_Grouphead>() {
                @Override
                public void onResponse(Call<Getcodes_Grouphead> call, Response<Getcodes_Grouphead> response) {
                    AppUtils.dismissCustomProgress(mCustomProgressDialog);
                    if (response.body()!=null){
                        if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                            AppUtils.showToast(mContext,response.body().getMessage());

//                            for (int i=0;i<response.body().getCodes().size();i++){
//                                list.add(response.body().getCodes().get(i).getVcount());
//                            }

                            for (int i=0;i<response.body().getGroup_Head().size();i++){
                                list1.add(response.body().getGroup_Head().get(i).getEmp_Name());
                            }
                         //   codes=response.body().getCodes();
                            groupHeads=response.body().getGroup_Head();
//                            countAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list);
//                            spinner_val_edt_det.setVisibility(View.VISIBLE);
//                            spinner_val_edt_det.setAdapter(countAdapter);

                            groupHeadAdapter = new ArrayAdapter<String>(mContext, R.layout.show_count, list1);
                            spinner_HOHL_group_head.setVisibility(View.VISIBLE);
                            spinner_HOHL_group_head.setAdapter(groupHeadAdapter);
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
                public void onFailure(Call<Getcodes_Grouphead> call, Throwable t) {
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

       // spinner_val_edt_det.setOnItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_HOHL_dts_weight_btn_save:

                    if (group_name != "Select Group Head"){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_HOHL_groupHead.setBackground(getResources().getDrawable(R.drawable.stroke_back_ground_gray));
                        }
                        if (doValidation()) {
                            AppUtils.showCustomOkCancelDialog(this, "","Do you want change  group head?", "No", "Yes",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                          //  count_code=codes.get(position-1).getVcode();
                                            group_id=groupHeads.get(group_position-1).getEmp_id();

                                            ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
                                            detaillsModel.setTime(txt_value_edt_weight_date_time.getText().toString());
                                            detaillsModel.setNo_of_nets(Integer.parseInt(txt_value_edt_weight_no_nets.getText().toString()));
                                            detaillsModel.setTotal_weight(Float.parseFloat(edt_value_edt_total_weight_kgs.getText().toString()));
                                            detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_value_edt_weight_total_tare_wt.getText().toString()));
                                            detaillsModel.setNet_weight(Float.parseFloat(txt_value_edt_weight_net_weight.getText().toString()));
                                            detaillsModel.setCummulative_weight(0);
                                            detaillsModel.setCount_code(processes_data.getVariety_Count());
                                            detaillsModel.setGroup_person(processes_data.getVcc());
                                            detaillsModel.setTeam_no(group_id);

                                            valueEditionDetaillsModel.add(detaillsModel);
                                            valueEditionDetailsAdapter=new HeadLessGrading_2_Grid(getApplicationContext(),valueEditionDetaillsModel,"HOHL");
                                            value_edt_weight_recycler_view.setHasFixedSize(true);
                                            value_edt_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        value_edt_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);
                                            clearText();
                                           // spinner_val_edt_det.setSelection(position);
                                            spinner_HOHL_group_head.setSelection(group_position);

                                            edt_value_edt_total_weight_kgs.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    final InputMethodManager imm = (InputMethodManager) edt_value_edt_total_weight_kgs.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                                    imm.showSoftInput(edt_value_edt_total_weight_kgs, InputMethodManager.SHOW_IMPLICIT);
                                                    edt_value_edt_total_weight_kgs.requestFocus(); // needed if you have more then one input
                                                }
                                            });


                                        }
                                    },
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                        //    count_code=codes.get(position-1).getVcode();
                                            group_id=groupHeads.get(group_position-1).getEmp_id();

                                            ValueEditionDetaillsModel detaillsModel=new ValueEditionDetaillsModel();
                                            detaillsModel.setTime(txt_value_edt_weight_date_time.getText().toString());
                                            detaillsModel.setNo_of_nets(Integer.parseInt(txt_value_edt_weight_no_nets.getText().toString()));
                                            detaillsModel.setTotal_weight(Float.parseFloat(edt_value_edt_total_weight_kgs.getText().toString()));
                                            detaillsModel.setTotal_tare_weight(Float.parseFloat(txt_value_edt_weight_total_tare_wt.getText().toString()));
                                            detaillsModel.setNet_weight(Float.parseFloat(txt_value_edt_weight_net_weight.getText().toString()));
                                            detaillsModel.setCummulative_weight(0);
                                            detaillsModel.setCount_code(processes_data.getVariety_Count());
                                            detaillsModel.setGroup_person(processes_data.getVcc());
                                            detaillsModel.setTeam_no(group_id);

                                            valueEditionDetaillsModel.add(detaillsModel);
                                            valueEditionDetailsAdapter=new HeadLessGrading_2_Grid(getApplicationContext(),valueEditionDetaillsModel,"HOHL");
                                            value_edt_weight_recycler_view.setHasFixedSize(true);
                                            value_edt_weight_recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                            value_edt_weight_recycler_view.setAdapter(valueEditionDetailsAdapter);

                                            clearText();
                                            //spinner_val_edt_det.setSelection(0);
                                            spinner_HOHL_group_head.setSelection(0);
                                        }
                                    });
                        } else {
                            AppUtils.showToast(mContext, "please check fields");
                        }
                    }
                    else {
                        AppUtils.showToast(mContext, "please select Group head");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            spinner_layout_HOHL_groupHead.setBackground(getResources().getDrawable(R.drawable.yellow_background));
                        }
                    }


                break;

            case R.id.txt_HOHL_weight_btn_complete:
                if (valueEditionDetaillsModel.size()!=0){
                    Bundle b = new Bundle();
                    b.putSerializable("objNames", (Serializable) valueEditionDetaillsModel);
                    Intent insertDetails=new Intent(HOHL_weights.this, HOHL_details_inserting.class);
                    insertDetails.putExtras(b);
                    insertDetails.putExtra("process",processes_data);
                    startActivity(insertDetails);
                }else {
                    AppUtils.showToast(mContext,"please fill details");
                }
                break;

            case R.id.back_button_HOHL_d:
                onBackPressed();
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // count=parent.getSelectedItem().toString();
       // count_code=codes.get(position).getVcode();
       // this.position=position;
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
                case R.id.txt_HOHL_weight_no_nets:
                    numberOfNets = text;
                    calculateWeight();
                    break;
                case R.id.txt_HOHL_weight_tare_weight:
                    tareWeight = Float.parseFloat(text);
                    calculateWeight();
                    break;

                case R.id.edt_HOHL_total_weight_kgs:
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
            Toast.makeText(mContext,"Total Weight should be greater than total tare weight",Toast.LENGTH_SHORT).show();

        }

        return validate;
    }

    private void calculateWeight() {
        totTareWeight = Float.parseFloat(numberOfNets) * tareWeight;
        txt_value_edt_weight_total_tare_wt.setText(String.valueOf(Math.round(totTareWeight * 100.0) / 100.0));

        Log.i(TAG, "calculateWeight: " + totalWeight);

        if (totalWeight > totTareWeight) {
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
                        HOHL_weights.super.onBackPressed();
                    }
                });
    }
}
