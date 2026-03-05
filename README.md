# 📝 Blogzy - Multi-User Blogging Platform REST API

A feature-rich, secure multi-user blogging platform REST API built with Spring Boot 3.4.5. Blogzy enables users to create blogs, interact through comments and replies, and engage with content through likes. Perfect for building modern social blogging applications.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Testing with Postman](#testing-with-postman)
- [Project Structure](#project-structure)
- [Security](#security)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## ✨ Features

### 🔐 Authentication & Authorization
- **JWT-based Authentication** - Secure token-based authentication
- **Spring Security Integration** - Protected endpoints
- **Custom User Details Service** - Personalized authentication flow
- **Password Encryption** - BCrypt password hashing

### 👤 User Management
- User registration (Sign Up)
- User login with JWT token generation
- View user profiles
- Update user profile
- Delete user account
- Search users

### 📰 Feed/Blog Management
- **Create Blog Posts** - Share your thoughts and stories
- **View All Feeds** - Browse all blog posts
- **Rich Content Support** - Post text content
- **Author Attribution** - Track post creators

### 💬 Comments System
- **Comment on Posts** - Engage with blog content
- **View Comments** - See all comments on a post
- **Delete Comments** - Remove your own comments
- **Nested Discussions** - Support for threaded conversations

### 🔄 Replies System
- **Reply to Comments** - Continue the conversation
- **Delete Replies** - Remove your replies
- **Threaded Responses** - Organized comment threads

### ❤️ Engagement Features
- **Like Posts** - Show appreciation for content
- **View Total Likes** - See engagement metrics
- **View Liked Feeds** - Track posts you've liked
- **Like Comments** - Engage with comment discussions
- **Like Replies** - Support specific responses

### 🛡️ Security Features
- JWT token validation on all protected endpoints
- Secure authentication flow
- Custom exception handling
- Input validation
- SQL injection prevention

## 🛠️ Tech Stack

**Backend Framework:**
- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security
- Spring Validation

**Database:**
- PostgreSQL

**Security:**
- JWT (JSON Web Tokens) - io.jsonwebtoken 0.12.5
- BCrypt Password Encoder

**Tools & Libraries:**
- Lombok - Reduce boilerplate code
- MapStruct 1.5.5 - Object mapping
- Apache Commons Text 1.10.0
- Apache Commons Collections 4.4
- Maven - Dependency management

**Testing:**
- **Extensively tested with Postman**
- All endpoints verified and working

## 📦 Prerequisites

Before running this application, ensure you have:

- **Java Development Kit (JDK) 17** or higher
- **PostgreSQL 12** or higher
- **Maven 3.6+** (or use included Maven wrapper)
- **Postman** (for API testing)
- **Git** (for version control)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Krishal-Modi/Blogzy.git
cd Blogzy/Blogzy
```

### 2. Configure Database

Create a PostgreSQL database:

```sql
CREATE DATABASE Blogzy;
```

Update the database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Blogzy
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the Project

Using Maven wrapper (recommended):

```bash
# Windows
mvnw.cmd clean install

# Linux/Mac
./mvnw clean install
```

Or using Maven:

```bash
mvn clean install
```

### 4. Run the Application

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

The application will start on `http://localhost:8086`

## ⚙️ Configuration

### Application Properties

Key configurations in `application.properties`:

```properties
# Server Port
server.port=8086

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/Blogzy
spring.datasource.username=postgres
spring.datasource.password=root

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 📡 API Endpoints

### Authentication Endpoints

#### User Sign Up
```http
POST /users/signUp
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securePassword123",
  "bio": "Passionate blogger and tech enthusiast"
}
```

#### User Login
```http
POST /users/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "securePassword123"
}
```

**Response:** JWT Token (use in Authorization header)

---

### User Profile Endpoints

#### View User Profiles
```http
GET /users/viewProfiles?search=john
Authorization: Bearer {jwt_token}
```

#### Update User Profile
```http
PUT /users/updateProfile
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "name": "John Smith",
  "bio": "Updated bio information"
}
```

#### Delete User Account
```http
DELETE /users/deleteProfile
Authorization: Bearer {jwt_token}
```

---

### Feed/Blog Post Endpoints

#### Create Blog Post
```http
POST /feed/createContent
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "title": "My Amazing Blog Post",
  "content": "This is the content of my blog post...",
  "category": "Technology"
}
```

#### Get All Blog Posts
```http
GET /feed/getAll
Authorization: Bearer {jwt_token}
```

---

### Comment Endpoints

#### Comment on Post
```http
POST /feedComments/commentOnPost?feedId={feedId}
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "comment": "Great article! Very informative."
}
```

#### Get Comments on Post
```http
GET /feedComments/getComments?feedId={feedId}
Authorization: Bearer {jwt_token}
```

#### Delete Comment
```http
DELETE /feedComments/deleteComment?commentId={commentId}
Authorization: Bearer {jwt_token}
```

---

### Reply Endpoints

#### Reply to Comment
```http
POST /replies/replyToComment?feedCommentsId={commentId}
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "reply": "I completely agree with your point!"
}
```

#### Delete Reply
```http
DELETE /replies/deleteReply?replyId={replyId}
Authorization: Bearer {jwt_token}
```

---

### Like Endpoints

#### Like a Post
```http
POST /feedLikes/likeAPost?feedId={feedId}
Authorization: Bearer {jwt_token}
```

#### Get Total Likes on Post
```http
GET /feedLikes/totalLikes?feedId={feedId}
Authorization: Bearer {jwt_token}
```

#### Get All Liked Feeds
```http
GET /feedLikes/getLikedFeeds
Authorization: Bearer {jwt_token}
```

#### Like a Comment or Reply
```http
POST /likeComment/likeAMessage?feedCommentId={commentId}
Authorization: Bearer {jwt_token}

