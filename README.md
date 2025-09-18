# Blocked Supply Chain

Blocked Supply is a comprehensive supply chain management application that leverages blockchain technology to provide transparency, traceability, and security for shipments. This project is a full-stack application with a backend, frontend, smart contract, and a node broker to interact with the blockchain.

## Architecture

The project is a monorepo containing four main components:

- **`blocked-supply-backend`**: A Spring Boot application that serves as the main backend for the application. It provides a REST API for the frontend to interact with, and it communicates with the `node-broker` to interact with the blockchain.
- **`blocked-supply-frontend`**: A Next.js application that provides the user interface for the application. Users can create and track shipments, view their history, and manage their account.
- **`blocked-supply-truffle`**: A Truffle project that contains the Solidity smart contract for managing shipments. The contract is deployed to a local Ganache blockchain.
- **`node-broker`**: A Node.js application that acts as a bridge between the backend and the blockchain. It exposes a REST API that the backend can use to interact with the smart contract.

## Features

- **User Authentication**: Users can register and log in to the application.
- **Shipment Creation**: Authenticated users can create new shipments with details such as product name, description, origin, destination, and delivery date.
- **Shipment Tracking**: Users can track the status and location of their shipments in real-time.
- **Shipment Transfer**: Users can transfer ownership of a shipment to another user.
- **Traceability**: The application provides a complete history of each shipment, including all transfers and state changes.
- **Notifications**: Users receive notifications for important events, such as when a shipment is transferred to them.
- **Blockchain Integration**: The application uses a Solidity smart contract to store and manage shipment data on a local Ganache blockchain.

## Getting Started

To get started with the project, you will need to have the following prerequisites installed:

- Java 21 or later
- Node.js 18 or later
- npm
- Truffle
- Ganache

### Setup

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/logerchain_public.git
    cd logerchain_public
    ```

2.  **Run the backend:**

    - Navigate to the `blocked-supply-backend` directory.
    - Run the application using Maven:
        ```bash
        ./mvnw spring-boot:run
        ```

3.  **Run the frontend:**

    - Navigate to the `blocked-supply-frontend` directory.
    - Install the dependencies:
        ```bash
        npm install
        ```
    - Run the application:
        ```bash
        npm run dev
        ```

4.  **Deploy the smart contract:**

    - Make sure you have Ganache running.
    - Navigate to the `blocked-supply-truffle` directory.
    - Compile and migrate the contracts:
        ```bash
        truffle migrate
        ```

5.  **Run the node broker:**

    - Navigate to the `node-broker` directory.
    - Install the dependencies:
        ```bash
        npm install
        ```
    - Create a `.env` file and add the following environment variables:
        ```
        BLOCKCHAIN_NODE_URL=http://localhost:7545
        CONTRACT_ADDRESS=<your-contract-address>
        ```
    - Run the application:
        ```bash
        npm start
        ```

## Usage

Once all the components are running, you can access the application at `http://localhost:3000`. You can register a new account, log in, and start creating and tracking shipments.

## Technologies Used

- **Backend**: Spring Boot, Java, Maven, Spring Security, JWT
- **Frontend**: Next.js, React, TypeScript, Tailwind CSS
- **Blockchain**: Solidity, Truffle, Ganache, Web3.js
- **Node Broker**: Node.js, Express.js
