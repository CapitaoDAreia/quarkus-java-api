# API CODE CHALLENGE

## General
- JAVA: 17
- FRAMEWORK: Quarkus
- DATABASE: MySQL
- ORM: Hibernate with Panache

## Requirements
- [x] Create account
- [x] Get account by customer document
- [x] Inactivate account **optional**
- [x] Create card
- [ ] List cards off an account **optional**
- [x] Activate card
- [ ] Block card **optional**
- [ ] Reissue card
- [x] Unit tests
- [ ] WebHooks **optional**

## How to run
- Clone the repository
- Navigate to the project directory
- Run the command `./mvnw quarkus:dev` (or use your IDE config)
- Access the URL `http://localhost:8080/swagger-ui/` to see the API documentation

## Additional information
- The file `application.properties` contains the database configuration
- The file `src/main/resources/import.sql` contains the SQL script to create the database and tables