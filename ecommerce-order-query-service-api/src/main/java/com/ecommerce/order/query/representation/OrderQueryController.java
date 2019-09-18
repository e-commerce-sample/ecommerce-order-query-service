package com.ecommerce.order.query.representation;

import com.ecommerce.order.query.sdk.representation.order.OrderWithProductRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders/query")
public class OrderQueryController {

    private OrderQueryRepresentationService service;

    public OrderQueryController(OrderQueryRepresentationService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public OrderWithProductRepresentation byId(@PathVariable(name = "id") String orderId) {
        return service.byId(orderId);
    }

}
