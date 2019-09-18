package com.ecommerce.order.query.representation;

import com.ecommerce.order.sdk.event.order.OrderEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderQueryEventHandler {
    private final OrderQueryRepresentationService orderRepresentationService;

    public OrderQueryEventHandler(OrderQueryRepresentationService orderRepresentationService) {
        this.orderRepresentationService = orderRepresentationService;
    }

    public void cqrsSync(OrderEvent event) {
        orderRepresentationService.cqrsSync(event.getOrderId());
    }
}
