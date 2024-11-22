# booksAPI
Demo books API
(made with an online tutorial)

A REST API that stores books and authors in a PostgreSQL database on a docker container. In-memory database H2 is used for testing. The relationship between books and authors is many to one and the common REST methods are implemented.

**How to use**

Make sure to open Docker desktop

Run the following in terminal:
docker-compose up
.\mvnw clean install
.\mvnw spring-boot:run

The created tables can be seen at localhost:8888 with Adminer tool
Using Postman, write http://localhost:8080/v1/*some_route_from_controller* and supply body as JSON
