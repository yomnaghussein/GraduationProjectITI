package com.example.iti.gradproject.screens.homescreen;

public interface HomeScreenContract {

    interface HomePresenter{
        void updateOrderStatus(long orderId, String orderStatus);
    }
    interface HomeScreen{
        OrderListAdapter.OrderListViewHolder getHolder();
        void updateText(OrderListAdapter.OrderListViewHolder holder, String orderStatus);
    }
}
