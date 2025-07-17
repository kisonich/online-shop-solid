package shop.repository;

import shop.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) { orders.add(order); }

    public Optional<Order> findOrderById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public List<Order> getUserOrders(String userId) {
        return orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .toList();
    }
}