package shop.repository;

import shop.model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) { reviews.add(review); }

    public List<Review> getReviewsForProduct(String productId) {
        return reviews.stream()
                .filter(r -> r.getProductId().equals(productId))
                .toList();
    }
}