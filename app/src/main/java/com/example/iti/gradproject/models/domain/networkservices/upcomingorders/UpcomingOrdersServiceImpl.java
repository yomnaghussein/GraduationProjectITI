package com.example.iti.gradproject.models.domain.networkservices.upcomingorders;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.entities.upcomingorders.UpcomingOrdersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingOrdersServiceImpl {
    private UpcomingOrdersService upcomingOrdersService;

    public UpcomingOrdersServiceImpl() {
        upcomingOrdersService = RetrofitClient.getsInstance().create(UpcomingOrdersService.class);
    }

    public void getHistoryOrders(String deliveryManId, String accessToken, final BaseService.ViewUpcomingOrders baseService) {

        Long deliveryManIdLong = Long.parseLong(deliveryManId);

        upcomingOrdersService.getUpcomingOrders(deliveryManIdLong, accessToken)
                .enqueue(new Callback<UpcomingOrdersResponse>() {
                    @Override
                    public void onResponse(Call<UpcomingOrdersResponse> call, Response<UpcomingOrdersResponse> response) {
                        baseService.onSuccess(response);
                    }

                    @Override
                    public void onFailure(Call<UpcomingOrdersResponse> call, Throwable t) {
                        baseService.onError();
                    }
                });
    }
}
