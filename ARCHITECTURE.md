# Architecture

* The application is a REST API that will be used by a frontend application.
* I chose to use Spring Boot because it is a framework that I am familiar with and has become one of the de facto standars in industry.
* A relational database was chosen to save the sales because the data is structured and the relationships between entities are well defined.

# Design decisions

* Total price is saved in the database to avoid recalculating it every time a request is made. 

# Assumptions

* The 3x2 promotion is a fixed promotion that is always available. It does not apply in an accumulative manner. This is, if a customer buys 3 products, they will get 1 for free. If they buy 6, they will get 1 for free (as opposed to apply it once more and get 2).
* The application does not have any authentication or authorization mechanisms.
* 