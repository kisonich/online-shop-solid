package shop.ui;

import shop.model.Cart;
import shop.model.Order;
import shop.model.Product;
import shop.model.Review;
import shop.services.ShopService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInterface {
    private final ShopService shopService;
    private final Scanner scanner = new Scanner(System.in);
    private Cart currentCart;
    private String currentUserId;

    public ConsoleInterface(ShopService shopService) {
        this.shopService = shopService;
    }

    public void start() {
        loginUser();
        mainMenu();
    }

    private void loginUser() {
        System.out.print("Введите ваш ID: ");
        currentUserId = scanner.nextLine();
        currentCart = new Cart(currentUserId);
        System.out.println("Добро пожаловать, пользователь " + currentUserId + "!");
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Просмотр товаров");
            System.out.println("2. Корзина");
            System.out.println("3. История заказов");
            System.out.println("4. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // очистка буфера

            switch (choice) {
                case 1 -> browseProducts();
                case 2 -> cartMenu();
                case 3 -> orderHistory();
                case 4 -> { return; }
                default -> System.out.println("Неверный ввод!");
            }
        }
    }

    private void browseProducts() {
        System.out.println("\n=== Каталог товаров ===");
        List<Product> products = shopService.browseProducts(null, null, null, null);

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.printf("%d. %s - $%.2f [%s] ★%.1f%n",
                    i + 1, p.getName(), p.getPrice(), p.getManufacturer(), p.getRating());
        }

        System.out.print("\nВведите номер товара для действий (0 - назад): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= products.size()) {
            productActions(products.get(choice - 1));
        }
    }

    private void productActions(Product product) {
        System.out.println("\n" + product.getName());
        System.out.println("Цена: $" + product.getPrice());
        System.out.println("Производитель: " + product.getManufacturer());
        System.out.println("Рейтинг: ★" + product.getRating());

        System.out.println("\n1. Добавить в корзину");
        System.out.println("2. Оставить отзыв");
        System.out.println("3. Рекомендации");
        System.out.println("4. Назад");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                currentCart.addItem(product);
                System.out.println("Товар добавлен в корзину!");
            }
            case 2 -> leaveReview(product);
            case 3 -> showRecommendations(product);
        }
    }

    private void leaveReview(Product product) {
        System.out.print("Оценка (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Комментарий: ");
        String comment = scanner.nextLine();

        Review review = new Review(product.getId(), currentUserId, rating, comment);
        shopService.addReview(review);
        System.out.println("Спасибо за ваш отзыв!");
    }

    private void showRecommendations(Product product) {
        System.out.println("\nРекомендуемые товары:");
        List<Product> recommendations = shopService.getRecommendations(product);
        recommendations.forEach(p ->
                System.out.println("- " + p.getName() + " ($" + p.getPrice() + ")")
        );
    }

    private void cartMenu() {
        System.out.println("\n=== Ваша корзина ===");
        List<Product> items = currentCart.getItems();

        if (items.isEmpty()) {
            System.out.println("Корзина пуста!");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            Product p = items.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, p.getName(), p.getPrice());
        }

        System.out.println("\n1. Оформить заказ");
        System.out.println("2. Удалить товар");
        System.out.println("3. Очистить корзину");
        System.out.println("4. Назад");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> placeOrder();
            case 2 -> removeItemFromCart();
            case 3 -> {
                currentCart.clearCart();
                System.out.println("Корзина очищена!");
            }
        }
    }

    private void placeOrder() {
        if (currentCart.getItems().isEmpty()) {
            System.out.println("Корзина пуста!");
            return;
        }

        Order order = shopService.placeOrder(currentUserId, currentCart.getItems());
        currentCart.clearCart();
        System.out.println("Заказ оформлен! ID: " + order.getId());
    }

    private void removeItemFromCart() {
        System.out.print("Введите номер товара для удаления: ");
        int itemNum = scanner.nextInt();
        scanner.nextLine();

        List<Product> items = currentCart.getItems();
        if (itemNum > 0 && itemNum <= items.size()) {
            Product toRemove = items.get(itemNum - 1);
            currentCart.removeItem(toRemove);
            System.out.println("Товар удалён из корзины");
        } else {
            System.out.println("Неверный номер товара!");
        }
    }

    private void orderHistory() {
        System.out.println("\n=== История заказов ===");
        List<Order> orders = shopService.getUserOrders(currentUserId);

        if (orders.isEmpty()) {
            System.out.println("У вас нет заказов");
            return;
        }

        for (Order order : orders) {
            System.out.printf("Заказ %s: %s - %s%n",
                    order.getId(),
                    order.getOrderDate().toString(),
                    order.getStatus().toString());
        }

        System.out.print("\nВведите ID заказа для действий (0 - назад): ");
        String orderId = scanner.nextLine();

        if (!orderId.equals("0")) {
            orderActions(orderId);
        }
    }

    private void orderActions(String orderId) {
        Optional<Order> orderOpt = shopService.getOrderById(orderId);
        if (orderOpt.isEmpty()) {
            System.out.println("Заказ не найден!");
            return;
        }

        Order order = orderOpt.get();
        System.out.println("\nСтатус заказа: " + order.getStatus());
        System.out.println("1. Повторить заказ");
        System.out.println("2. Вернуть заказ");
        System.out.println("3. Назад");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> repeatOrder(order);
            case 2 -> returnOrder(order);
        }
    }

    private void repeatOrder(Order order) {
        order.getItems().forEach(currentCart::addItem);
        System.out.println("Товары добавлены в корзину!");
    }

    private void returnOrder(Order order) {
        if (order.getStatus() == Order.Status.DELIVERED) {
            shopService.returnOrder(order.getId());
            System.out.println("Заказ возвращен!");
        } else {
            System.out.println("Возврат возможен только для доставленных заказов!");
        }
    }
}