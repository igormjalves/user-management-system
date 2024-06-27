# user-management-system

## System Architecture

The project is built using a layered architecture, separating concerns into different layers to maintain a clean and manageable codebase. The main components of the architecture are:

1. **Frontend**: Developed using Angular, a popular framework for building single-page applications. The UI components are designed using Angular Material to provide a consistent and responsive user experience.

2. **Backend**: Built with Spring Boot, a powerful framework for creating standalone, production-grade Spring-based applications. The backend handles business logic, data access, and serves RESTful APIs to the frontend.

3. **Database**: Uses H2, an in-memory database for development and testing purposes. It provides fast and lightweight database operations, ideal for local development.

## Technologies Used

- **Frontend**:
  - Angular
  - Angular Material
  - TypeScript
  - HTML
  - CSS

- **Backend**:
  - Spring Boot
  - Spring Data JPA
  - H2 Database

- **Testing**:
  - JUnit
  - Mockito
  - Spring Test

## Instructions to Configure and Run the Project Locally

### Prerequisites

- Node.js (v18.x or higher)
- Angular CLI (v17.x or higher)
- Java Development Kit (JDK) (v17 or higher)
- Maven (v3.6.0 or higher)

### Backend Configuration and Setup

1. **Clone the Repository**:
   ```
   git clone <repository-url>
   cd <repository-folder>
   ```
2. **Build the Backend**:
   ```
   mvn clean install
   ```
3. **Run the Backend**:
   ```
   mvnw spring-boot:run
   ```
The backend server will start at http://localhost:8080.

### Frontend Configuration and Setup

1. **Navigate to the frontend directory**:
   ```
   cd frontend
   ```
2. **Install dependencies**:
   ```
   npm install
   ```
3. **Run the Frontend**:
   ```
   ng serve
   ```
The frontend application will be available at `http://localhost:4200`.

### Running Tests

1. **Backend tests**:
  - Execute the tests using Maven:
   ```
   mvn test
   ```
