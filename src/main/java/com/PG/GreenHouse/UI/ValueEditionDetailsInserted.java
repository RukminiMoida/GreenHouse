package com.PG.GreenHouse.UI;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.ValueEditionDetailsAdapter;
import com.PG.GreenHouse.Adapters.ValueEditionInsertDetailsAdapter;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.LocalDataBase.DbHelper;
import com.PG.GreenHouse.LoginActivity;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.JsonData;
import com.PG.GreenHouse.model.LoginResponse;
import com.PG.GreenHouse.model.Processes_data;
import com.PG.GreenHouse.model.Status;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ValueEditionDetailsInserted extends BaseActivity {
    //widgets
    private RecyclerView recycler_view_ho_hl_details;
    private ImageView back_button_val_edt_inserted;
    private ValueEditionInsertDetailsAdapter adapter;
    private Processes_data processes_data;
    private TextView tot_no_nets,tot_net_weight,tot_weight;
    private FloatingActionButton btnValue_edition_Upload;
    private Button valEdtNextLot;

    private ArrayList<String> list=new ArrayList<>();
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

    private ArrayList<String> date_timeFromDb=new ArrayList<>();
    private ArrayList<String> VAP_No_of_NetsFromDb=new ArrayList<>();
    private ArrayList<String> VAP_Net_Tare_WtFromDb=new ArrayList<>();
    private ArrayList<String> VAP_Total_WeightFromDb=new ArrayList<>();
    private ArrayList<String> VAP_Total_Tare_WeightFromDb=new ArrayList<>();
    private ArrayList<String> VAP_Net_WeightFromDb=new ArrayList<>();
    private ArrayList<String> VAP_Group_Emp_idFromDb=new ArrayList<>();
    private ArrayList<String> Variety_Count_CodeFromDb=new ArrayList<>();

    private String Lot_no_fromDB;
    private String Office_no_db_fromDB;
    private String Process_code_fromDB;
    private String Schedule_no_fromDB;
    private String Process_no_fromDB;
    private String Table_no_fromDB;
    private double Tot_wt_fromDB;
    private String Weight_no_fromDB;
    private String Worker_nos_fromDB;



    private Context context;
    private ApiService apiService;

    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_edition_details_inserted);

        context=ValueEditionDetailsInserted.this;

        dbHelper=new DbHelper(this);

        Bundle b = getIntent().getExtras();
        ArrayList<ValueEditionDetaillsModel> detaillsModels = (ArrayList<ValueEditionDetaillsModel>) b.getSerializable("objNames");
        processes_data=(Processes_data) getIntent().getSerializableExtra("process");

        recycler_view_ho_hl_details=findViewById(R.id.recycler_view_ho_hl_details);
        back_button_val_edt_inserted=findViewById(R.id.back_button_val_edt_inserted);
        btnValue_edition_Upload=findViewById(R.id.btnValue_edition_Upload);
        valEdtNextLot=findViewById(R.id.valEdtNextLot);

        tot_no_nets=findViewById(R.id.tot_no_nets);
        tot_net_weight=findViewById(R.id.tot_net_weight);
        tot_weight=findViewById(R.id.tot_weight);

        adapter=new ValueEditionInsertDetailsAdapter(this,detaillsModels);
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

        valEdtNextLot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.showCustomOkCancelDialog(context, "",getString(R.string.NextLot), "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (int i=0;i<VAP_No_of_Nets.size();i++){
                                    dbHelper.insertDetails(processes_data.getLot_no(),processes_data.getOffice_no(),processes_data.getProcess_code(),processes_data.getProcess_schedule_no()
                                            ,VAP_Group_Emp_id.get(i),String.valueOf(tot_wt),VAP_Net_Tare_Wt.get(i),VAP_Net_Weight.get(i),VAP_No_of_Nets.get(i),
                                            processes_data.getProcess_no(),"14",processes_data.getTables_no(),VAP_Total_Tare_Weight.get(i),
                                            VAP_Total_Weight.get(i),date_time.get(i),processes_data.getWeight_no(),processes_data.getWorkers_nos(),Variety_Count_Code.get(i),"12.0");
                                }

                                Intent nextLot=new Intent(ValueEditionDetailsInserted.this,ValueEdition.class);
                                startActivity(nextLot);
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

        for (int i=0;i<VAP_No_of_Nets.size();i++){
            if (dbHelper.insertDetails(processes_data.getLot_no(),processes_data.getOffice_no(),processes_data.getProcess_code(),processes_data.getProcess_schedule_no()
                    ,VAP_Group_Emp_id.get(i),String.valueOf(tot_wt),VAP_Net_Tare_Wt.get(i),VAP_Net_Weight.get(i),VAP_No_of_Nets.get(i),
                    processes_data.getProcess_no(),"14",processes_data.getTables_no(),VAP_Total_Tare_Weight.get(i),
                    VAP_Total_Weight.get(i),date_time.get(i),processes_data.getWeight_no(),processes_data.getWorkers_nos(),Variety_Count_Code.get(i),"12.0"))
            {
                Log.e("data ","data inserted");
            }
            else {
                Log.e("data ","data not inserted");
            }
        }

        final Cursor cursor = dbHelper.getAllDetails();
        if (cursor.moveToFirst()){
            do{
                Lot_no_fromDB=cursor.getString(cursor.getColumnIndex("Lot_No"));
                Office_no_db_fromDB=cursor.getString(cursor.getColumnIndex("Org_office_no"));
                Process_code_fromDB=cursor.getString(cursor.getColumnIndex("Product_Process_Code"));
                Schedule_no_fromDB=cursor.getString(cursor.getColumnIndex("Production_Process_Schedule_No"));
                Process_no_fromDB=cursor.getString(cursor.getColumnIndex("VAP_Process_No"));
                Table_no_fromDB=cursor.getString(cursor.getColumnIndex("VAP_Tables_No"));
                Tot_wt_fromDB=cursor.getDouble(cursor.getColumnIndex("VAP_Group_Total_Qty"));
                Weight_no_fromDB=cursor.getString(cursor.getColumnIndex("VAP_Weight_No"));
                Worker_nos_fromDB=cursor.getString(cursor.getColumnIndex("VAP_Workers_Nos"));
                date_timeFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_Weighment_Date_Time")));
                VAP_No_of_NetsFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_No_of_Nets")));
                VAP_Net_Tare_WtFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_Net_Tare_Wt")));
                VAP_Total_WeightFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_Total_Weight")));
                VAP_Total_Tare_WeightFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_Total_Tare_Weight")));
                VAP_Net_WeightFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_Net_Weight")));
                VAP_Group_Emp_idFromDb.add( cursor.getString(cursor.getColumnIndex("VAP_Group_Emp_id")));
                Variety_Count_CodeFromDb.add( cursor.getString(cursor.getColumnIndex("Variety_Count_Code")));
            }while(cursor.moveToNext());

         //   Log.e("list",data.toString());
        }
        cursor.close();

        AppUtils.showCustomProgressDialog(mCustomProgressDialog,"Loading...");
        apiService= AppUrl.getApiClient().create(ApiService.class);

        Log.e("getProcess_schedule_no",Schedule_no_fromDB);
        Log.e("getLot_no",Lot_no_fromDB);
        Log.e("getOffice_no",Office_no_db_fromDB);
        Log.e("getProcess_code",Process_code_fromDB);
        Log.e("getWeight_no",Weight_no_fromDB);
        Log.e("date_time",date_timeFromDb.toString());
        Log.e("getWorkers_nos",Worker_nos_fromDB);
        Log.e("getTables_no",Table_no_fromDB);
        Log.e("VAP_No_of_Nets",VAP_No_of_NetsFromDb.toString());
        Log.e("VAP_Net_Tare_Wt",VAP_Net_Tare_WtFromDb.toString());
        Log.e("VAP_Total_Weight",VAP_Total_WeightFromDb.toString());
        Log.e("VAP_Total_Tare_Weight",VAP_Total_Tare_WeightFromDb.toString());
        Log.e("getProcess_no",Process_no_fromDB);
        Log.e("VAP_Net_Weight",VAP_Net_WeightFromDb.toString());
        Log.e("VAP_Group_Emp_id",VAP_Group_Emp_idFromDb.toString());
        Log.e("tot_wt",String.valueOf(Tot_wt_fromDB));
        Log.e("Variety_Count_Code",Variety_Count_CodeFromDb.toString());

        JsonData jsonData=new JsonData(Schedule_no_fromDB,Lot_no_fromDB,
                Office_no_db_fromDB,Process_code_fromDB,Weight_no_fromDB,
                Worker_nos_fromDB,Table_no_fromDB,"14",
                Process_no_fromDB,12,Tot_wt_fromDB,date_timeFromDb,VAP_No_of_NetsFromDb,VAP_Net_Tare_WtFromDb,VAP_Total_WeightFromDb,
                VAP_Total_Tare_WeightFromDb,VAP_Net_WeightFromDb,VAP_Group_Emp_idFromDb,Variety_Count_CodeFromDb);

        Gson gson = new Gson();
        String json = gson.toJson(jsonData);
        Log.e("data ",json);

