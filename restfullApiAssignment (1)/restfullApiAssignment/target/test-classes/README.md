
Celine Muhoracyeye     27381

## Spring Boot REST API Assignment 
 

This project (RESTful API assignment),Is where I built six different REST APIs from scratch. Each API demonstrates core REST concepts, including GET, POST, PUT, PATCH, and DELETE methods, with proper HTTP status codes and sample data for testing.

## The project includes:

Library Book Management API

Student Registration API

Restaurant Menu API

E-Commerce Product API

Task Management API

Bonus question: User Profile API

Each API can be tested individually using Postman, curl, or any other HTTP client.

## What I actually Learned

Working on this project helped me understand:

How REST APIs work in real-world scenarios

When to use GET, POST, PUT, PATCH, DELETE

Proper HTTP status codes (200, 201, 204, 404)

How to structure API responses consistently

Searching and filtering data via query/path parameters

Pagination for large datasets

Error handling and custom responses

Spring Boot annotations: @RestController, @RequestMapping, @PathVariable, @RequestParam, @RequestBody

# How to Navigate This

Setup & Prerequisites

Project Structure

Quick Start

API Overview

Endpoints & Sample Requests

Testing

Error Handling

Troubleshooting
## Project Structure

The project follows the MVC pattern:
src/main/java/auca/ac/rw/restfullApiAssignment/
├── RestfullApiAssignmentApplication.java   # Main Spring Boot app
├── controller/                             # REST controllers
│   ├── library/                            # Question 1
│   ├── studentRegistration/                # Question 2
│   ├── restaurant/                         # Question 3
│   ├── ecommerce/                          # Question 4
│   ├── taskmanagement/                     # Question 5
│   └── userprofile/                         # Bonus
└── model/                                  # Data models for each API
## API Overview
| API           | Purpose                      |
| ------------- | ---------------------------- |
| Books         | Manage library books         |
| Students      | Register and filter students |
| Menu          | Restaurant menu management   |
| Products      | E-Commerce product catalog   |
| Tasks         | Task/to-do management        |
| User Profiles | Manage users (bonus)         |

## Endpoints & Sample Requests
1. Library Book Management

Base URL: /api/books

Add/search/delete books
## Sample GET Response
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
## Student Registration

Base URL: /api/students

Filter by major or GPA, register new students
# Sample Response for GET /api/students/major/Com
[
  {
    "studentId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@university.edu",
    "major": "Computer Science",
    "gpa": 3.8
  }
]
## Restaurant Menu

Base URL: /api/menu

Browse items, search, toggle availability
## Sample Response for GET /api/menu/category/Main Course
[
  {
    "id": 3,
    "name": "Chicken Pizza",
    "description": "Cheesy pizza",
    "price": 6000.0,
    "category": "Main Course",
    "available": false
  }
]
## E-Commerce Product

Base URL: /api/products

Browse, filter by price/category/brand, check stock
## Task Management

Base URL: /api/tasks

Create tasks, update, mark complete, filter by status/priority
## Sample GET Response for status=false
[
  {
    "taskId": 1,
    "title": "Complete project documentation",
    "description": "Write comprehensive documentation",
    "completed": false,
    "priority": "HIGH",
    "dueDate": "2026-02-10"
  }
]
## Bonus question: User Profile API

Base URL: /api/userprofiles

CRUD operations, search, activate/deactivate users
## Sample Response:
{
  "success": true,
  "message": "User profile created successfully",
  "data": {
    "userId": 1,
    "username": "john_doe",
    "email": "john@example.com"
  }
}
## Testing

I tested APIs using Postman or curl.For example:
# Books API
curl  GET http://localhost:8080/api/books
curl  POST http://localhost:8080/api/books 

# Student API
curl  GET http://localhost:8080/api/students/major/Computer%20Science

# Menu API
curl  GET http://localhost:8080/api/menu/available?available=true

## Error I Met and Handled

404 Not Found: Resource doesn’t exist

400 Bad Request: Missing fields or invalid input

Connection refused: Server not running

## Features I Implemented

Full CRUD operations for all APIs

Search & filter support

Pagination for products

Toggle activation/availability

Custom response format for bonus API

Proper HTTP status codes

Sample data for testing


