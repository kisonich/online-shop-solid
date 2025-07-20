SOLID Principles

Single Responsibility Principle (SRP)
Cart.java
Только управление корзиной (добавление/удаление товаров)
java
public void addItem(Product product) { items.add(product); }
public void removeItem(Product product) { items.remove(product); }
Open/Closed Principle (OCP)
ProductRepository.java#L14-L18
Фильтрация через Predicate (расширяемо без изменений кода)
java
public List<Product> findProducts(Predicate<Product> filter) {
    return products.stream().filter(filter).toList();
}
Liskov Substitution Principle (LSP)
Product.java#L3-L7
Неизменяемые поля (final)
java
private final String id;
private final String name;
Interface Segregation Principle (ISP)
OrderRepository.java
Узкие методы для работы с заказами
java
public Optional<Order> findOrderById(String id) { ... }
Dependency Inversion Principle (DIP)
ShopService.java#L15-L22
Внедрение зависимостей через конструктор
java
public ShopService(ProductRepository productRepo, ...) {
    this.productRepository = productRepo;
}
DRY (Don’t Repeat Yourself)

ShopService.java#L56-L66
Вынесение логики расчета рейтинга в метод updateProductRating
java
private void updateProductRating(String productId) {
    double average = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    ...
}
Избегание магических чисел (Magics)

RecommendationService.java#L5-L6
Константы вместо чисел
java
public static final int MAX_RECOMMENDATIONS = 3;
public static final int MIN_PRODUCTS_FOR_RECOMMENDATIONS = 1;
