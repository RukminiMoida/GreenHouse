package com.PG.GreenHouse.Adapters.HeadLessGrading;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.FactoryWeighmentAdapters.FactoryWeighmentGridAdapter;
import com.PG.GreenHouse.InterFace.HeadlessGradinRadioClick;
import com.PG.GreenHouse.InterFace.OnRadioButtonClick;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.FactoryWeighment.FTLotNumbers;
import com.PG.GreenHouse.model.HeadLessGrading.Lot_numbers;

import java.util.ArrayList;

public class HeadLessGradingGridAdapter extends RecyclerView.Adapter<HeadLessGradingGridAdapter.Viewholder> {

    private Context context;
    private int selected=-1;
    private HeadlessGradinRadioClick onRadioButtonClick;
    private ArrayList<Lot_numbers> Lot_No;

    public HeadLessGradingGridAdapter(Context context, HeadlessGradinRadioClick onRadioButtonClick, ArrayList<Lot_numbers> lot_No) {
        this.context = context;
        this.onRadioButtonClick = onRadioButtonClick;
        this.Lot_No = lot_No;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.factory_weighment_grid,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, final int i) {
        holder.txt_quatity.setVisibility(View.VISIBLE);
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
        holder.txt_lot_no.setText(Lot_No.get(i).getLot_No());
        holder.txt_lot_date.setText(AppUtils.dateFormat(Lot_No.get(i).getLot_Date()));
        holder.variaty.setText(Lot_No.get(i).getProduct_Variety_Name());
        holder.location.setText(Lot_No.get(i).getVariety_Count());
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

    public class Viewholder extends RecyclerView.ViewHolder{
        private CheckBox checkbox_ftwt;
        private TextView txt_sl_no,txt_lot_date,txt_lot_no,variaty,location,txt_quatity;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            checkbox_ftwt=itemView.findViewById(R.id.checkbox_ftwt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_lot_date=itemView.findViewById(R.id.txt_lot_date);
            txt_lot_no=itemView.findViewById(R.id.txt_lot_no);
            variaty=itemView.findViewById(R.id.txt_variety);
            location=itemView.findViewById(R.id.txt_location);
            txt_quatity=itemView.findViewById(R.id.txt_quatity);
        }
    }
}
