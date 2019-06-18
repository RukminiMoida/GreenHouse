package com.PG.GreenHouse.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.Processes_data;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;

import java.util.ArrayList;
import java.util.List;

public class ValueEditionDetailsAdapter extends RecyclerView.Adapter<ValueEditionDetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ValueEditionDetaillsModel> details;
    float cummulativeWeight;

    public ValueEditionDetailsAdapter(Context context, ArrayList<ValueEditionDetaillsModel> details) {
        this.context = context;
        this.details=details;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.value_edition_details_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

         final ValueEditionDetaillsModel valueEditionDetaillsModel1=details.get(position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AppUtils.showCustomOkCancelDialog(v.getContext(), "", "Do you want to delete this row?", "YES", "NO",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                removeItem(valueEditionDetaillsModel1);
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                return false;
            }
        });

        cummulativeWeight=cummulativeWeight+ valueEditionDetaillsModel1.getNet_weight();
        holder.txt_cumulative_wt.setText(String.valueOf(cummulativeWeight));

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

        String sl= String.valueOf(position+1);
        holder.txt_sl_no.setText(sl);
        holder.txt_time.setText(valueEditionDetaillsModel1.getTime());
        holder.txt_no_nets.setText(String.valueOf(valueEditionDetaillsModel1.getNo_of_nets()));
        holder.txt_total_weight.setText(String.valueOf(valueEditionDetaillsModel1.getTotal_weight()));
        holder.txt_total_tare_wt.setText(String.valueOf( valueEditionDetaillsModel1.getTotal_tare_weight()));
        holder.txt_net_weight.setText(String.valueOf(valueEditionDetaillsModel1.getNet_weight()));

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public void removeItem(ValueEditionDetaillsModel data) {
        int position=details.indexOf(data);
        details.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_time,txt_no_nets,txt_total_weight,txt_total_tare_wt,txt_net_weight,txt_cumulative_wt,txt_sl_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_time=itemView.findViewById(R.id.txt_time);
            txt_no_nets=itemView.findViewById(R.id.txt_no_nets);
            txt_total_weight=itemView.findViewById(R.id.txt_total_weight);
            txt_total_tare_wt=itemView.findViewById(R.id.txt_total_tare_wt);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_cumulative_wt=itemView.findViewById(R.id.txt_cumulative_wt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
        }
    }
}
