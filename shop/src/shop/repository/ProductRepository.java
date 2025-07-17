package shop.repository;

import shop.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) { products.add(product); }
    public List<Product> getAllProducts() { return new ArrayList<>(products); }

    public List<Product> findProducts(Predicate<Product> filter) {
        return products.stream()
                .filter(filter)
                .toList();
    }
}