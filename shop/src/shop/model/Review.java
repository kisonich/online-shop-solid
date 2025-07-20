package shop.model;

public class Review {
    private final String productId;
    private final String userId;
    private final int rating;
    private final String comment;

    public Review(String productId, String userId, int rating, String comment) {
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getProductId() { return productId; }
    public String getUserId() { return userId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}