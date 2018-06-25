package com.example.iti.gradproject.models.domain.networkservices.updatestatus;

import android.util.Log;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.entities.UpdateOrderResponse;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStatusServiceImpl {
    private UpdateStatusService updateStatusService;


    public UpdateStatusServiceImpl() {
        updateStatusService = RetrofitClient.getsInstance().create(UpdateStatusService.class);
    }

    public void updateOrderStatus(Long orderId, String orderStatus, String token) {

        JSONObject requestBody = new JSONObject();

        try {

            requestBody.put("orderId", orderId);
            requestBody.put("orderStatus", orderStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        updateStatusService.updateOrderStatus(token,requestBody.toString())
                .enqueue(new Callback<UpdateOrderResponse>() {
                    @Override
                    public void onResponse(Call<UpdateOrderResponse> call, Response<UpdateOrderResponse> response) {
                        Log.i("Response","Response"+response.code());
                        if (response.isSuccessful()){
                            Log.i("success","successful");

                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateOrderResponse> call, Throwable t) {
                        Log.i("Onfailure", "error");
                    }
                });



    }
}
