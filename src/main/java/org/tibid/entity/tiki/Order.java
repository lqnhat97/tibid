package org.tibid.entity.tiki;

import com.google.gson.annotations.SerializedName;

public class Order {
    private String id;
    private String status;
    @SerializedName(value="grand_total")
    private Integer grandTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }
}
