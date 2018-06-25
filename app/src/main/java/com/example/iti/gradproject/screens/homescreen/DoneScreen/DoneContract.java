package com.example.iti.gradproject.screens.homescreen.DoneScreen;

import com.example.iti.gradproject.models.entities.OrderResponseObject;

import java.util.List;

public interface DoneContract {
    public interface DonePresenter{
        public void getHistoryOrders(String deliveryManId, String accessToken);
    }
    public interface DoneFragment{
        void setHistoryOrdersAdapter(List<OrderResponseObject> orderResponseObjectList);
    }
}
