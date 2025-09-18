# Project Details: Blocked Supply Chain

## 1. Purpose and Vision

**Purpose:** To provide a transparent, secure, and efficient supply chain management system by leveraging blockchain technology.

**Vision:** To create a trustworthy and immutable record of a shipment's journey, from creation to delivery, that is accessible to all stakeholders in the supply chain. This will reduce fraud, improve efficiency, and increase trust among participants.

## 2. User Personas

### a. Shipment Creator (e.g., Manufacturer, Supplier)

-   **Needs:** To create new shipments, define their properties (e.g., product name, description, destination), and transfer them to the next participant in the supply chain.
-   **Goals:** To have a simple and secure way to introduce new products into the supply chain and to have a clear record of when and to whom they handed off the shipment.

### b. Shipment Participant (e.g., Carrier, Warehouse Manager)

-   **Needs:** To view the shipments they are currently responsible for, update the status and location of shipments, and transfer them to the next participant.
-   **Goals:** To have a clear and real-time view of the shipments they are handling and to be able to easily update the shipment's status as it moves through the supply chain.

### c. Shipment Receiver (e.g., Retailer, End Customer)

-   **Needs:** To track the status and location of shipments they are expecting and to have confidence in the authenticity and integrity of the products they receive.
-   **Goals:** To have a transparent and trustworthy way to verify the journey of a shipment and to be able to identify any potential issues or delays.

## 3. Features in Detail

### a. User Authentication

-   **Description:** Users can create an account and log in to the application. Authentication is handled using JWT (JSON Web Tokens).
-   **User Flow:**
    1.  A new user registers with their name, email, and password.
    2.  The backend validates the input, creates a new user in the database, and assigns them a unique blockchain address from a pool of available addresses.
    3.  The user can then log in with their email and password to receive a JWT.
    4.  This JWT is used to authenticate all subsequent requests to the backend.

### b. Shipment Creation

-   **Description:** Authenticated users can create new shipments. Each shipment is recorded as a new asset on the blockchain.
-   **User Flow:**
    1.  The user fills out a form with the shipment details (product name, description, origin, destination, delivery date, units, weight).
    2.  The frontend sends a request to the backend to create the shipment.
    3.  The backend communicates with the `node-broker`, which in turn calls the `createShipment` function on the `ShipmentManagement` smart contract.
    4.  A new shipment is created on the blockchain, and a corresponding record is saved in the backend's database.

### c. Shipment Tracking and Traceability

-   **Description:** Users can view the details and history of any shipment they are or have been a participant in.
-   **User Flow:**
    1.  The user can search for a shipment by its SKU.
    2.  The frontend requests the transfer history for that shipment from the backend.
    3.  The backend communicates with the `node-broker` to call the `getTransfers` function on the smart contract.
    4.  The complete history of the shipment, including all state changes, location updates, and ownership transfers, is displayed to the user.

### d. Shipment Transfer

-   **Description:** The current owner of a shipment can transfer it to another user.
-   **User Flow:**
    1.  The current owner selects a shipment they own and specifies the new owner's email address, the new state of the shipment, the current location, and any relevant notes.
    2.  The backend validates the request and communicates with the `node-broker` to call the `shipmentTransfer` function on the smart contract.
    3.  The smart contract updates the shipment's state and owner.
    4.  The new owner receives a notification about the transfer.

## 4. Technical Architecture and Data Flow

### a. Components

-   **`blocked-supply-frontend` (Next.js):** The user interface. It communicates with the `blocked-supply-backend` via a REST API.
-   **`blocked-supply-backend` (Spring Boot):** The core of the application. It handles user authentication, business logic, and data persistence. It communicates with the `node-broker` to interact with the blockchain.
-   **`node-broker` (Node.js/Express.js):** A lightweight service that acts as a bridge between the backend and the blockchain. It exposes a simple REST API that the backend can call, which then translates these calls into transactions or calls on the smart contract using Web3.js. This isolates the blockchain interaction logic from the main backend.
-   **`blocked-supply-truffle` (Solidity):** The Truffle project containing the `ShipmentManagement` smart contract. This contract is the single source of truth for all shipment data and is deployed on a local Ganache blockchain.

