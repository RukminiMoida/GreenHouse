package com.PG.GreenHouse.Adapters.LocAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentDetailsInsertion;
import com.PG.GreenHouse.Api.ApiService;
import com.PG.GreenHouse.Api.AppUrl;
import com.PG.GreenHouse.BaseActivity;
import com.PG.GreenHouse.InterFace.ScannedInterface;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppConstant;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.Utils.SharedPreferenceConfig;
import com.PG.GreenHouse.model.LocationPlacement.BarcodeResponce;
import com.PG.GreenHouse.model.LocationPlacement.BarcodeResults;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutAway_scanner_adapter extends RecyclerView.Adapter<PutAway_scanner_adapter.Viewholder>  {

    private Context context;
    private ArrayList<BarcodeResults> barcodeResults;
    private ScannedInterface scannedInterface;

    public PutAway_scanner_adapter(Context context, ArrayList<BarcodeResults> barcodeResults, ScannedInterface scannedInterface) {
        this.context = context;
        this.barcodeResults = barcodeResults;
        this.scannedInterface = scannedInterface;
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cus_loc_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
        }

            String sl= String.valueOf(position+1);
            holder.loc_s_no.setText(sl);
            holder.loc_date_time.setText(barcodeResults.get(position).getPP_Date());
            holder.loc_st_no.setText(barcodeResults.get(position).getPP_Number());
            holder.loc_loc_no.setText(barcodeResults.get(position).getFK_CS_Pellet_Location_Visible_No());
            holder.loc_status.setText(barcodeResults.get(position).getStatus());

            if (barcodeResults.get(position).getStatus().contains("SCANNED")){

                holder.loc_confirm.setVisibility(View.GONE);
                holder.loc_pending.setVisibility(View.VISIBLE);
            }
            else {
                holder.loc_confirm.setVisibility(View.VISIBLE);
                holder.loc_pending.setVisibility(View.GONE);
            }

            holder.loc_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    scannedInterface.onButtonClick(barcodeResults.get(position).getPP_Number(),barcodeResults.get(position).getFK_CS_Pellet_Location_No());

                }
            });


    }





    @Override
    public int getItemCount() {
        return barcodeResults.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView loc_s_no,loc_date_time,loc_st_no,loc_loc_no,loc_status;
        private Button loc_confirm,loc_pending;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            loc_s_no=itemView.findViewById(R.id.loc_s_no);
            loc_date_time=itemView.findViewById(R.id.loc_date_time);
            loc_st_no=itemView.findViewById(R.id.loc_st_no);
            loc_loc_no=itemView.findViewById(R.id.loc_loc_no);
            loc_status=itemView.findViewById(R.id.loc_status);
            loc_confirm=itemView.findViewById(R.id.loc_confirm);
            loc_pending=itemView.findViewById(R.id.loc_pending);
        }
    }
}
