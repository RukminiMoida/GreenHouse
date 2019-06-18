package com.PG.GreenHouse.Adapters.HeadOnHeadlessAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.PG.GreenHouse.InterFace.HeadOnHeadLessRadioClick;
import com.PG.GreenHouse.InterFace.HeadlessGradinRadioClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;
import com.PG.GreenHouse.model.headOnHeadLessGrading.Lot_details;

import java.util.ArrayList;

public class HeadOnHeadlessGridAdapter extends RecyclerView.Adapter<HeadOnHeadlessGridAdapter.ViewHolder> {

    private Context context;
    private int selected=-1;
    private HeadOnHeadLessRadioClick onRadioButtonClick;
    private ArrayList<Lot_details> Lot_No;

    public HeadOnHeadlessGridAdapter(Context context, HeadOnHeadLessRadioClick onRadioButtonClick, ArrayList<Lot_details> lot_No) {
        this.context = context;
        this.onRadioButtonClick = onRadioButtonClick;
        Lot_No = lot_No;
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
                onRadioButtonClick.onRadioClickFT(Lot_No.get(i));
                notifyDataSetChanged();
            }
        });
        holder.checkbox_ftwt.setTag(i);
        holder.checkbox_ftwt.setChecked(i==selected);

        String sl= String.valueOf(i+1);
        holder.txt_sl_no.setText(sl);
        holder.txt_lot_no.setText(Lot_No.get(i).getLotNo());
        holder.txt_lot_date.setText(AppUtils.dateFormat(Lot_No.get(i).getLot_date()));
        holder.variaty.setText(Lot_No.get(i).getProduct_Variety_Name());
        holder.count.setText(Lot_No.get(i).getVariety_Count());
        holder.txt_quatity.setText(Lot_No.get(i).getQuantity());
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
        return Lot_No.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_ftwt;
        private TextView txt_sl_no,txt_lot_date,txt_lot_no,variaty,count,txt_quatity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox_ftwt=itemView.findViewById(R.id.checkbox_ftwt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_lot_date=itemView.findViewById(R.id.txt_lot_date);
            txt_lot_no=itemView.findViewById(R.id.txt_lot_no);
            variaty=itemView.findViewById(R.id.txt_variety);
            count=itemView.findViewById(R.id.txt_location);
            txt_quatity=itemView.findViewById(R.id.txt_quatity);
        }
    }
}
