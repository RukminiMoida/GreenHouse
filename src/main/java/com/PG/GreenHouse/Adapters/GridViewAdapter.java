package com.PG.GreenHouse.Adapters;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.model.Processes_data;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {

    private Context context;
    private int selected=-1;
    private OnRadioButtonClick onRadioButtonClick;
    private ArrayList<Processes_data> processes_data;

    public GridViewAdapter(Context context, OnRadioButtonClick onRadioButtonClick, ArrayList<Processes_data> processes_data) {
        this.context = context;
        this.onRadioButtonClick = onRadioButtonClick;
        this.processes_data = processes_data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.value_edition_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        holder.checkbox_vale_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=(Integer)v.getTag();
                onRadioButtonClick.onRadioClick(processes_data.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_vale_edt.setTag(i);
        holder.checkbox_vale_edt.setChecked(i==selected);

        String sl= String.valueOf(i+1);
        holder.txt_sl_no.setText(sl);
        holder.txt_lot_no.setText(processes_data.get(i).getLot_no());
        holder.txt_count.setText(processes_data.get(i).getCount_code());
        holder.received_quantity.setText("");
        holder.process_for.setText(processes_data.get(i).getProcess_code());
        holder.received_from.setText("");



        if(i %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F5F6F8"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }

    }

    @Override
    public int getItemCount() {
        return processes_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_vale_edt;
        private TextView txt_sl_no,txt_lot_no,txt_count,received_quantity,received_from,process_for;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox_vale_edt=itemView.findViewById(R.id.checkbox_vale_edt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_lot_no=itemView.findViewById(R.id.txt_lot_no);
            txt_count=itemView.findViewById(R.id.txt_count);
            received_quantity=itemView.findViewById(R.id.received_quantity);
            received_from=itemView.findViewById(R.id.received_from);
            process_for=itemView.findViewById(R.id.process_for);

        }
    }
}