//        JsonData jsonData=new JsonData(processes_data.getProcess_schedule_no(),processes_data.getLot_no(),
//                processes_data.getOffice_no(),processes_data.getProcess_code(),processes_data.getWeight_no(),
//                processes_data.getWorkers_nos(),processes_data.getTables_no(),"14",
//                processes_data.getProcess_no(),12,tot_wt,date_time,VAP_No_of_Nets,VAP_Net_Tare_Wt,VAP_Total_Weight,
//                VAP_Total_Tare_Weight,VAP_Net_Weight,VAP_Group_Emp_id,Variety_Count_Code);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(jsonData);
//        Log.e("data ",json);

        Call<Status> call=apiService.insertDetails(json);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                AppUtils.dismissCustomProgress(mCustomProgressDialog);
                Log.e("status","entered");
                if (response.body()!=null){
                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
                        Log.e("status",response.body().getMessage());
                        AppUtils.showToast(context,response.body().getMessage());
                        dbHelper.deleteTable();
                        Intent goback=new Intent(ValueEditionDetailsInserted.this,MenuActivity.class);
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

//        Call<Status> call=apiService.insertDetails(
//                    processes_data.getProcess_schedule_no(),
//                    processes_data.getLot_no(),
//                    processes_data.getOffice_no(),
//                    processes_data.getProcess_code(),
//                    processes_data.getWeight_no(),
//                    date_time,
//                    processes_data.getWorkers_nos(),
//                    processes_data.getTables_no(),
//                    VAP_No_of_Nets,
//                    VAP_Net_Tare_Wt,
//                    VAP_Total_Weight,
//                    VAP_Total_Tare_Weight,
//                    "14",
//                    processes_data.getProcess_no(),
//                    VAP_Net_Weight,
//                    1.0,
//                    VAP_Group_Emp_id,
//                    Double.parseDouble(String.valueOf(tot_wt)),
//                    Variety_Count_Code);
//        call.enqueue(new Callback<Status>() {
//            @Override
//            public void onResponse(Call<Status> call, Response<Status> response) {
//                AppUtils.dismissCustomProgress(mCustomProgressDialog);
//                Log.e("status","entered");
//                if (response.body()!=null){
//                    if (response.body().getStatus().contains(AppConstant.MESSAGE)){
//                        Log.e("status",response.body().getMessage());
//                        AppUtils.showToast(context,response.body().getMessage());
//
//                    }else {
//                        Log.e("status","not success");
//                        AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
//                    }
//                }
//                else {
//                    Log.e("status","response null");
//                    AppUtils.showCustomOkDialog(context,"",getResources().getString(R.string.error_default),"OK",null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Status> call, Throwable t) {
//                Log.e("status",t.toString());
//                AppUtils.dismissCustomProgress(mCustomProgressDialog);
//                AppUtils.showCustomOkDialog(context,
//                        "",
//                        getString(R.string.error_default),
//                        "OK", null);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
