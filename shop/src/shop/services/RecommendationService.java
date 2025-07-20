package shop.services;

import shop.model.Product;
import shop.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecommendationService {
    public static final int MAX_RECOMMENDATIONS = 3;
    public static final int MIN_PRODUCTS_FOR_RECOMMENDATIONS = 1;

    private final ProductRepository productRepository;

    public RecommendationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getRecommendations(Product baseProduct) {
        List<Product> allProducts = new ArrayList<>(productRepository.getAllProducts());
        allProducts.remove(baseProduct);

        if (allProducts.size() < MIN_PRODUCTS_FOR_RECOMMENDATIONS) {
            return Collections.emptyList();
        }

        Collections.shuffle(allProducts, new Random());
        return allProducts.subList(0, Math.min(MAX_RECOMMENDATIONS, allProducts.size()));
    }
}