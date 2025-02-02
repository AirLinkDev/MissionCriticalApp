package java.com.example.microservices.orderms.application;

import java.com.example.microservices.orderms.domain.Order;
import java.com.example.microservices.orderms.domain.OrderStatus;
import java.com.example.microservices.orderms.infrastructure.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(String customerId, double amount) {
        Order order = new Order(customerId, amount);
        return repository.save(order);
    }

    public Order completeOrder(String orderId) {
        Order order = repository.findById(orderId);
        if (order != null && order.getStatus() == OrderStatus.PENDING) {
            order.completeOrder();
            repository.save(order);
        }
        return order;
    }

    public Order cancelOrder(String orderId) {
        Order order = repository.findById(orderId);
        if (order != null && order.getStatus() == OrderStatus.PENDING) {
            order.cancelOrder();
            repository.save(order);
        }
        return order;
    }
}
