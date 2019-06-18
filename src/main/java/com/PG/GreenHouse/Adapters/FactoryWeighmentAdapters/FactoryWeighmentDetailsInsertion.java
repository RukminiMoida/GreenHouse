package com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class FactoryWeighmentDetailsInsertion extends RecyclerView.Adapter<FactoryWeighmentDetailsInsertion.ViewHolder> {
    private Context context;
    private ArrayList<ValueEditionDetaillsModel> insertDetails;
    float cummulative_wt;

    public FactoryWeighmentDetailsInsertion(Context context, ArrayList<ValueEditionDetaillsModel> insertDetails) {
        this.context = context;
        this.insertDetails = insertDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ft_insertion,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        if(i %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }


        String sl= String.valueOf(i+1);
        holder.txt_sl_no.setText(sl);

        cummulative_wt=cummulative_wt+insertDetails.get(i).getNet_weight();
        BigDecimal bd1 = new BigDecimal(cummulative_wt).setScale(2, RoundingMode.HALF_UP);
        cummulative_wt= (float) bd1.doubleValue();

        holder.txt_count.setText(insertDetails.get(i).getGroup_person());
        holder.txt_time.setText(insertDetails.get(i).getTime());
        holder.txt_cummulative_wt.setText(String.valueOf(cummulative_wt));
        holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf(insertDetails.get(i).getNet_weight())));



    }

    @Override
    public int getItemCount() {
        return insertDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_count,txt_time,txt_net_weight,txt_cummulative_wt,txt_sl_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_count=itemView.findViewById(R.id.txt_count);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_time=itemView.findViewById(R.id.txt_time);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_cummulative_wt=itemView.findViewById(R.id.txt_cumulative_wt);

        }
    }
}
