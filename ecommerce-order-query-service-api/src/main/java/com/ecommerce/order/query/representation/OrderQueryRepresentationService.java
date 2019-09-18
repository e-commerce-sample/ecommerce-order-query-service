package com.ecommerce.order.query.representation;

import com.ecommerce.order.query.sdk.representation.order.OrderWithProductRepresentation;
import com.ecommerce.order.query.sdk.representation.order.Product;
import com.ecommerce.order.sdk.representation.order.OrderRepresentation;
import com.ecommerce.product.sdk.representation.product.ProductRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderQueryRepresentationService {
    private RestTemplate restTemplate;
    private OrderWithProductDao dao;

    public OrderQueryRepresentationService(RestTemplate restTemplate,
                                           OrderWithProductDao dao) {
        this.restTemplate = restTemplate;
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    public OrderWithProductRepresentation byId(String orderId) {
        return dao.byId(orderId);
    }

    public void cqrsSync(String orderId) {
        String orderUrl = "http://localhost:8080/orders/{id}";
        String productUrl = "http://localhost:8082/products/{id}";

        OrderRepresentation orderRepresentation = restTemplate.getForObject(orderUrl, OrderRepresentation.class, orderId);

        List<Product> products = orderRepresentation.getItems().stream().map(orderItem -> {
            ProductRepresentation productRepresentation = restTemplate.getForObject(productUrl,
                    ProductRepresentation.class,
                    orderItem.getProductId());

            return new Product(productRepresentation.getId(),
                    productRepresentation.getName(),
                    productRepresentation.getDescription());
        }).collect(Collectors.toList());

        OrderWithProductRepresentation order = new OrderWithProductRepresentation(
                orderRepresentation.getId(),
                orderRepresentation.getTotalPrice(),
                orderRepresentation.getStatus(),
                orderRepresentation.getCreatedAt(),
                orderRepresentation.getAddress(),
                products

        );
        dao.save(order);
        log.info("CQRS synced order {}.",orderId);
    }
}