# OR for replies
POST /likeComment/likeAMessage?replyId={replyId}
Authorization: Bearer {jwt_token}
```

---

## 🧪 Testing with Postman

This API has been **extensively tested with Postman** to ensure all endpoints work flawlessly.

### Quick Start with Postman

1. **Download Postman** from [postman.com](https://www.postman.com/downloads/)

2. **Create a Collection**
   - Name it "Blogzy API"
   - Set base URL variable: `{{base_url}}` = `http://localhost:8086`

3. **Setup Environment Variables**
   - `base_url`: `http://localhost:8086`
   - `jwt_token`: (will be set after login)

### Complete Testing Workflow

#### Step 1: User Registration
```
POST {{base_url}}/users/signUp
Body: {
  "name": "Test User",
  "email": "test@example.com",
  "password": "Test123!",
  "bio": "Test user bio"
}
```

#### Step 2: User Login
```
POST {{base_url}}/users/login
Body: {
  "email": "test@example.com",
  "password": "Test123!"
}
```
**Action:** Copy the JWT token from response and save it

#### Step 3: Set Authorization Header
For all subsequent requests, add header:
```
Authorization: Bearer {your_jwt_token}
```

#### Step 4: Create a Blog Post
```
POST {{base_url}}/feed/createContent
Authorization: Bearer {{jwt_token}}
Body: {
  "title": "My First Blog",
  "content": "This is my first blog post on Blogzy!"
}
```
**Action:** Save the `feedId` from response

#### Step 5: View All Posts
```
GET {{base_url}}/feed/getAll
Authorization: Bearer {{jwt_token}}
```

#### Step 6: Comment on Post
```
POST {{base_url}}/feedComments/commentOnPost?feedId={feedId}
Authorization: Bearer {{jwt_token}}
Body: {
  "comment": "Great post!"
}
```
**Action:** Save the `commentId` from response

#### Step 7: Like the Post
```
POST {{base_url}}/feedLikes/likeAPost?feedId={feedId}
Authorization: Bearer {{jwt_token}}
```

