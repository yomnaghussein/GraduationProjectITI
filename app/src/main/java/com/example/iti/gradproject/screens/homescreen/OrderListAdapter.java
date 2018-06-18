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
import com.ramotion.foldingcell.FoldingCell;


import java.util.List;



public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>{

    private Context context;
    private List<Order> list;

    public OrderListAdapter(Context context, List<Order> list) {
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
        String temp=list.get(position).getStatus();
       // holder.textView1.setText("Details");
        //holder.textView2.setText("Order");
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
            tv_date=itemView.findViewById(R.id.tv_id);
            tv_Status=itemView.findViewById(R.id.tv_status);
            tv_id=itemView.findViewById(R.id.tv_id);
            foldingCell=itemView.findViewById(R.id.folding_cell2);
            btn_changeStatus=itemView.findViewById(R.id.button);



        }
    }
}
