package com.example.iti.gradproject.models.domain.networkservices.historyorders;

import com.example.iti.gradproject.models.entities.historyorders.HistoryOrdersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface HistoryOrdersService {
    @GET("orders/deliveryPerson/getHistory/{deliveryPersonId}")
    Call<HistoryOrdersResponse> getHistoryOrders(@Path("deliveryPersonId") Long deliveryPersonId, @Header("Authorization") String accessToken);

}