#### Step 8: Reply to Comment
```
POST {{base_url}}/replies/replyToComment?feedCommentsId={commentId}
Authorization: Bearer {{jwt_token}}
Body: {
  "reply": "Thank you!"
}
```

#### Step 9: View Liked Feeds
```
GET {{base_url}}/feedLikes/getLikedFeeds
Authorization: Bearer {{jwt_token}}
```

#### Step 10: Check Comment Likes
```
GET {{base_url}}/feedLikes/totalLikes?feedId={feedId}
Authorization: Bearer {{jwt_token}}
```

### Postman Testing Tips

✅ **Environment Variables** - Use variables for reusable values  
✅ **Pre-request Scripts** - Auto-add authorization headers  
✅ **Tests Tab** - Add assertions to validate responses  
✅ **Collection Runner** - Run all tests sequentially  
✅ **Save Responses** - Create examples for documentation  
✅ **Organize** - Use folders to group related endpoints  

### Sample Test Scripts

```javascript
// Auto-save JWT token after login
pm.test("Login successful", function () {
    pm.response.to.have.status(200);
    var token = pm.response.text();
    pm.environment.set("jwt_token", token);
});

// Validate response structure
pm.test("Response has required fields", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property("feedId");
    pm.expect(jsonData).to.have.property("content");
});
```

## 📁 Project Structure

```
Blogzy/
├── src/
│   ├── main/
│   │   ├── java/com/example/Blogzy/
│   │   │   ├── BlogzyApplication.java               # Main application class
│   │   │   ├── config/                              # Configuration classes
│   │   │   │   └── SpringSecurity.java              # Security configuration
│   │   │   ├── controller/                          # REST controllers
│   │   │   │   ├── UsersController.java             # User management
│   │   │   │   ├── FeedController.java              # Blog post management
│   │   │   │   ├── CommentsController.java          # Comment operations
│   │   │   │   ├── RepliesController.java           # Reply operations
│   │   │   │   ├── LikesController.java             # Post likes
│   │   │   │   └── CommentLikeController.java       # Comment/reply likes
│   │   │   ├── entity/                              # JPA entities
│   │   │   │   ├── Users.java                       # User entity
│   │   │   │   ├── Feed.java                        # Blog post entity
│   │   │   │   ├── Comments.java                    # Comment entity
│   │   │   │   ├── Replies.java                     # Reply entity
│   │   │   │   ├── Likes.java                       # Post like entity
│   │   │   │   └── CommentLike.java                 # Comment like entity
│   │   │   ├── exceptions/                          # Custom exceptions
│   │   │   │   └── handler/
│   │   │   │       └── GlobalExceptionHandler.java
│   │   │   ├── filter/                              # Security filters
│   │   │   │   └── JwtFilter.java                   # JWT authentication
│   │   │   ├── mapper/                              # MapStruct mappers
│   │   │   │   ├── UsersMapper.java
│   │   │   │   ├── FeedMapper.java
│   │   │   │   └── CommentsMapper.java
│   │   │   ├── model/                               # DTOs
│   │   │   │   ├── UsersModel.java
│   │   │   │   ├── FeedModel.java
│   │   │   │   ├── ParentFeedModel.java
│   │   │   │   ├── CommentsRequestModel.java
│   │   │   │   ├── CommentsFeedModel.java
│   │   │   │   ├── ReplyRequestModel.java
│   │   │   │   ├── ReplyResponseModel.java
│   │   │   │   ├── LikesOnFeedModel.java
│   │   │   │   ├── LikesResponseModel.java
│   │   │   │   ├── LikedFeedModel.java
│   │   │   │   └── CommentLikeResponseModel.java
│   │   │   ├── repository/                          # JPA repositories
│   │   │   │   ├── UsersRepository.java
│   │   │   │   ├── FeedRepository.java
│   │   │   │   ├── CommentsRepository.java
│   │   │   │   ├── RepliesRepository.java
│   │   │   │   ├── LikesRepository.java
│   │   │   │   └── CommentLikeRepository.java
│   │   │   ├── service/                             # Business logic
│   │   │   │   ├── UsersService.java
│   │   │   │   ├── FeedService.java
│   │   │   │   ├── CommentsService.java
│   │   │   │   ├── RepliesService.java
│   │   │   │   ├── LikesService.java
│   │   │   │   ├── CommentLikeService.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   └── utils/                               # Utility classes
│   │   │       └── JwtUtil.java                     # JWT utilities
│   │   └── resources/
│   │       └── application.properties               # Configuration
│   └── test/                                        # Test files
├── mvnw                                             # Maven wrapper (Unix)
├── mvnw.cmd                                         # Maven wrapper (Windows)
└── pom.xml                                          # Maven dependencies
```

