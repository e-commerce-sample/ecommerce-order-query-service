package com.ecommerce.order.query.representation;

import com.ecommerce.order.sdk.event.order.OrderEvent;
import com.ecommerce.spring.common.event.messaging.rabbit.EcommerceRabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

// TODO: 2019-09-18 Need to handle product name changed event
@Component
@EcommerceRabbitListener
public class OrderQueryRabbitListener {
    private OrderQueryEventHandler queryEventHandler;

    public OrderQueryRabbitListener(OrderQueryEventHandler queryEventHandler) {
        this.queryEventHandler = queryEventHandler;
    }

    @RabbitHandler
    public void on(OrderEvent event) {
        queryEventHandler.cqrsSync(event);
    }

}
