# How to run

## Requirements

* Java 17
* Gradle
* Docker Compose
* Docker

## Running the application

1. In directory /shoppingcart, build the code with `gradle build`
2. In the root / build docker images with `docker-compose build`
3. Run the application with `docker-compose up`
4. The application will be available at `http://localhost:8080`
5. The database will be available at `localhost:5432`
6. The database should be created at init and can be accessed with the following credentials:
   * User: `usc
   * Password: `psc
7. A postman collection have been added to the repository to ease the test of the APIs

# Architecture

* The application is a REST API that will be used by a frontend application.
* I chose to use Spring Boot because it is a framework that I am familiar with and has become almost one of the de facto standars in industry.
* A relational database was chosen to save the cart because the data is structured and the relationships between entities are well defined. 
* Given the nature of shopping carts, it is expected that the carts that do not become effective sales get deleted at some point. This is, the data in the tables form this project will be eventually deleted (probably after a certain period of time).

# Design decisions

* Totals are saved in the database to avoid recalculating it every time a request is made.
* Only artificial identifiers are used in the exercise. In a complete implementation natural identifiers would be added.
* Besides add and remove APIs an additional one was created to create the cart. This is to avoid creating a cart when adding a product to it.
* The customer needs to be created prior to the cart to be able to know if is a VIP or COMMON.
* Spring data rest is used because of the CRUD APIs that can be used to facilitate create data to test.

# APIs

## Create cart

An endpoint to create the cart. It receives a JSON object with the customer and the items. The customer is a URL to the customer resource. The items are a list of objects with the quantity and the product price URL.

```
curl --location 'http://localhost:8080/api/cart' \
--header 'Content-Type: application/json' \
--data '{
"customer": "http://localhost:8080/customers/2",
"items": [{
"quantity": 2.0,
"productPrice": "http://localhost:8080/productPrices/1"
},
{
"quantity": 2.0,
"productPrice": "http://localhost:8080/productPrices/3"
}
]
}
'
```

## Add to cart

Adds items to an existing cart. Can add multiple items at once. The items are a list of objects with the quantity and the product price URL.

```
curl --location 'http://localhost:8080/api/cart/6/item' \
--header 'Content-Type: application/json' \
--data '{
"items": [{
"quantity": 1.0,
"productPrice": "http://localhost:8080/productPrices/1"
},
{
"quantity": 1.0,
"productPrice": "http://localhost:8080/productPrices/2"
}
]
}
'
```

## Remove from cart

Removes items by subtracting 1 from the quantity. If the quantity is 1, the product is removed from the cart.

```
curl --location --request DELETE 'http://localhost:8080/api/cart/6/item/16'
```

# Assumptions

* The 3x2 promotion is a fixed promotion that is always available. It does not apply in an accumulative manner. This is, if a customer buys 3 products, they will get 1 for free. If they buy 6, they will get 1 for free (as opposed to apply it once more and get 2).
* The application does not have any authentication or authorization mechanisms.
* The Scenario 2 from the exercise is wrong. The total price before discounts is 202.98. The free item costs 35.99. The expected total should be 166.99
* The Scenario 4 from the exercise is wrong. The total price before discounts is 292.50. The free item costs 65.50. The expected total should be 227.00
* The delete endpoint is to remove one item from the quantity (cannot remove the product from the cart). If the quantity is 1, the product is removed from the cart.
* The customer should be created before the cart. It is not created by the cart API. During the computation of the totals, it identifies if it is VIP or COMMON
* The removal of an item in a single call when the quantity is greater than 1 is not implemented.
