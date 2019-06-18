package com.PG.GreenHouse.Adapters.HeadLessGrading;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.PG.GreenHouse.Adapters.ValueEditionDetailsAdapter;
import com.PG.GreenHouse.R;
import com.PG.GreenHouse.Utils.AppUtils;
import com.PG.GreenHouse.model.ValueEditionDetaillsModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class HeadLessGrading_2_Grid extends RecyclerView.Adapter<HeadLessGrading_2_Grid.Viewholder> {

    private Context context;
    private ArrayList<ValueEditionDetaillsModel> details;
    float cummulativeWeight,total_weight;
    private String status;

    public HeadLessGrading_2_Grid(Context context, ArrayList<ValueEditionDetaillsModel> details, String status) {
        this.context = context;
        this.details = details;
        this.status = status;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_l_g_second_grid,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        if (status=="HLG"){
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
            BigDecimal bd1 = new BigDecimal(cummulativeWeight).setScale(2, RoundingMode.HALF_UP);
            cummulativeWeight= (float) bd1.doubleValue();
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

            holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getTotal_weight())));

            holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( valueEditionDetaillsModel1.getTotal_tare_weight())));
            holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getNet_weight())));
            holder.txt_count.setText(String.valueOf(valueEditionDetaillsModel1.getCount_code()));
            holder.txt_grade.setText(String.valueOf(valueEditionDetaillsModel1.getGradeNo()));
        }

       else if (status=="HOHL"){

           holder.txt_grade.setVisibility(View.GONE);
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
            BigDecimal bd1 = new BigDecimal(cummulativeWeight).setScale(2, RoundingMode.HALF_UP);
            cummulativeWeight= (float) bd1.doubleValue();
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
            holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getTotal_weight())));

            holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( valueEditionDetaillsModel1.getTotal_tare_weight())));

            holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getNet_weight())));
            holder.txt_count.setText(String.valueOf(valueEditionDetaillsModel1.getCount_code()));
        }

        else if (status=="FW"){

            holder.txt_grade.setVisibility(View.GONE);
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
            BigDecimal bd1 = new BigDecimal(cummulativeWeight).setScale(2, RoundingMode.HALF_UP);
            cummulativeWeight= (float) bd1.doubleValue();
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

            holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getTotal_weight())));

            holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( valueEditionDetaillsModel1.getTotal_tare_weight())));
            holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getNet_weight())));
            holder.txt_count.setText(String.valueOf(valueEditionDetaillsModel1.getGroup_person()));
        }

        else if (status=="SW"){

            holder.txt_grade.setVisibility(View.GONE);
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
            BigDecimal bd1 = new BigDecimal(cummulativeWeight).setScale(2, RoundingMode.HALF_UP);
            cummulativeWeight= (float) bd1.doubleValue();
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

            holder.txt_total_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getTotal_weight())));

            holder.txt_total_tare_wt.setText(AppUtils.roundValue(String.valueOf( valueEditionDetaillsModel1.getTotal_tare_weight())));
            holder.txt_net_weight.setText(AppUtils.roundValue(String.valueOf(valueEditionDetaillsModel1.getNet_weight())));
            holder.txt_count.setText(String.valueOf(valueEditionDetaillsModel1.getGroup_person()));
        }


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

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView txt_time,txt_no_nets,txt_total_weight,txt_total_tare_wt,txt_net_weight,txt_cumulative_wt,txt_sl_no,txt_count,txt_grade;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txt_time=itemView.findViewById(R.id.txt_time);
            txt_no_nets=itemView.findViewById(R.id.txt_no_nets);
            txt_total_weight=itemView.findViewById(R.id.txt_total_weight);
            txt_total_tare_wt=itemView.findViewById(R.id.txt_total_tare_wt);
            txt_net_weight=itemView.findViewById(R.id.txt_net_weight);
            txt_cumulative_wt=itemView.findViewById(R.id.txt_cumulative_wt);
            txt_sl_no=itemView.findViewById(R.id.txt_sl_no);
            txt_count=itemView.findViewById(R.id.txt_count);
            txt_grade=itemView.findViewById(R.id.txt_grade);
        }
    }
}
