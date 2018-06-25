package com.example.iti.gradproject.models.domain.networkservices.upcomingorders;

import com.example.iti.gradproject.models.entities.upcomingorders.UpcomingOrdersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UpcomingOrdersService {
    @GET("orders/deliveryPerson/getUpcoming/{deliveryPersonId}")
    Call<UpcomingOrdersResponse> getUpcomingOrders(@Path("deliveryPersonId") Long deliveryPersonId, @Header("Authorization") String accessToken);

}
