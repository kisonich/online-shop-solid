# Online Shop - Реализация принципов SOLID

## Примененные принципы SOLID

### 1. Принцип единственной ответственности (SRP)
- **Описание**: Каждый класс должен иметь только одну причину для изменения
- **Пример**: `Cart.java` отвечает исключительно за управление корзиной пользователя
- **Код**: [Cart.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/model/Cart.java)
- **Обоснование**: Класс содержит только методы для добавления/удаления товаров и очистки корзины

### 2. Принцип открытости/закрытости (OCP)
- **Описание**: Классы должны быть открыты для расширения, но закрыты для модификации
- **Пример**: Фильтрация товаров через `Predicate<Product>` в ProductRepository
- **Код**: [ProductRepository.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/repository/ProductRepository.java)
- **Обоснование**: Новые критерии фильтрации добавляются без изменения кода метода

### 3. Принцип подстановки Лисков (LSP)
- **Описание**: Объекты должны быть заменяемы экземплярами подтипов
- **Пример**: Все сущности (Product, Review) неизменяемы после создания
- **Код**: [Product.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/model/Product.java)
- **Обоснование**: Поля объявлены как `final`, что гарантирует консистентность поведения

### 4. Принцип разделения интерфейса (ISP)
- **Описание**: Клиенты не должны зависеть от интерфейсов, которые не используют
- **Пример**: Узкоспециализированные репозитории (ProductRepository, OrderRepository)
- **Код**: [OrderRepository.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/repository/OrderRepository.java)
- **Обоснование**: Каждый репозиторий предоставляет только необходимые методы

### 5. Принцип инверсии зависимостей (DIP)
- **Описание**: Зависимости от абстракций, а не от конкретных реализаций
- **Пример**: Внедрение зависимостей через конструктор в ShopService
- **Код**: [ShopService.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/services/ShopService.java)
- **Обоснование**: ShopService зависит от абстракций репозиториев

## Другие принципы

### DRY (Don't Repeat Yourself)
- **Пример**: Метод `updateProductRating` в ShopService
- **Код**: [ShopService.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/services/ShopService.java)
- **Обоснование**: Логика расчета рейтинга вынесена в отдельный метод

### Избегание магических чисел
- **Пример**: Константа RECOMMENDATION_COUNT в RecommendationService
- **Код**: [RecommendationService.java](https://github.com/kisonich/online-shop-solid/blob/main/shop/src/shop/services/RecommendationService.java)
- **Обоснование**: Число 3 заменено на именованную константу

## Запуск проекта

1. Клонируйте репозиторий:
```bash
git clone https://github.com/kisonich/online-shop-solid.git
cd online-shop-solid/shop
