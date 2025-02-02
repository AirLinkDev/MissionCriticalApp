package java.com.example.microservices.orderms.infrastructure;

import java.com.example.microservices.orderms.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    private final Map<String, Order> orderMap = new HashMap<>();

    public Order save(Order order) {
        orderMap.put(order.getId(), order);
        return order;
    }

    public Order findById(String orderId) {
        return orderMap.get(orderId);
    }
}
