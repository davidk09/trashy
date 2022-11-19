package com.example.trashy.requests;

import com.example.trashy.domain.ExchangeOrder;

public class OrderRequest {


    private Long userId;

    private ExchangeOrder order;


    public OrderRequest(Long userId, ExchangeOrder order) {
        this.userId = userId;
        this.order = order;
    }

    public OrderRequest() {

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ExchangeOrder getOrder() {
        return order;
    }

    public void setOrder(ExchangeOrder order) {
        this.order = order;
    }
}
