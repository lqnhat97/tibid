package org.tibid.entity.tiki.response;

import com.google.gson.annotations.SerializedName;
import org.tibid.entity.tiki.Customer;
import org.tibid.entity.tiki.Order;

import java.util.List;

public class Data {
    private Order order;

    @SerializedName(value = "access_token")
    private String accessToken;

    @SerializedName(value = "expire_in")
    private String expireIn;

    @SerializedName(value = "refresh_token")
    private String refreshToken;

    private List<String> scopes;

    @SerializedName(value = "token_type")
    private String tokenType;

    private Customer customer;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(String expireIn) {
        this.expireIn = expireIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
