package com.ecommerce.order.query.representation;

import com.ecommerce.shared.exception.AppException;

import static com.ecommerce.order.query.representation.OrderQueryErrorCode.ORDER_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;

public class OrderNotFoundException extends AppException {
    public OrderNotFoundException(String orderId) {
        super(ORDER_NOT_FOUND, of("orderId", orderId));
    }
}
