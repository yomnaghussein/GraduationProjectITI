package com.example.iti.gradproject.screens.homescreen.InProcessScreen;

import com.example.iti.gradproject.models.entities.OrderResponseObject;

import java.util.List;

public interface InProcessContract {
    public interface InProcessPresenter{
        public void getUpcomingOrders(String deliveryManId, String accessToken);
    }
    public interface InProcessFragment{
        void setUpcomingOrdersAdapter(List<OrderResponseObject> orderResponseObjectList);
    }
}

