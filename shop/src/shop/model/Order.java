package shop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    public enum Status { PENDING, SHIPPED, DELIVERED, RETURNED }

    private final String id;
    private final String userId;
    private final LocalDateTime orderDate;
    private Status status;
    private final List<Product> items = new ArrayList<>();

    public Order(String userId, List<Product> items) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.orderDate = LocalDateTime.now();
        this.status = Status.PENDING;
        this.items.addAll(items);
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public Status getStatus() { return status; }
    public List<Product> getItems() { return new ArrayList<>(items); }
    public void updateStatus(Status newStatus) { this.status = newStatus; }
}