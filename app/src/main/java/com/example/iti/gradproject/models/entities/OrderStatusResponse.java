
package com.example.iti.gradproject.models.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderStatusResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderResponseObject")
    @Expose
    private List<String> orderResponseObject = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getOrderResponseObject() {
        return orderResponseObject;
    }

    public void setOrderResponseObject(List<String> orderResponseObject) {
        this.orderResponseObject = orderResponseObject;
    }

}
