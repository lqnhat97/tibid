package org.tibid.entity.tiki.ipn.request;

import com.google.gson.annotations.SerializedName;
import org.tibid.entity.tiki.Order;

import java.io.Serializable;

public class IpnRequest implements Serializable {
    @SerializedName(value = "message_id")
    private String messageId;
    @SerializedName(value = "message_type")
    private String messageType;
    @SerializedName(value = "message_created_at")
    private String messageCreatedAt;
    @SerializedName(value = "order")
    private Order order;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageCreatedAt() {
        return messageCreatedAt;
    }

    public void setMessageCreatedAt(String messageCreatedAt) {
        this.messageCreatedAt = messageCreatedAt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
