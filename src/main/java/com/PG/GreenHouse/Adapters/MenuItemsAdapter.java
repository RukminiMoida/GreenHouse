package com.PG.GreenHouse.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.PG.GreenHouse.R;
import com.PG.GreenHouse.UI.FactoryWeighment.FactoryWeighment;
import com.PG.GreenHouse.UI.HeadLessGrading.HeadLessGrading;
import com.PG.GreenHouse.UI.HeadOnGrading.HeadOnGrading;
import com.PG.GreenHouse.UI.HeadOnHeadLessGrading.HeadOnHeadLessGrading;
import com.PG.GreenHouse.UI.LocationPlacement.LocationPlacement;
import com.PG.GreenHouse.UI.Site_Weightment.Site_weightment;
import com.PG.GreenHouse.UI.ValueEdition;
import com.PG.GreenHouse.UI.WeightLoadMachine;
import com.PG.GreenHouse.Utils.AppUtils;
import com.google.zxing.integration.android.IntentIntegrator;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    private Context context;
    public MenuItemsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridview_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.layout_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtils.isNetworkAvailable(context)) {
                    if (position==0){
                        Intent siteWeightment=new Intent(context, Site_weightment.class);
                        context.startActivity(siteWeightment);
                    }
                    if (position==1){
                        //  Toast.makeText(context,"factory weighment",Toast.LENGTH_SHORT).show();
                        Intent valueEdition=new Intent(context, FactoryWeighment.class);
                        context.startActivity(valueEdition);
                    }
                    if (position==2){
                        Intent valueEdition=new Intent(context, ValueEdition.class);
                        context.startActivity(valueEdition);
                    }
                    if (position==3){
                        Intent valueEdition=new Intent(context, HeadLessGrading.class);
                        context.startActivity(valueEdition);
                    }
                    if (position==4){
                        Intent valueEdition=new Intent(context, HeadOnGrading.class);
                        context.startActivity(valueEdition);
                    }
                    if (position==5){
                        Intent valueEdition=new Intent(context, HeadOnHeadLessGrading.class);
                        context.startActivity(valueEdition);
                    }
                    if (position==6){
                        Intent valueEdition=new Intent(context, LocationPlacement.class);
                        context.startActivity(valueEdition);
                    }

                }
                else {
                    AppUtils.showToast(context,context.getString(R.string.error_network));
                }

            }
        });

        if (position==0){
            holder.item_name.setText("Site\n weighment");
            holder.gridImage.setImageResource(R.drawable.ic_site_weighment);
        }
        if (position==1){
            holder.item_name.setText("Factory\n weighment");
            holder.gridImage.setImageResource(R.drawable.ic_factory_weighment);
        }
        if (position==2){
            holder.item_name.setText("Value\n Edition");
            holder.gridImage.setImageResource(R.drawable.ic_value_edi);
        }
        if (position==3){
            holder.item_name.setText("HeadLess\n Grading");
            holder.gridImage.setImageResource(R.drawable.ic_headles_grading);
        }
        if (position==4){
            holder.item_name.setText("HeadOn\n Grading");
            holder.gridImage.setImageResource(R.drawable.ic_head_on_grading);
        }
        if (position==5){
            holder.item_name.setText("HOHL\n Weighment");
            holder.gridImage.setImageResource(R.drawable.ic_hod_hd);
        }
        if (position==6){
            holder.item_name.setText("Location\nPlacement");
            holder.gridImage.setImageResource(R.drawable.ic_barcode);
        }


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView gridImage;
        private TextView item_name;
        private LinearLayout layout_grid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gridImage=itemView.findViewById(R.id.gridImage);
            item_name=itemView.findViewById(R.id.item_name);
            layout_grid=itemView.findViewById(R.id.layout_grid);
        }
    }
}
