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
  - Postgres Database

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
The backend server will start at `http://localhost:8080`.

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

## Usage

### Accessing the Application

-  **Frontend**: Open a web browser and navigate to `http://localhost:4200`.
-  **Backend API**: The backend APIs can be accessed via `http://localhost:8080/api/`.

### Key Features

1. **User Management**:
- List Users
- Add User
- Update User
- Delete User

2. **Department Management**:
- Assign Department to Users
- Create New Departments

## Adding a New User

1. Click the "Adicionar" button in the user list toolbar.
2. Fill out the user details in the dialog that appears.
3. Select a department from the dropdown or add a new department by typing its name.
4. Submit the form to create a new user.

## Editing a User

1. Click the "Edit" icon next to the user in the user list.
2. Modify the user details in the dialog that appears.
3. Submit the form to update the user information.

## Deleting a User
1. Click the "Delete" icon next to the user in the user list.
2. Confirm the deletion in the confirmation dialog.

## Important Notes
In `backend/src/main/resources/application.properties` and `backend/src/test/resources/application.properties` define the variables **DATABASE_URL**, **DATABASE_USERNAME**, **DATABASE_PASSWORD**.
