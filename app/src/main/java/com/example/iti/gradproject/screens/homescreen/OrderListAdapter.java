package com.example.iti.gradproject.screens.homescreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.entities.Order;
import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.ramotion.foldingcell.FoldingCell;


import java.util.List;



public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>{

    private Context context;
    private List<OrderResponseObject> list;

    public OrderListAdapter(Context context, List<OrderResponseObject> list) {
        this.context = context;
        this.list = list;
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
        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.foldingCell.toggle(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_id,tv_Status,tv_date;
        Button btn_changeStatus;
        FoldingCell foldingCell;

        public OrderListViewHolder(View itemView) {

            super(itemView);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_Status=itemView.findViewById(R.id.tv_status);
            tv_id=itemView.findViewById(R.id.tv_id);
            foldingCell=itemView.findViewById(R.id.folding_cell2);
            btn_changeStatus=itemView.findViewById(R.id.button);



        }
    }
}
