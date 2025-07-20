package shop.model;

public class Product {
    private final String id;
    private final String name;
    private final double price;
    private final String manufacturer;
    private double rating;

    public Product(String id, String name, double price, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.rating = 0.0;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getManufacturer() { return manufacturer; }
    public double getRating() { return rating; }
    public void updateRating(double newRating) { this.rating = newRating; }
}