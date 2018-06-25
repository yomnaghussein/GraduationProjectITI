
package com.example.iti.gradproject.models.entities;

import com.example.iti.gradproject.models.entities.historyorders.DeliveryLocation;
import com.example.iti.gradproject.models.entities.historyorders.HubLocation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponseObject {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("hubName")
    @Expose
    private String hubName;
    @SerializedName("hubLocation")
    @Expose
    private HubLocation hubLocation;
    @SerializedName("deliveryLocation")
    @Expose
    private DeliveryLocation deliveryLocation;
    @SerializedName("deliveryDueDate")
    @Expose
    private String deliveryDueDate;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public HubLocation getHubLocation() {
        return hubLocation;
    }

    public void setHubLocation(HubLocation hubLocation) {
        this.hubLocation = hubLocation;
    }

    public DeliveryLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryDueDate() {
        return deliveryDueDate;
    }

    public void setDeliveryDueDate(String deliveryDueDate) {
        this.deliveryDueDate = deliveryDueDate;
    }

}
