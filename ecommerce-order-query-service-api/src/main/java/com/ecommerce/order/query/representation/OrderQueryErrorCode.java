package com.ecommerce.order.query.representation;


import com.ecommerce.shared.exception.ErrorCode;

public enum OrderQueryErrorCode implements ErrorCode {
    ORDER_NOT_FOUND(404, "没有找到订单");
    private int status;
    private String message;

    OrderQueryErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
