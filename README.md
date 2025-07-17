# Online Shop - Реализация принципов SOLID

## Примененные принципы SOLID

### 1. Принцип единственной ответственности (SRP)
- **Описание**: Каждый класс должен иметь только одну причину для изменения
- **Пример**: `Cart.java` отвечает исключительно за управление корзиной пользователя
- **Код**: [src/shop/model/Cart.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/model/Cart.java)
- **Обоснование**: Класс содержит только методы для добавления/удаления товаров и очистки корзины, без смешивания с логикой заказов или оплаты

### 2. Принцип открытости/закрытости (OCP)
- **Описание**: Классы должны быть открыты для расширения, но закрыты для модификации
- **Пример**: Фильтрация товаров через `Predicate<Product>` в ProductRepository
- **Код**: [src/shop/repository/ProductRepository.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/repository/ProductRepository.java)
- **Обоснование**: Можно добавлять новые критерии фильтрации без изменения кода метода `findProducts`

### 3. Принцип подстановки Лисков (LSP)
- **Описание**: Объекты должны быть заменяемы экземплярами подтипов
- **Пример**: Все сущности (Product, Review) неизменяемы после создания
- **Код**: [src/shop/model/Product.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/model/Product.java)
- **Обоснование**: Поля объявлены как `final`, что гарантирует консистентность поведения при использовании в разных контекстах

### 4. Принцип разделения интерфейса (ISP)
- **Описание**: Клиенты не должны зависеть от интерфейсов, которые не используют
- **Пример**: Узкоспециализированные репозитории (ProductRepository, OrderRepository)
- **Код**: [src/shop/repository/OrderRepository.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/repository/OrderRepository.java)
- **Обоснование**: Каждый репозиторий предоставляет только методы, необходимые для работы с конкретной сущностью

### 5. Принцип инверсии зависимостей (DIP)
- **Описание**: Зависимости от абстракций, а не от конкретных реализаций
- **Пример**: Внедрение зависимостей через конструктор в ShopService
- **Код**: [src/shop/services/ShopService.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/services/ShopService.java)
- **Обоснование**: ShopService зависит от абстракций репозиториев, а не от их конкретных реализаций

## Другие принципы

### DRY (Don't Repeat Yourself)
- **Пример**: Метод `updateProductRating` в ShopService
- **Код**: [src/shop/services/ShopService.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/services/ShopService.java)
- **Обоснование**: Логика расчета рейтинга вынесена в отдельный метод и используется при каждом добавлении отзыва

### Избегание магических чисел
- **Пример**: Константа RECOMMENDATION_COUNT в RecommendationService
- **Код**: [src/shop/services/RecommendationService.java](https://github.com/kisonich/online-shop-solid/blob/main/src/shop/services/RecommendationService.java)
- **Обоснование**: Число 3 заменено на именованную константу для улучшения читаемости кода

## Запуск проекта

1. Клонируйте репозиторий:
```bash
git clone https://github.com/kisonich/online-shop-solid.git
cd online-shop-solid
