package com.example.iti.gradproject.models.domain.networkservices.updatestatus;

import com.example.iti.gradproject.models.entities.UpdateOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;


public interface UpdateStatusService {
    @Headers("Content-Type: application/json")
    @PUT("orders/updateStatus")
    Call<UpdateOrderResponse> updateOrderStatus(@Header("Authorization") String token,@Body String requestBody);
}
