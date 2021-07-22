package org.tibid.entity.tiki.request;

import com.google.gson.annotations.SerializedName;

public class CompleteRequest {
    @SerializedName(value="order_id")
    private String orderId;

    private String comment;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
