package shop;

import shop.model.Product;
import shop.repository.OrderRepository;
import shop.repository.ProductRepository;
import shop.repository.ReviewRepository;
import shop.services.RecommendationService;
import shop.services.ShopService;
import shop.ui.ConsoleInterface;

public class Main {
    public static void main(String[] args) {
        // Инициализация репозиториев
        ProductRepository productRepo = new ProductRepository();
        ReviewRepository reviewRepo = new ReviewRepository();
        OrderRepository orderRepo = new OrderRepository();

        // Добавление тестовых товаров
        Product p1 = new Product("P1", "Ноутбук", 999.99, "Dell");
        Product p2 = new Product("P2", "Смартфон", 699.99, "Samsung");
        Product p3 = new Product("P3", "Планшет", 499.99, "Apple");
        productRepo.addProduct(p1);
        productRepo.addProduct(p2);
        productRepo.addProduct(p3);

        RecommendationService recService = new RecommendationService(productRepo);

        ShopService shopService = new ShopService(
                productRepo,
                reviewRepo,
                orderRepo,
                recService
        );

        ConsoleInterface ui = new ConsoleInterface(shopService);
        ui.start();
    }
}