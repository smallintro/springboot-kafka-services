package io.github.smallintro.kafka.orderprocessor.model;

import java.util.Date;

public class OrderInfo {
    private Long orderId;
    private Date orderTime;
    private Integer orderArea;
    private String orderStatus;

    public OrderInfo() {
    }

    public OrderInfo(Long orderId, Date orderTime, Integer orderArea, String orderStatus) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderArea = orderArea;
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderArea() {
        return orderArea;
    }

    public void setOrderArea(Integer orderArea) {
        this.orderArea = orderArea;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", orderArea=" + orderArea +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
