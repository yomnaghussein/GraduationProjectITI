package com.example.iti.gradproject.screens.homescreen;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.App;
import com.example.iti.gradproject.models.Utilities;
import com.example.iti.gradproject.models.domain.networkservices.updatestatus.UpdateStatusServiceImpl;
import com.example.iti.gradproject.models.entities.Order;
import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.example.iti.gradproject.models.entities.OrderStatusResponse;
import com.example.iti.gradproject.screens.homescreen.InProcessScreen.InProcessPresenterImpl;
import com.ramotion.foldingcell.FoldingCell;


import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder> implements
HomeScreenContract.HomeScreen{

    private Context context;
    private List<OrderResponseObject> list;
    private List<String> changeStatusList;
    InProcessPresenterImpl inProcessPresenter;
    HomeScreenContract.HomePresenter presenter;
    boolean flag=false;

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
    public void onBindViewHolder(@NonNull final OrderListViewHolder holder, final int position) {
        String[] due=getDue(list.get(position).getDeliveryDueDate(),"T");
        holder.tv_date.setText(due[0]);
        holder.tv_time.setText(due[1].substring(0,8));
        holder.tv_id.setText(list.get(position).getOrderId().toString());
        //holder.tv_Status.setText(list.get(position).getOrderStatus());
        holder.tv_hub.setText(list.get(position).getHubName());
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(list.get(position).getDeliveryLocation().getLatitude(), list.get(position).getDeliveryLocation().getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            holder.tv_distination.setText(addresses.get(0).getThoroughfare());
        }

        holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.foldingCell.toggle(false);
            }
        });

        String[] items = new String[] { "CREATED", "PURCHASING", "ASSEMBLING","DELIVERING","DELIVERED","CANCELLED"};
       // Log.i("asmaa", "m"+changeStatusList.size());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, items);
        holder.statusSpinner.setPrompt("Change Status...");
        holder.statusSpinner.setAdapter(adapter);
        holder.statusSpinner.setSelection(adapter.getPosition(list.get(position).getOrderStatus()));
       //holder.statusSpinner.setSelection(statusPositiion(list.get(position).getOrderStatus(),changeStatusList));
        if(list.get(position).getOrderStatus().equals("DELIVERED")){
            holder.statusSpinner.setClickable(false);
        }

        holder.statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String accesstoken = Utilities.getTokenFromPref(App.getApplication());
                new UpdateStatusServiceImpl().updateOrderStatus(list.get(position).getOrderId().longValue(), adapterView.getItemAtPosition(i).toString(), accesstoken);

                if(adapterView.getItemAtPosition(i).toString().equals("DELIVERED")&&holder.statusSpinner.isClickable()){
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,list.size());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?f=d&saddr="+list.get(position).getHubLocation().getLatitude().toString()
                                +","+list.get(position).getHubLocation().getLongitude().toString()
                                +"&daddr="+list.get(position).getDeliveryLocation().getLatitude().toString()+","+
                                list.get(position).getDeliveryLocation().getLongitude().toString()+"&mode=d"));

                intent.setComponent(new ComponentName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity"));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void updateText(OrderListViewHolder holder, String status) {
       // holder.tv_Status.setText(holder.statusSpinner.getSelectedItem().toString());
    }

    @Override
    public OrderListViewHolder getHolder() {
        return null;
    }

    class OrderListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_id,tv_time,tv_date,tv_hub,tv_distination;
        FoldingCell foldingCell;
        Spinner statusSpinner;
        Button btn_map;

        public OrderListViewHolder(View itemView) {

            super(itemView);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_id=itemView.findViewById(R.id.tv_id);
            foldingCell=itemView.findViewById(R.id.folding_cell2);
            tv_hub=itemView.findViewById(R.id.tv_StartPoint);
            tv_distination=itemView.findViewById(R.id.tv_endPoint);
            statusSpinner=itemView.findViewById(R.id.dropDown);
            btn_map=itemView.findViewById(R.id.btn_start);




        }
    }
    private String[] getDue(String date,String spliter){
        String[] x=date.split(spliter);
        return  x;

    }


}
