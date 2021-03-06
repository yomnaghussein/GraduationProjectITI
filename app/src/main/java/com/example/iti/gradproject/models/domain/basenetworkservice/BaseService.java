package com.example.iti.gradproject.models.domain.basenetworkservice;

import com.example.iti.gradproject.models.entities.LoginResponse;
import com.example.iti.gradproject.models.entities.OrderStatusResponse;
import com.example.iti.gradproject.models.entities.UpdateOrderResponse;
import com.example.iti.gradproject.models.entities.UserProfileResponse;
import com.example.iti.gradproject.models.entities.historyorders.HistoryOrdersResponse;
import com.example.iti.gradproject.models.entities.upcomingorders.UpcomingOrdersResponse;

import retrofit2.Response;

public interface BaseService {
    void onError();

    interface Login extends BaseService {
        void onSuccess(Response<LoginResponse> response);
    }
    interface ViewUpcomingOrders extends BaseService {
        void onSuccess(Response<UpcomingOrdersResponse> response);
    }
    interface ViewHistoryOrders extends BaseService {
        void onSuccess(Response<HistoryOrdersResponse> response);
    }
    interface ViewOrderStatus extends BaseService {
        void onSuccessOrders(Response<OrderStatusResponse> response);
    }
    interface ViewUserProfile extends BaseService {
        void onSuccessView(Response<UserProfileResponse> response);
    }

}
