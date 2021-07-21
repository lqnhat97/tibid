package org.tibid.entity.tiki.request;

import com.google.gson.annotations.SerializedName;
import org.tibid.entity.tiki.Address;
import org.tibid.entity.tiki.Item;

import java.util.List;

public class BaseRequest {
    @SerializedName(value="customer_id")
    private String customerId;

    @SerializedName(value="items")
    private List<Item> items;

    @SerializedName(value="shipping_address")
    private Address shippingAddress;

    @SerializedName(value="billing_address")
    private Address billingAddress;

    @SerializedName(value="extra")
    private String extra;

    @SerializedName(value="reference_id")
    private String referenceId;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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
