
package com.example.iti.gradproject.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOrderResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderResponseObject")
    @Expose
    private Object orderResponseObject;

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

    public Object getOrderResponseObject() {
        return orderResponseObject;
    }

    public void setOrderResponseObject(Object orderResponseObject) {
        this.orderResponseObject = orderResponseObject;
    }

}
