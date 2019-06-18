package com.PG.GreenHouse.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.PG.GreenHouse.R;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_prferance), Context.MODE_PRIVATE);
    }
    public void writeLoginPreference(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.check_login), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readLoginPrefernce(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.check_login),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }

    public void writeLoginEmpId(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.emp_id), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readLoginEmpId(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.emp_id),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }
    public void writeSiteWeighmentPond_no(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.pond_no), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readSiteWeighmentPond_no(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.pond_no),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }

    public void writeSiteWeighmentFarmLocation(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.farm_loc), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readSiteWeighmentFarmLocation(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.farm_loc),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }

    public void writeSiteWeighmentMapData(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.map_details), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readSiteWeighmentFarmMapData(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.map_details),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }

    public void writeSiteWeighmentLatitude(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.map_latitude), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readSiteWeighmentLatitude(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.map_latitude),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }

    public void writeSiteWeighmentLongitude(String url){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.map_longitude), String.valueOf(url));
        Log.i("SharedPreferenceWrite: ",""+url);
        editor.apply();
    }

    public String readSiteWeighmentLongitude(){
        String url;
        url = sharedPreferences.getString(context.getResources().getString(R.string.map_longitude),"no");
        Log.i("SharedPreferenceRead: ",""+url);
        return url;
    }
}