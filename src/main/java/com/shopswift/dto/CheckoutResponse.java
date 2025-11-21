package com.shopswift.dto;

public class CheckoutResponse {
    private Long orderId;
    private String status;

    public CheckoutResponse() {}

    public CheckoutResponse(Long orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Long getOrderId() { return orderId; }
    public String getStatus() { return status; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public void setStatus(String status) { this.status = status; }
}