## 🔒 Security

### Authentication Flow

1. **Registration:** User signs up with email and password
2. **Login:** User sends credentials to `/users/login`
3. **Token Generation:** Server validates and returns JWT token
4. **Authenticated Requests:** Client includes token in `Authorization: Bearer {token}` header
5. **Token Validation:** `JwtFilter` validates token on each request

### Security Best Practices Implemented

✅ **JWT Authentication** - Stateless, secure token-based auth  
✅ **Password Hashing** - BCrypt encryption for passwords  
✅ **Protected Endpoints** - All user operations require authentication  
✅ **Input Validation** - Bean validation on all inputs  
✅ **SQL Injection Prevention** - JPA/Hibernate parameterized queries  
✅ **Exception Handling** - Secure, user-friendly error messages  
✅ **CORS Configuration** - Controlled cross-origin access  
✅ **Token Expiration** - Time-limited authentication tokens  

### Authorization Rules

- **Public Endpoints:** `/users/signUp`, `/users/login`
- **Protected Endpoints:** All other endpoints require valid JWT token
- **User-Specific Actions:** Users can only delete their own content

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Contribution Guidelines

- Follow Java coding conventions
- Write meaningful commit messages
- Add JavaDoc comments for public methods
- Update documentation for new features
- Test thoroughly with Postman before submitting PR

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📧 Contact

**Krishal Modi**

- GitHub: [@Krishal-Modi](https://github.com/Krishal-Modi)
- Project Link: [https://github.com/Krishal-Modi/Blogzy](https://github.com/Krishal-Modi/Blogzy)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community
- JWT.io for JWT implementation guidance
- Postman for comprehensive API testing tools
- Open source community

## 📈 Future Enhancements

- [ ] Add image upload for blog posts
- [ ] Implement pagination for feeds and comments
- [ ] Add follow/unfollow user functionality
- [ ] Create user feed (posts from followed users)
- [ ] Implement search functionality for posts
- [ ] Add tags/categories for posts
- [ ] Email notifications for comments and likes
- [ ] Rich text editor support
- [ ] User profile pictures
- [ ] Post bookmarking feature
- [ ] Draft posts functionality
- [ ] Comment editing
- [ ] Real-time notifications using WebSocket
- [ ] Analytics dashboard
- [ ] Content moderation tools
- [ ] API rate limiting

## 🌟 Key Features Summary

| Feature | Description | Status |
|---------|-------------|--------|
| User Authentication | JWT-based secure login | ✅ Completed |
| Blog Posts | Create and view posts | ✅ Completed |
| Comments | Comment on posts | ✅ Completed |
| Replies | Reply to comments | ✅ Completed |
| Post Likes | Like blog posts | ✅ Completed |
| Comment/Reply Likes | Like comments and replies | ✅ Completed |
| Profile Management | Update/Delete profiles | ✅ Completed |
| Search Users | Find other users | ✅ Completed |
| Security | Spring Security + JWT | ✅ Completed |
| Postman Tested | All endpoints verified | ✅ Completed |

---

<div align="center">
  <p>⭐️ Star this repo if you find it helpful! ⭐️</p>
  <p>Made with ❤️ by Krishal Modi</p>
  <p><strong>Happy Blogging! 📝</strong></p>
</div>
