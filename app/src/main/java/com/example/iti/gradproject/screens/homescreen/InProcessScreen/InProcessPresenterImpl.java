package com.example.iti.gradproject.screens.homescreen.InProcessScreen;

import android.content.Context;
import android.util.Log;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.domain.networkservices.orderstatus.OrderStatusServiceImpl;
import com.example.iti.gradproject.models.domain.networkservices.upcomingorders.UpcomingOrdersService;
import com.example.iti.gradproject.models.domain.networkservices.upcomingorders.UpcomingOrdersServiceImpl;
import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.example.iti.gradproject.models.entities.OrderStatusResponse;
import com.example.iti.gradproject.models.entities.upcomingorders.UpcomingOrdersResponse;

import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

public class InProcessPresenterImpl implements InProcessContract.InProcessPresenter,BaseService.ViewUpcomingOrders,BaseService.ViewOrderStatus{
    private static final String LOG_TAG = InProcessPresenterImpl.class.getSimpleName();
    private final Context context;
    private final InProcessContract.InProcessFragment view;
    private UpcomingOrdersServiceImpl upcomingOrdersService;
    private List<OrderResponseObject> orderResponseObjectList;
    private List<String> orderStatusList;
    private OrderStatusServiceImpl ordersChangeService;

    InProcessPresenterImpl(Context context,  InProcessContract.InProcessFragment view) {
        this.context = context;
        this.view = view;
        upcomingOrdersService = new UpcomingOrdersServiceImpl();
        ordersChangeService = new OrderStatusServiceImpl();


    }
    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(Response<UpcomingOrdersResponse> response) {
        switch (response.code()) {
            case 200:
                UpcomingOrdersResponse successfulResponse = response.body();
                orderResponseObjectList =  successfulResponse.getOrderResponseObject();
                view.setUpcomingOrdersAdapter(orderResponseObjectList,orderStatusList);


                break;

            default:
                Converter<ResponseBody, UpcomingOrdersResponse> converter = RetrofitClient.getsInstance().responseBodyConverter(UpcomingOrdersResponse.class, new Annotation[0]);
                try {
                    UpcomingOrdersResponse error = converter.convert(response.errorBody());
                    Log.i(LOG_TAG, "Response code: "+response.code()+ " message: "+error.getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }

    @Override
    public void getUpcomingOrders(String deliveryManId, String accessToken) {
        upcomingOrdersService.getHistoryOrders(deliveryManId,accessToken,this);
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
                Converter<ResponseBody, OrderStatusResponse> converter = RetrofitClient.getsInstance().responseBodyConverter(UpcomingOrdersResponse.class, new Annotation[0]);
                try {
                    OrderStatusResponse error = converter.convert(response.errorBody());
                    Log.i(LOG_TAG, "Response code: "+response.code()+ " message: "+error.getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
