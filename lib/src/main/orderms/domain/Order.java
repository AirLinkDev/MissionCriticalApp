package java.com.example.microservices.orderms.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private String id;
    private String customerId;
    private double amount;
    private LocalDateTime createdAt;
    private OrderStatus status;

    public Order(String customerId, double amount) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public void completeOrder() {
        if (this.status == OrderStatus.PENDING) {
            this.status = OrderStatus.COMPLETED;
        }
    }

    public void cancelOrder() {
        if (this.status == OrderStatus.PENDING) {
            this.status = OrderStatus.CANCELED;
        }
    }

    // Getters/Setters omitted for brevity
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderStatus getStatus() { return status; }
}
