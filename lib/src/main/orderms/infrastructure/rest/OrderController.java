package java.com.example.microservices.orderms.infrastructure.rest;

import java.com.example.microservices.orderms.application.OrderService;
import java.com.example.microservices.orderms.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * In Java, the @ symbol denotes an annotation. 
 * Annotations are a form of metadata that you attach to 
 * classes, methods, parameters, fields, and other elements. 
 * They serve various purposes—such as describing configuration for frameworks, 
 * generating boilerplate code, or guiding runtime behavior. 
 * The Java language itself provides several built-in annotations 
 * (e.g., @Override, @Deprecated, @SuppressWarnings), and many third-party frameworks 
 * (like Spring or JUnit) define their own annotations for specialized tasks.
 */
// Tells Spring that this class contains RESTful endpoints.
// Spring will scan your project for classes annotated with 
// @RestController (or @Controller), then instantiate them 
// and map HTTP requests to the appropriate methods.
@RestController
// Specifies the base URL for all endpoints in this class.
// In this example, it means every method in OrderController begins with /api/orders
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    /**
     * Indicates that the annotated method will handle HTTP POST requests to a specified path.
     * For instance, @PostMapping("/{orderId}/complete") means a POST request 
     * to /api/orders/{orderId}/complete will trigger the completeOrder(...) method.
     * @param customerId
     * @param amount
     * @return
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam String customerId,
                                             @RequestParam double amount) {
        Order order = orderService.createOrder(customerId, amount);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<Order> completeOrder(@PathVariable String orderId) {
        Order order = orderService.completeOrder(orderId);
        return order != null
            ? ResponseEntity.ok(order)
            : ResponseEntity.notFound().build();
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable String orderId) {
        Order order = orderService.cancelOrder(orderId);
        return order != null
            ? ResponseEntity.ok(order)
            : ResponseEntity.notFound().build();
    }
}
