
package  com.example.iti.gradproject.models.entities.upcomingorders;

import java.util.List;

import com.example.iti.gradproject.models.entities.OrderResponseObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingOrdersResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderResponseObject")
    @Expose
    private List<OrderResponseObject> orderResponseObject = null;

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

    public List<OrderResponseObject> getOrderResponseObject() {
        return orderResponseObject;
    }

    public void setOrderResponseObject(List<OrderResponseObject> orderResponseObject) {
        this.orderResponseObject = orderResponseObject;
    }

}
