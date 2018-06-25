package com.example.iti.gradproject.screens.homescreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.entities.Order;
import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.example.iti.gradproject.models.entities.OrderStatusResponse;
import com.ramotion.foldingcell.FoldingCell;


import java.util.List;



public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>{

    private Context context;
    private List<OrderResponseObject> list;
    private List<String> changeStatusList;

    public OrderListAdapter(Context context, List<OrderResponseObject> list,List<String> changeStatusList) {
        this.context = context;
        this.list = list;
        this.changeStatusList = changeStatusList;
    }

    @NonNull
    @Override
    public OrderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.order_item,null);
        OrderListViewHolder holder=new OrderListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderListViewHolder holder, int position) {

        holder.tv_date.setText(list.get(position).getDeliveryDueDate());
        holder.tv_id.setText(list.get(position).getOrderId().toString());
        holder.tv_Status.setText(list.get(position).getOrderStatus());
        holder.tv_hub.setText(list.get(position).getHubName());
        holder.tv_distination.setText("estany");
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.foldingCell.toggle(false);
            }
        });

        //String[] items = new String[] { "  Chai Latte", "  Green Tea", "  Black Tea"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, changeStatusList);
        holder.statusSpinner.setPrompt("Change Status...");
        holder.statusSpinner.setAdapter(adapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_id,tv_Status,tv_date,tv_hub,tv_distination;
        FoldingCell foldingCell;
        Spinner statusSpinner;
        Button btn_map;

        public OrderListViewHolder(View itemView) {

            super(itemView);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_Status=itemView.findViewById(R.id.tv_status);
            tv_id=itemView.findViewById(R.id.tv_id);
            foldingCell=itemView.findViewById(R.id.folding_cell2);
            tv_hub=itemView.findViewById(R.id.tv_StartPoint);
            tv_distination=itemView.findViewById(R.id.tv_endPoint);
            statusSpinner=itemView.findViewById(R.id.dropDown);
            btn_map=itemView.findViewById(R.id.btn_start);



        }
    }
}
