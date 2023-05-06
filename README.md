# Ecommerce-Rest-Mircorservice-RecommendationSystem

> This project is a microservices-based recommendation system for an ecommerce platform, designed to provide personalized product recommendations to users. The system is secured with Keycloak Auth server and JWT tokens              

# Table of contents
- [Ecommerce-Rest-Mircorservice-RecommendationSystem](#ecommerce-rest-mircorservice-recommendationsystem)
- [Table of contents](#table-of-contents)
- [Features](#features)
- [Project Documentation](#project-documentation)
- [Project Architecture](#project-architecture)
- [Tools and Technologies](#tools-and-technologies)
- [Status:](#status)
# Features
- Microservices Architecture for scalability <!--- //and fault tolerance -->.
- Load Balancing using Eureka Server for better performance and availability.
- Authentication and Authorization with Keycloak Auth server and JWT tokens for secure access control.
- Personalized Product Recommendations based on user orders history (under devlopment).
- Admin Endpoints for managing the product catalog.
- Cart Management for adding, removing, and updating items before checkout.
- Order Creation with price calculation and order confirmation (order payment and confirmation under devlopment).
- Order History for viewing past orders.
- Scalability by adding more instances or resources to a service.
- Global Exception handling. <!--- //and logging -->
# Project Documentation
Detailed documentation of the project can be found in [Postman](https://documenter.getpostman.com/view/23987687/2s93eX2ZTi). This includes API documentation and usage examples.

# Project Architecture
The project is built using a microservices architecture, with each microservice responsible for a specific task. The microservices are:
- **Product Service:** This microservice is responsible for managing the product catalog, including adding new products/categories, updating existing products/categories, and retrieving product/category information.
- **Order Service:** This microservice is responsible for managing user orders/carts, including creating new orders/carts, updating carts, and retrieving order information
- **Recommendation Service:** This microservice is responsible for generating personalized product recommendations for users based on their purchasing history.(under development)
- **Auth Service:** This microservice is responsible for user registration, authentication and authorization using Keycloak Auth server and JWT tokens.
- **Api Gateway:** This microservice acts as a single entry point for all incoming requests, and routes requests to the appropriate microservice.
- **Eureka Server:** This microservice is a naming server for registering microservices.




![micro](https://github.com/zeyadahmed10/Ecommerce-Rest-Mircorservice-RecommendationSystem/blob/master/Ecommerce-microservice2.png)



# Tools and Technologies

- **Java 17**
- **Spring Boot** - version 3.0.4
- **Spring Cloud** - version 2202.0.1
- **KeyCloak** - version 21.0.1
- **Spring Web**
- **MySQL Driver**
- **OAuth Resource Server**
- **Open Feign** 
- **Eureka Server**
- **Spring Gateway**
- **Spring Data Jpa**
- **Hibernate Validator**
- **Lombok**
- **Maven**

   
# Status:

**Application status :** BETA