package com.PG.GreenHouse.Adapters.HeadLessGrading;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentDetailsInsertion;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.Status;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class HeadLessGradingDetailsInsertedAdapter extends RecyclerView.Adapter<HeadLessGradingDetailsInsertedAdapter.Viewholder> {
    private Context context;
    private ArrayList<ValueEditionDetaillsModel> insertDetails;
    float cummulative_wt,total_weight;
    private String status;

    public HeadLessGradingDetailsInsertedAdapter(Context context, ArrayList<ValueEditionDetaillsModel> insertDetails, String status) {
        this.context = context;
        this.insertDetails = insertDetails;
        this.status = status;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_headless_insertion,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int i) {
        if(i %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        if (status == "HLG") {
            String sl= String.valueOf(i+1);
            holder.txt_sl_no.setText(sl);
            holder.txt_grade.setText(insertDetails.get(i).getGradeNo());
            holder.txt_count.setText(insertDetails.get(i).getCount_code());
            holder.txt_time.setText(insertDetails.get(i).getTime());
            holder.txt_no_nets.setText(String.valueOf(insertDetails.get(i).getNo_of_nets()));
            holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( insertDetails.get(i).getTotal_tare_weight())));
            holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(insertDetails.get(i).getTotal_weight())));
            holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf( insertDetails.get(i).getNet_weight())));
        }
        else if (status=="HOHL"){

            holder.txt_grade.setVisibility(View.GONE);
            String sl= String.valueOf(i+1);
            holder.txt_sl_no.setText(sl);
            holder.txt_count.setText(insertDetails.get(i).getCount_code());
            holder.txt_time.setText(insertDetails.get(i).getTime());
            holder.txt_no_nets.setText(String.valueOf(insertDetails.get(i).getNo_of_nets()));
            holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( insertDetails.get(i).getTotal_tare_weight())));
            holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(insertDetails.get(i).getTotal_weight())));
            holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf( insertDetails.get(i).getNet_weight())));
        }


    }

    @Override
    public int getItemCount() {
        return insertDetails.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView txt_grade,txt_time,txt_no_nets,txt_total_weight,txt_total_tare_wt,txt_net_weight,txt_sl_no,txt_count;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txt_grade=itemView.findViewById(R.id.txt_grade);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_time=itemView.findViewById(R.id.txt_time);
            txt_no_nets=itemView.findViewById(R.id.txt_no_nets);
            txt_total_tare_wt=itemView.findViewById(R.id.txt_total_tare_wt);
            txt_total_weight=itemView.findViewById(R.id.txt_total_weight);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_count=itemView.findViewById(R.id.txt_count);
        }
    }
}
