package com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.GridViewAdapter;
import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;

import java.util.ArrayList;

public class FactoryWeighmentGridAdapter extends RecyclerView.Adapter<FactoryWeighmentGridAdapter.ViewHolder> {

    private Context context;
    private int selected=-1;
    private OnRadioButtonClick onRadioButtonClick;
    private ArrayList<FTLotNumbers> lotNumbers;

    public FactoryWeighmentGridAdapter(Context context, OnRadioButtonClick onRadioButtonClick, ArrayList<FTLotNumbers> lot_numbers) {
        this.context = context;
        this.onRadioButtonClick = onRadioButtonClick;
        this.lotNumbers=lot_numbers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.factory_weighment_grid,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        holder.checkbox_ftwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=(Integer)v.getTag();
                onRadioButtonClick.onRadioClickFT(lotNumbers.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_ftwt.setTag(i);
        holder.checkbox_ftwt.setChecked(i==selected);

        holder.txt_quatity.setVisibility(View.VISIBLE);

        holder.txt_quatity.setText(lotNumbers.get(i).getStatus());

        String sl= String.valueOf(i+1);
        holder.txt_sl_no.setText(sl);
        holder.txt_lot_no.setText(lotNumbers.get(i).getLot_No());
        holder.txt_lot_date.setText(AppUtils.dateFormat(lotNumbers.get(i).getLot_Date()));
        holder.variaty.setText(lotNumbers.get(i).getProduct_Variety_Name());
        holder.location.setText(lotNumbers.get(i).getOrg_office_Name());
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
        return lotNumbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_ftwt;
        private TextView txt_sl_no,txt_lot_date,txt_lot_no,variaty,location,txt_quatity,txt_count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkbox_ftwt=itemView.findViewById(R.id.checkbox_ftwt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_lot_date=itemView.findViewById(R.id.txt_lot_date);
            txt_lot_no=itemView.findViewById(R.id.txt_lot_no);
            variaty=itemView.findViewById(R.id.txt_variety);
            location=itemView.findViewById(R.id.txt_location);
            txt_quatity=itemView.findViewById(R.id.txt_quatity);
            txt_count=itemView.findViewById(R.id.txt_count);
        }
    }
}
