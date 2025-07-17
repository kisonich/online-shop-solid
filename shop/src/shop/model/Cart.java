package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final String userId;
    private final List<Product> items = new ArrayList<>();

    public Cart(String userId) {
        this.userId = userId;
    }

    public void addItem(Product product) { items.add(product); }
    public void removeItem(Product product) { items.remove(product); }
    public void clearCart() { items.clear(); }
    public List<Product> getItems() { return new ArrayList<>(items); }
}