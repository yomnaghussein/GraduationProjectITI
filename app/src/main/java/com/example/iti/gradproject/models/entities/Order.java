package com.example.iti.gradproject.models.entities;

public class Order {
    String deadline;
    String orderID;
    String status;

    public String getDeadline() {
        return deadline;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
