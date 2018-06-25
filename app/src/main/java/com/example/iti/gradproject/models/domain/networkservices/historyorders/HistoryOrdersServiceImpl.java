package com.example.iti.gradproject.models.domain.networkservices.historyorders;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.entities.historyorders.HistoryOrdersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrdersServiceImpl {
    private HistoryOrdersService historyOrdersService;

    public HistoryOrdersServiceImpl() {
        historyOrdersService = RetrofitClient.getsInstance().create(HistoryOrdersService.class);
    }

    public void getHistoryOrders(String deliveryManId, String accessToken, final BaseService.ViewHistoryOrders baseService) {

            Long deliveryManIdLong = Long.parseLong(deliveryManId);

            historyOrdersService.getHistoryOrders(deliveryManIdLong, accessToken)
                .enqueue(new Callback<HistoryOrdersResponse>() {
                    @Override
                    public void onResponse(Call<HistoryOrdersResponse> call, Response<HistoryOrdersResponse> response) {
                        baseService.onSuccess(response);
                    }

                    @Override
                    public void onFailure(Call<HistoryOrdersResponse> call, Throwable t) {
                        baseService.onError();
                    }
                });
    }
}
