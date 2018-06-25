package com.example.iti.gradproject.screens.homescreen.DoneScreen;

import android.content.Context;
import android.util.Log;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.domain.networkservices.historyorders.HistoryOrdersServiceImpl;
import com.example.iti.gradproject.models.domain.networkservices.orderstatus.OrderStatusServiceImpl;
import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.example.iti.gradproject.models.entities.OrderStatusResponse;
import com.example.iti.gradproject.models.entities.historyorders.HistoryOrdersResponse;

import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


public class DonePresenterImpl implements DoneContract.DonePresenter,BaseService.ViewHistoryOrders ,BaseService.ViewOrderStatus{

    private static final String LOG_TAG = DonePresenterImpl.class.getSimpleName();
    private final Context context;
    private final DoneContract.DoneFragment view;
    private HistoryOrdersServiceImpl historyOrdersService;
    private List<OrderResponseObject> orderResponseObjectList;
    private List<String> orderStatusList;
    private OrderStatusServiceImpl ordersChangeService;



    DonePresenterImpl(Context context,  DoneContract.DoneFragment view) {
        this.context = context;
        this.view = view;
        historyOrdersService = new HistoryOrdersServiceImpl();
        ordersChangeService = new OrderStatusServiceImpl();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(Response<HistoryOrdersResponse> response) {
        switch (response.code()) {
            case 200:
                HistoryOrdersResponse successfulResponse = response.body();
                orderResponseObjectList =  successfulResponse.getOrderResponseObject();
                view.setHistoryOrdersAdapter(orderResponseObjectList,orderStatusList);

                break;

            default:
                Converter<ResponseBody, HistoryOrdersResponse> converter = RetrofitClient.getsInstance().responseBodyConverter(HistoryOrdersResponse.class, new Annotation[0]);
                try {
                    HistoryOrdersResponse error = converter.convert(response.errorBody());
                    Log.i(LOG_TAG, "Response code: "+response.code()+ " message: "+error.getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void getHistoryOrders(String deliveryManId,String accessToken) {
        historyOrdersService.getHistoryOrders(deliveryManId,accessToken,this);
    }

    @Override
    public void getOrders(String token) {
        ordersChangeService.getOrders(token,this);
    }

    @Override
    public void onSuccessOrders(Response<OrderStatusResponse> response) {
        switch (response.code()) {
            case 200:
                OrderStatusResponse successfulResponse = response.body();
                orderStatusList =  successfulResponse.getOrderResponseObject();

                break;

            default:
                Converter<ResponseBody, OrderStatusResponse> converter = RetrofitClient.getsInstance().responseBodyConverter(OrderStatusResponse.class, new Annotation[0]);
                try {
                    OrderStatusResponse error = converter.convert(response.errorBody());
                    Log.i(LOG_TAG, "Response code: "+response.code()+ " message: "+error.getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
