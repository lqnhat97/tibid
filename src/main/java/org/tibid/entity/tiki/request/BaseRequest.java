package org.tibid.entity.tiki.request;

import com.google.gson.annotations.SerializedName;
import org.tibid.entity.tiki.Address;
import org.tibid.entity.tiki.Items;

public class BaseRequest {
    @SerializedName(value="customer_id")
    private String customerId;

    @SerializedName(value="items")
    private Items items;

    @SerializedName(value="shipping_address")
    private Address shippingAddress;

    @SerializedName(value="billing_address")
    private Address billingAddress;

    @SerializedName(value="extra")
    private String extra;

    @SerializedName(value="reference_id")
    private String referenceId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
