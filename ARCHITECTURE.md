```markdown
# ARCHITECTURE.md

## 1. System Design Overview

This project implements a **client-server architecture** designed to manage deposit interest. It consists of two primary applications: a frontend Single Page Application (SPA) and a backend API server. The project structure suggests a **monorepo** approach, where both applications reside within the same repository, facilitating coordinated development and deployment.

The frontend, built with Angular, provides a rich user interface for interacting with the system. It communicates with the backend via standard HTTP/REST API calls. The backend, a Java application (likely Spring Boot), handles business logic, data processing, and persistence, serving as the data provider for the frontend.

## 2. Component Descriptions

### 2.1. Frontend Application

*   **Type:** Single Page Application (SPA)
*   **Purpose:** Provides the user interface for interacting with the deposit interest management system. It handles user input, displays data fetched from the backend, and manages client-side routing and state.
*   **Technology Stack:**
    *   **Framework:** Angular 17.2.0
    *   **Language:** TypeScript 5.4.2
    *   **Runtime/Tooling:** Node.js (22.21.0, though `^18.13.0 || >=20.9.0` is required by Angular DevKit)
    *   **Package Manager:** npm 10.9.4
*   **Key Files/Configuration:**
    *   `frontend/src/main.ts`: The entry point for bootstrapping the Angular application.
    *   `frontend/src/index.html`: The main HTML file served to the browser.
    *   `frontend/angular.json`: Angular CLI workspace configuration, defining project settings, build options, and serve commands.
    *   `frontend/package.json`: Lists project dependencies (e.g., `@angular/core`, `@angular/router`, `rxjs`, `zone.js`) and development scripts.
    *   `frontend/tsconfig.json`, `frontend/tsconfig.app.json`: TypeScript compiler configurations.
*   **Build Output:** `dist/deposit-interest-ng17`

### 2.2. Backend Application

*   **Type:** Server-side application, likely a RESTful API.
*   **Purpose:** Manages the core business logic related to deposits and interest transactions. It exposes endpoints for the frontend to consume, handles data validation, and interacts with the persistence layer.
*   **Technology Stack:**
    *   **Language:** Java 1.8
    *   **Framework:** Inferred to be Spring Boot, based on the `bootRun` instruction in `README.md` and common Java backend patterns.
    *   **Build Tool:** Not explicitly provided, but typically Maven or Gradle for Java projects.
*   **Key Components/Example:**
    *   `backend/src/main/java/com/example/deposit/entity/InterestTransaction.java`: Defines a core data entity for managing interest transactions. This suggests a persistence layer (e.g., using an ORM like Hibernate) and a relational database.
    *   **`InterestTransaction` Entity Structure:**
        ```java
        public class InterestTransaction {
            private Long transactionId;
            private Long depositId;
            private BigDecimal interestAmount;
            private LocalDate transactionDate;
            // ... other fields, getters, setters
        }
        ```

### 2.3. Database

*   **Type:** Relational Database (Inferred).
*   **Purpose:** Provides persistent storage for all application data, including deposit details, interest calculations, and transaction records.
*   **Technology:** The specific database technology (e.g., PostgreSQL, MySQL, H2) is not specified in the provided context, but its relational nature is inferred from the presence of Java entities.

## 3. Data Flow Diagram

The following diagram illustrates the high-level data flow between the main components:

```mermaid
graph TD
    User[User] -->|1. Accesses Web Browser| Frontend[Frontend Application (Angular)]
    Frontend -->|2. Makes API Requests (HTTP/REST)| Backend[Backend Application (Java)]
    Backend -->|3. Interacts with| Database[(Relational Database)]
    Database -->|4. Returns Data| Backend
    Backend -->|5. Sends API Responses (JSON)| Frontend
    Frontend -->|6. Renders UI with Data| User