### b. Data Flow for Shipment Creation

1.  **Frontend:** The user submits the shipment creation form.
2.  **Backend:** The `/api/shipment/create` endpoint is called. The backend performs validation and then sends a request to the `node-broker`.
3.  **Node Broker:** The broker receives the request and calls the `createShipment` function on the `ShipmentManagement` smart contract using Web3.js.
4.  **Blockchain (Ganache):** The smart contract creates a new shipment and stores it on the blockchain.
5.  **Backend:** The backend saves a corresponding `ShipmentRecord` in its own database to cache some of the shipment data and to store additional information not present on the blockchain (like the internal user ID of the owner).

This architecture provides a clear separation of concerns, with each component having a well-defined responsibility.

## 5. Potential Improvements

### a. Code Quality and Maintainability

-   **Refactoring Opportunities:**
    -   In the `blocked-supply-frontend`, the `AuthPage`, `ShipmentsPage`, and `TransferShipmentPage` components have a lot of state management and business logic directly within the component. This could be refactored into custom hooks to make the components cleaner and more reusable.
    -   The `AuthService` in the backend is quite large and handles a lot of different responsibilities (registration, login, token refresh, user retrieval, input validation). This could be broken down into smaller, more focused services.
-   **Testing:**
    -   The project has very few tests. The backend has a single test that only checks if the application context loads. The frontend, smart contract, and node broker have no tests at all. A comprehensive test suite should be added to ensure the application is working as expected and to prevent regressions. This should include unit tests, integration tests, and end-to-end tests.
-   **Error Handling:**
    -   Error handling in the frontend is basic. It often just sets a generic error message. More specific and user-friendly error messages should be provided.
    -   The backend sometimes returns generic 500 errors when a more specific error code would be more appropriate.

### b. Security

-   **Secret Management:** The `encryptionKey` in the backend is currently read from the `application.properties` file. This is not a secure way to manage secrets. A more secure solution, such as using a secret management tool (e.g., HashiCorp Vault, AWS Secrets Manager) or environment variables, should be used. The same applies to the JWT secret key.
-   **Input Validation:** While there is some input validation in the backend, it could be more robust. For example, the `ShipmentInput` validation could be done using a dedicated validation library (e.g., Hibernate Validator) and annotations on the DTO itself.
-   **Smart Contract Security:** The `ShipmentManagement` smart contract is simple, but it could still benefit from a more thorough security audit. For example, it does not currently handle the case where a new owner is the zero address. It also relies on the `msg.sender` for ownership, which can be problematic if the contract is called from another contract. Using a library like OpenZeppelin's `Ownable` could improve security.

### c. Performance

-   **Database Queries:** The `AuthService` revokes all user tokens every time a new token is generated. This could be inefficient if a user has a large number of tokens. A more efficient approach would be to only revoke the specific token being refreshed.
-   **Frontend Rendering:** The frontend could benefit from some performance optimizations, such as using `React.memo` to prevent unnecessary re-renders of components.

### d. Features

-   **User Roles and Permissions:** The `Roles` enum suggests a role-based access control system, but it is not fully implemented. The application could be enhanced by adding more granular permissions for different roles (e.g., only an admin can view all shipments).
-   **Shipment Deletion/Archiving:** There is no way to delete or archive old shipments. This could be a useful feature for keeping the system clean and manageable.
-   **Real-time Updates:** The frontend currently polls for notifications every 60 seconds. A more efficient solution would be to use WebSockets to push real-time updates to the client.
-   **Advanced Search and Filtering:** The traceability page only allows searching by SKU. Adding more advanced search and filtering options (e.g., by date, status, owner) would make it more useful.
