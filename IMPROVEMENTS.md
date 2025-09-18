# Detailed Improvements and Feature Suggestions

This document provides a more detailed breakdown of the potential improvements and new features suggested in the `PROJECT_DETAILS.md` file.

## 1. Code Quality and Maintainability

### a. Refactoring Opportunities

#### i. Frontend: Custom Hooks for State Management

**Problem:** In the `blocked-supply-frontend`, components like `AuthPage`, `ShipmentsPage`, and `TransferShipmentPage` handle a lot of their own state and business logic. This makes the components large, difficult to read, and hard to test.

**Solution:** This logic can be extracted into custom hooks. For example, a `useAuth` hook could encapsulate all the logic related to user authentication (login, logout, registration).

**Example:**

In `AuthPage.tsx`, the `handleLogin` and `handleRegister` functions could be moved into a `useAuth` hook:

```typescript
// hooks/useAuth.ts
import {useState} from "react";
import {useRouter} from "next/navigation";
import api from "@/utils/baseApi";

export function useAuth() {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const router = useRouter();

    const login = async (loginData: LoginForm) => {
        setLoading(true);
        setError(null);
        // ... login logic
        setLoading(false);
    };

    const register = async (registerData: RegisterForm) => {
        setLoading(true);
        setError(null);
        // ... register logic
        setLoading(false);
    };

    return {loading, error, login, register};
}
```

#### ii. Backend: Service Layer Refactoring

**Problem:** The `AuthService` in the `blocked-supply-backend` is a large class that handles multiple responsibilities. This violates the Single Responsibility Principle and makes the class difficult to maintain and test.

**Solution:** The `AuthService` can be broken down into smaller, more focused services. For example:
-   `AuthenticationService`: Handles login and token generation.
-   `RegistrationService`: Handles new user registration and validation.
-   `UserService`: Handles user retrieval and management.

### b. Testing Strategy

**Problem:** The project currently has a severe lack of automated tests. This makes it difficult to verify that the application is working correctly and to prevent regressions when making changes.

**Solution:** A comprehensive testing strategy should be implemented, including:

-   **Unit Tests:** To test individual functions and components in isolation.
    -   **Backend:** Use JUnit and Mockito to test the services and controllers.
    -   **Frontend:** Use Jest and React Testing Library to test components and hooks.
    -   **Smart Contract:** Use Truffle's testing framework to write unit tests for the `ShipmentManagement` contract.
-   **Integration Tests:** To test the interaction between different components.
    -   **Backend/Broker:** Test the integration between the `blocked-supply-backend` and the `node-broker`.
    -   **Frontend/Backend:** Test the integration between the frontend and the backend API.
-   **End-to-End (E2E) Tests:** To test the application as a whole, from the user's perspective.
    -   Use a tool like Cypress or Playwright to write E2E tests for the main user flows.

## 2. Security

### a. Secret Management

**Problem:** Secrets like the JWT secret key and the database encryption key are currently stored in configuration files. This is a major security risk.

**Solution:** Use a dedicated secret management solution:
-   **Environment Variables:** Store secrets in environment variables, which are not checked into version control.
-   **Secret Management Tools:** For production environments, use a tool like HashiCorp Vault, AWS Secrets Manager, or Google Cloud Secret Manager.

### b. Smart Contract Security

**Problem:** The `ShipmentManagement` contract has some potential security vulnerabilities.

**Solution:**
-   **Zero Address Validation:** Add checks to ensure that addresses passed to functions (e.g., `newShipmentOwner`) are not the zero address.
-   **Ownership and Access Control:** Instead of relying on `msg.sender` for ownership checks, use a well-vetted library like OpenZeppelin's `Ownable` contract. This provides a more robust and secure way to manage ownership.

## 3. Performance

### a. Database Queries

**Problem:** The `AuthService` revokes all of a user's tokens every time a new token is generated. This is inefficient.

**Solution:** Only revoke the specific refresh token that is being used to generate a new access token. This will reduce the number of database writes and improve performance.

### b. Frontend Rendering

**Problem:** The frontend may have unnecessary re-renders, which can impact performance.

**Solution:** Use `React.memo` to wrap components that are expensive to render and that are not expected to change often. This will prevent them from re-rendering if their props have not changed.

## 4. New Features

### a. User Roles and Permissions

**User Story:** As an administrator, I want to be able to view all shipments in the system, so that I can have a complete overview of the supply chain.

**Implementation:**
-   Extend the `Roles` enum with more specific roles (e.g., `ADMIN`, `MANUFACTURER`, `CARRIER`, `RETAILER`).
-   Implement a role-based access control system in the backend using Spring Security's method-level security (`@PreAuthorize`).
-   The frontend would then show or hide certain UI elements based on the user's role.

### b. Shipment Deletion/Archiving

**User Story:** As a user, I want to be able to archive old or completed shipments, so that I can keep my list of active shipments clean and manageable.

**Implementation:**
-   Add a new `ARCHIVED` state to the `State` enum in the smart contract.
-   Add a function to the smart contract to allow the owner of a shipment to move it to the `ARCHIVED` state.
-   The frontend would then provide a button to archive a shipment and would filter out archived shipments from the main list by default.

### c. Real-time Updates with WebSockets

**User Story:** As a user, I want to receive real-time updates about my shipments, so that I am always aware of their current status and location.

**Implementation:**
-   Use WebSockets (e.g., with Socket.IO or Spring WebSockets) to push real-time updates from the backend to the frontend.
-   When a shipment is updated (e.g., transferred to a new owner), the backend would send a WebSocket message to all relevant users.
-   The frontend would then update the UI in real-time without the need for polling.
