# 📝 Blogging App

A robust RESTful Blog API service built with **Spring Boot 4.0.1** and **PostgreSQL**, featuring comprehensive authentication and authorization mechanisms with OAuth 2.0 and JWT tokens.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 🌟 Features

### Core Functionality
- **CRUD Operations**: Complete blog post management (Create, Read, Update, Delete)
- **User Management**: User registration, authentication, and profile management
- **Visibility Controls**: Public and private blog post visibility settings
- **Blog Discovery**: Browse and search through public blogs

### Security & Authentication
- **OAuth 2.0 Integration**: Third-party authentication support
- **JWT-based Authorization**: Secure token-based authentication
- **Refresh Token Mechanism**: Seamless token renewal without re-authentication
- **Spring Security**: Enterprise-grade security implementation
- **Role-based Access Control**: Fine-grained permission management

### Technical Features
- **RESTful API Design**: Clean and intuitive API endpoints
- **Data Validation**: Input validation using Spring Validation
- **Exception Handling**: Centralized error handling with custom exceptions
- **DTO Pattern**: Clean separation between entities and API contracts
- **MapStruct Integration**: Efficient object mapping
- **Lombok**: Reduced boilerplate code

## 🏗️ Architecture

### Project Structure
```
src/main/java/com/K955/Blog_App/
├── Controller/       # REST API endpoints
├── Service/          # Business logic layer
├── Repository/       # Data access layer
├── Entity/           # JPA entities
├── Dto/              # Data Transfer Objects
├── Mapper/           # MapStruct mappers
├── Security/         # Security configuration & JWT utilities
├── Handlers/         # Exception handlers
└── Error/            # Custom exceptions
```

### Technology Stack

| Layer | Technology |
|-------|-----------|
| **Backend Framework** | Spring Boot 4.0.1 |
| **Language** | Java 21 |
| **Database** | PostgreSQL |
| **ORM** | Spring Data JPA |
| **Security** | Spring Security + OAuth 2.0 |
| **Authentication** | JWT (JSON Web Tokens) |
| **Object Mapping** | MapStruct 1.6.3 |
| **Build Tool** | Maven |
| **Code Simplification** | Lombok |

## 🚀 Getting Started

### Prerequisites
- **Java 21** or higher
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Git**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/krisharma955/Blogging-App.git
   cd Blogging-App
   ```

2. **Configure PostgreSQL Database**
   
   Create a new PostgreSQL database:
   ```sql
   CREATE DATABASE blogging_app;
   ```

3. **Configure Application Properties**
   
   Update `src/main/resources/application.yaml` with your database credentials:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/blogging_app
       username: your_username
       password: your_password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   
   jwt:
     secret: your-secret-key-here
     expiration: 86400000  # 24 hours
     refresh-expiration: 604800000  # 7 days
   ```

4. **Build the project**
   ```bash
   ./mvnw clean install
   ```

5. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "securePassword123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "securePassword123"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIs...",
  "tokenType": "Bearer",
  "expiresIn": 86400
}
```

#### Refresh Token
```http
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
}
```

### Blog Endpoints

#### Create Blog Post
```http
POST /api/blogs
Authorization: Bearer {accessToken}
Content-Type: application/json

{
  "title": "My First Blog Post",
  "content": "This is the content of my blog post...",
  "isPublic": true
}
```

#### Get All Public Blogs
```http
GET /api/blogs/public
```

#### Get User's Blogs
```http
GET /api/blogs/my-blogs
Authorization: Bearer {accessToken}
```

#### Get Blog by ID
```http
GET /api/blogs/{id}
Authorization: Bearer {accessToken}
```

#### Update Blog Post
```http
PUT /api/blogs/{id}
Authorization: Bearer {accessToken}
Content-Type: application/json

{
  "title": "Updated Title",
  "content": "Updated content...",
  "isPublic": false
}
```

#### Delete Blog Post
```http
DELETE /api/blogs/{id}
Authorization: Bearer {accessToken}
```

## 🔐 Security Features

### JWT Token Structure
- **Access Token**: Short-lived token (24 hours) for API authentication
- **Refresh Token**: Long-lived token (7 days) for obtaining new access tokens
- **Token Validation**: Automatic validation on each request
- **Secure Storage**: Tokens should be stored securely on the client side

### OAuth 2.0 Integration
The application supports OAuth 2.0 authentication with popular providers:
- Google
- (Configurable in application properties)

### Password Security
- Passwords are hashed using BCrypt
- Minimum password strength requirements enforced
- Secure password reset mechanism

## 🛠️ Development

### Running Tests
```bash
./mvnw test
```

### Building for Production
```bash
./mvnw clean package -DskipTests
java -jar target/Blog-App-0.0.1-SNAPSHOT.jar
```

### Code Quality
The project uses:
- **Lombok** for reducing boilerplate
- **MapStruct** for type-safe bean mapping
- **Spring Validation** for input validation
- **Custom Exception Handling** for consistent error responses

## 📦 Dependencies

### Core Dependencies
- `spring-boot-starter-data-jpa` - Database operations
- `spring-boot-starter-webmvc` - REST API
- `spring-boot-starter-security` - Security framework
- `spring-boot-starter-security-oauth2-client` - OAuth 2.0 support
- `postgresql` - PostgreSQL driver

### Security Dependencies
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` (v0.12.6) - JWT implementation

### Utility Dependencies
- `lombok` - Code generation
- `mapstruct` (v1.6.3) - Object mapping
- `spring-boot-starter-validation` - Input validation

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Krish Sharma**
- GitHub: [@krisharma955](https://github.com/krisharma955)
- Email: krishsh.codes@gmail.com

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community for the robust database
- All contributors who help improve this project

## 📞 Support

For support, email krishsh.codes@gmail.com or open an issue in the GitHub repository.

---

**Note**: This is a learning project demonstrating modern Spring Boot development practices with security best practices. Feel free to use it as a reference for your own projects!
