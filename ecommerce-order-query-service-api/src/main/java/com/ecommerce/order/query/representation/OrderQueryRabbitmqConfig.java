package com.ecommerce.order.query.representation;

import com.ecommerce.spring.common.event.messaging.rabbit.EcommerceRabbitProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class OrderQueryRabbitmqConfig {


    private EcommerceRabbitProperties properties;

    public OrderQueryRabbitmqConfig(EcommerceRabbitProperties properties) {
        this.properties = properties;
    }


    @Bean
    public Binding bindToOrder() {
        return new Binding(properties.getReceiveQ(),
                QUEUE,
                "order-publish-x",
                "com.ecommerce.order.sdk.event.order.#",
                null);
    }

}
