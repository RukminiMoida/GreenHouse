package com.PG.GreenHouse.Api;

import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentCodes;
import com.PG.GreenHouse.model.FactoryWeighment.FactoryWeighmentGridModel;
import com.PG.GreenHouse.model.GetEnquiryRespone;
import com.PG.GreenHouse.model.GetScheduleNo;
import com.PG.GreenHouse.model.GettingProcesses;
import com.PG.GreenHouse.model.GettingScheduleDetails;
import com.PG.GreenHouse.model.GettingVeriatyCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetGrades;
import com.PG.GreenHouse.model.HeadLessGrading.GetGroupCodes;
import com.PG.GreenHouse.model.HeadLessGrading.GetVarietyDetails;
import com.PG.GreenHouse.model.HeadLessGrading.LotNumbersStatus;
import com.PG.GreenHouse.model.LocationPlacement.BarcodeResponce;
import com.PG.GreenHouse.model.LoginResponse;
import com.PG.GreenHouse.model.SiteWTInsertResponce;
import com.PG.GreenHouse.model.Status;
import com.PG.GreenHouse.model.headOnHeadLessGrading.GetLotNumbers_HOHL_status;
import com.PG.GreenHouse.model.headOnHeadLessGrading.Getcodes_Grouphead;
import com.PG.GreenHouse.model.headOnHeadLessGrading.HOHL_location;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("app_login/")
    Call<LoginResponse> agentLogin(
            @Field("User_Mail_Id")String username,
            @Field("User_Passward")String password);

    @GET("app_process_get/")
    Call<GettingProcesses> getProcesses();

    @GET("app_product_get/")
    Call<GettingVeriatyCodes> getCountCodes();

    @FormUrlEncoded
    @POST("app_login/")
    Call<LoginResponse> uploadDetails(
            @Field("VAP_Weighment_Date_Time")String date,
            @Field("VAP_Group_Emp_id")String group_id,
            @Field("VAP_No_of_Nets")String no_nets,
            @Field("VAP_Net_Tare_Wt")String net_tare_wt,
            @Field("VAP_Total_Weight")String total_weight,
            @Field("VAP_Total_Tare_Weight")String total_tare_weight,
            @Field("VAP_Net_Weight")String net_wt,
            @Field("VAP_Supervisor_Emp_Id")String emp_id,
            @Field("Lot_No")String lt_no,
            @Field("Variety_Count_Code")String count_code,
            @Field("VAP_Net_Weight")String nt_wt,
            @Field("VAP_Process_No")String P_no,
            @Field("Lot_No")String lt_no2,
            @Field("c87")String c87);

    @FormUrlEncoded
    @POST("app_multi_procesregisters/")
    Call<Status> insertDetails(
                @Field("data")String json);

    @FormUrlEncoded
    @POST("app_site_get/")
    Call<GetScheduleNo> getScheduleNo(
            @Field("emp_id") String emp_id );

    @FormUrlEncoded
    @POST("app_get_schedule_date/")
    Call<GettingScheduleDetails> getScheduleDetails(
            @Field("scheduledate")String json);

    @FormUrlEncoded
    @POST("app_get_details/")
    Call<GetEnquiryRespone> getEnQuiryDetails(
            @Field("sechduleno")String schNo,
            @Field("  ")String enquiryNo);

    @FormUrlEncoded
    @POST("app_site_map_registers/")
    Call<SiteWTInsertResponce> insertSiteData(
            @Field("sitedata")String schNo);

    //factory weighment

    @FormUrlEncoded
    @POST("app_lot_number_get/")
    Call<FactoryWeighmentGridModel> ftLotDetails(
            @Field("emp_id")String emp_id);

    @FormUrlEncoded
    @POST("app_get_variety/")
    Call<FactoryWeighmentCodes> ftgetCodes(
            @Field("lotno")String lot_no,
            @Field("emp_id")String emp_id);

    @FormUrlEncoded
    @POST("app_factory_weighment_registers/")
    Call<Status> factoryIsertionData(
            @Field("factorydata")String factorydata);

    //headLessGrading
    @FormUrlEncoded
    @POST("app_get_lot_num/")
    Call<LotNumbersStatus> HeadLeassLotData(
            @Field("Emp_id")String Emp_15);

    @FormUrlEncoded
    @POST("app_get_count_variety_code/")
    Call<GetCodes> HeadLeassCountData(
            @Field("VarietyCode")String VarietyCode);

    @FormUrlEncoded
    @POST("app_save_lot_details/")
    Call<Status> HeadLeassInsertData(
            @Field("data")String VarietyCode);

    @FormUrlEncoded
    @POST("app_get_variety_counts/")
    Call<GetCodes> getSiteCountCodes(
            @Field("varietycode")String VarietyCode);

    @GET("app_get_fp_group_codes/")
    Call<GetGroupCodes> hlGetFinishedGroups();

    @FormUrlEncoded
    @POST("app_get_fp_variety_details/")
    Call<GetVarietyDetails> hlGetVarityCodes(
            @Field("fpGroupCode")String VarietyCode);

    @FormUrlEncoded
    @POST("app_get_fp_production_grade/")
    Call<GetGrades> hlGetGradeCodes(
            @Field("fpVarietyCode")String fpVarietyCode);



    //Headon headless grading

    @FormUrlEncoded
    @POST("app_headon_headless_get_lot_No/")
    Call<GetLotNumbers_HOHL_status> getlotNum_hohl(
            @Field("Emp_id")String Emp);

    @FormUrlEncoded
    @POST("app_headon_headless_get_variety_count/")
    Call<Getcodes_Grouphead> getCodes_heads(
            @Field("VarietyCountCode")String VarietyCountCode,
            @Field("LotNo")String LotNo);

    @FormUrlEncoded
    @POST("app_headon_headless_get_group_loc/")
    Call<HOHL_location> getLocation(
            @Field("GroupEmpId")String GroupEmpId);

    @FormUrlEncoded
    @POST("app_headon_headless_save_headon_headless/")
    Call<Status> insertHOHLDetails(
            @Field("data")String GroupEmpId);

    //headon grading
    @GET("app_headon_get_lot_No/")
    Call<LotNumbersStatus> getlotNum_h_on();

    @FormUrlEncoded
    @POST("app_headon_save_details/")
    Call<Status> insert_h_on(
            @Field("data")String json);

    //location placement
    @FormUrlEncoded
    @POST("app_cs_scan_barcode/")
    Call<BarcodeResponce> scanbarcode(
            @Field("barcode")String barcode);

    @GET("app_cs_scanned_list/")
    Call<BarcodeResponce> getAllBarcodeResults();

    @FormUrlEncoded
    @POST("app_save_cs_put_away_details/")
    Call<BarcodeResponce> saveScanDetails(
            @Field("PP_Number")String PP_Number,
            @Field("CS_Pellet_Location_No")String CS_Pellet_Location_No,
            @Field("Operator_Emp_id")String Operator_Emp_id);

}
