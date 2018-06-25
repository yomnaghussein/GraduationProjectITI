package com.example.iti.gradproject.models.domain.networkservices.orderstatus;

import android.util.Log;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.entities.OrderStatusResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderStatusServiceImpl {
    private OrderStatusService orderStatusService;

    public OrderStatusServiceImpl() {
        orderStatusService = RetrofitClient.getsInstance().create(OrderStatusService.class);
    }
    public void getOrders(String token , final BaseService.ViewOrderStatus baseService) {


        orderStatusService.getOrders(token)
                .enqueue(new Callback<OrderStatusResponse>() {
                    @Override
                    public void onResponse(Call<OrderStatusResponse> call, Response<OrderStatusResponse> response) {

                        Log.i("CHECKING_orders","Code: "+response.code());
                        if (response.isSuccessful() && response.body().getSuccess() &&  response.body() != null ) {
                                baseService.onSuccessOrders(response);

                        } else {
                            Log.i("CHECKING_orders", "Error");

                        }
                    }

                    @Override
                    public void onFailure(Call<OrderStatusResponse> call, Throwable t) {
                        Log.i("Onfailure", "error");
                    }
                });
    }
}
