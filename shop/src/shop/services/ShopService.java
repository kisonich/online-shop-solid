
package shop.services;

import shop.model.Order;
import shop.model.Product;
import shop.model.Review;
import shop.repository.OrderRepository;
import shop.repository.ProductRepository;
import shop.repository.ReviewRepository;
import java.util.List;
import java.util.Optional;

public class ShopService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final RecommendationService recommendationService;

    public ShopService(
            ProductRepository productRepo,
            ReviewRepository reviewRepo,
            OrderRepository orderRepo,
            RecommendationService recService
    ) {
        this.productRepository = productRepo;
        this.reviewRepository = reviewRepo;
        this.orderRepository = orderRepo;
        this.recommendationService = recService;
    }

    public List<Product> browseProducts(String keyword, Double minPrice, Double maxPrice, String manufacturer) {
        return productRepository.findProducts(p ->
                (keyword == null || p.getName().contains(keyword)) &&
                        (minPrice == null || p.getPrice() >= minPrice) &&
                        (maxPrice == null || p.getPrice() <= maxPrice) &&
                        (manufacturer == null || p.getManufacturer().equals(manufacturer))
        );
    }

    public void addReview(Review review) {
        reviewRepository.addReview(review);
        updateProductRating(review.getProductId());
    }

    public Order placeOrder(String userId, List<Product> items) {
        Order newOrder = new Order(userId, items);
        orderRepository.addOrder(newOrder);
        return newOrder;
    }

    public void returnOrder(String orderId) {
        orderRepository.findOrderById(orderId)
                .ifPresent(order -> order.updateStatus(Order.Status.RETURNED));
    }

    public List<Product> getRecommendations(Product product) {
        return recommendationService.getRecommendations(product);
    }

    public List<Order> getUserOrders(String userId) {
        return orderRepository.getUserOrders(userId);
    }

    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findOrderById(orderId);
    }

    private void updateProductRating(String productId) {
        List<Review> reviews = reviewRepository.getReviewsForProduct(productId);
        double average = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        productRepository.getAllProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .ifPresent(p -> p.updateRating(average));
    }
}