```

**Data Flow Steps:**

1.  **User Access:** A user accesses the web application through their browser, which loads the Frontend Application.
2.  **API Request:** The Frontend Application (Angular) makes HTTP/REST API requests to the Backend Application (Java) to fetch or send data.
3.  **Database Interaction:** The Backend Application processes the request, applies business logic, and interacts with the Relational Database to retrieve or persist data.
4.  **Data Retrieval:** The Database returns the requested data to the Backend Application.
5.  **API Response:** The Backend Application formats the data into a JSON response and sends it back to the Frontend Application.
6.  **UI Rendering:** The Frontend Application receives the data, updates its user interface accordingly, and displays the information to the User.

## 4. Technology Stack Analysis

### 4.1. Frontend Stack

*   **Angular 17.2.0:** A modern, component-based framework for building scalable Single Page Applications. It provides a structured approach to frontend development, including routing, state management, and form handling, promoting maintainability and developer efficiency.
*   **TypeScript 5.4.2:** A superset of JavaScript that adds static typing. This significantly enhances code quality, maintainability, and developer productivity by catching errors at compile-time, especially beneficial in large-scale applications.
*   **Node.js (22.21.0, though `^18.13.0 || >=20.9.0` is required by Angular DevKit):** The JavaScript runtime environment essential for running Angular CLI commands, building the application, and managing npm packages.
*   **npm 10.9.4:** The default package manager for Node.js, used to manage project dependencies and scripts for the frontend.

### 4.2. Backend Stack

*   **Java 1.8:** A widely adopted, robust, and platform-independent programming language, suitable for building high-performance server-side applications.
*   **Spring Boot (Inferred):** A popular framework for building production-ready, stand-alone Spring applications with minimal configuration. It simplifies the development of RESTful APIs and microservices.
*   **Build Tool (Maven/Gradle - Inferred):** Standard build automation tools for Java projects, used for dependency management, compilation, testing, and packaging the backend application.

### 4.3. Database

*   **Relational Database (Inferred):** A type of database that stores and provides access to data points that are related to one another. Its use is inferred from the presence of Java entities (e.g., `InterestTransaction.java`) which typically map to relational tables.

## 5. Architectural Decisions

*   **Client-Server Separation:**
    *   **Decision:** The project explicitly separates the frontend (Angular SPA) from the backend (Java API).
    *   **Benefit:** This clear division allows for independent development, deployment, and scaling of each component. It also enables different teams to work on specialized areas (UI/UX vs. business logic/data management) and allows for future flexibility, such as supporting multiple client applications (e.g., mobile apps) with the same backend.
*   **Single Page Application (SPA) with Angular:**
    *   **Decision:** The frontend is implemented as an SPA using Angular.
    *   **Benefit:** Provides a rich, dynamic, and responsive user experience by loading the application once and updating content dynamically without full page reloads. It leverages a robust, opinionated framework with a large ecosystem, promoting maintainability and scalability.
*   **Monorepo Structure:**
    *   **Decision:** Both frontend and backend applications reside within the same repository.
    *   **Benefit:** Often chosen for smaller to medium-sized projects or teams where tight coupling between frontend and backend development is beneficial. It simplifies dependency management, versioning, and ensures that changes across both applications can be coordinated and tested together more easily.

## 6. Codebase Structure

The project adopts a monorepo structure, organizing the frontend and backend applications into separate top-level directories:

```
.
├── backend/                        # Contains the Java backend application
│   ├── src/                        # Backend source code
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── deposit/
│   │   │   │               └── entity/
│   │   │   │                   └── InterestTransaction.java # Example Java entity
│   │   │   └── resources/          # (Implied) Configuration files, static assets for backend
│   │   └── test/                   # (Implied) Backend unit and integration tests
│   └── pom.xml                     # (Implied) Maven build file or build.gradle for Gradle
├── frontend/                       # Contains the Angular frontend application
│   ├── src/                        # Frontend source code
│   │   ├── main.ts                 # Entry point for bootstrapping Angular app
│   │   └── index.html              # Main HTML file
│   ├── angular.json                # Angular CLI workspace configuration
│   ├── package.json                # Frontend dependencies and scripts
│   ├── tsconfig.json               # TypeScript compiler configuration
│   └── tsconfig.app.json           # Application-specific TypeScript configuration
├── dist/                           # (Implied) Build output directory for frontend
│   └── deposit-interest-ng17/      # Frontend build artifacts
└── README.md                       # Project README file
```
```