package com.example.iti.gradproject.models.domain.networkservices.orderstatus;

import com.example.iti.gradproject.models.entities.OrderStatusResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface OrderStatusService {
    @GET("orders/ORDER_STATUS")
    Call<OrderStatusResponse> getOrders(@Header("Authorization") String token);
}
