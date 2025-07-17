package shop.services;

import shop.model.Product;
import shop.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecommendationService {
    private final ProductRepository productRepository;

    public RecommendationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getRecommendations(Product baseProduct) {
        List<Product> allProducts = new ArrayList<>(productRepository.getAllProducts());
        allProducts.remove(baseProduct);

        Collections.shuffle(allProducts, new Random());
        return allProducts.subList(0, Math.min(3, allProducts.size()));
    }
}