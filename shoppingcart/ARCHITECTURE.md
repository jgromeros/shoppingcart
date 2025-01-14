# Architecture

* The application is a REST API that will be used by a frontend application.
* I chose to use Spring Boot because it is a framework that I am familiar with and has become almost one of the de facto standars in industry.
* A relational database was chosen to save the cart because the data is structured and the relationships between entities are well defined.

# Design decisions

* Totals are saved in the database to avoid recalculating it every time a request is made.
* Only artificial identifiers are used in the exercise. In a complete implementation natural identifiers would be added. 

# Assumptions

* The 3x2 promotion is a fixed promotion that is always available. It does not apply in an accumulative manner. This is, if a customer buys 3 products, they will get 1 for free. If they buy 6, they will get 1 for free (as opposed to apply it once more and get 2).
* The application does not have any authentication or authorization mechanisms.
* The Scenario 2 from the exercise is wrong. The total price before discounts is 202.98. The free item costs 35.99. The expected total should be 166.99
* The Scenario 4 from the exercise is wrong. The total price before discounts is 292.50. The free item costs 65.50. The expected total should be 227.00
* The delete endpoint is to remove one item from the quantity (cannot remove the product from the cart). If the quantity is 1, the product is removed from the cart.
* 