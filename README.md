# Spring Boot URL Shortener

A simple URL shortener service built with Spring Boot that converts long URLs into short links.

## Features

- Shorten long URLs
- Redirect short URLs to original links
- REST API endpoints
- Database storage

## Tech Stack

- Java 8+
- Spring Boot
- Spring Data JPA
- MySQL/H2 Database
- Maven

## Setup

1. **Clone the repository**
```bash
git clone https://github.com/MitulNakrani003/SpringBootURL-Shortener.git
cd SpringBootURL-Shortener
```

2. **Configure database** (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Run the application**
```bash
mvn spring-boot:run
```

Application runs on `http://localhost:8080`

## Author

**Mitul Nakrani** - [@MitulNakrani003](https://github.com/MitulNakrani003)
