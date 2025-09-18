// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * @title ShipmentManagement
 * @dev A smart contract for managing shipments and their transfers on the blockchain.
 */
contract ShipmentManagement {
    /**
     * @dev Represents the possible states of a shipment.
     */
    enum State { CREATED, IN_TRANSIT, STORED, DELIVERED }

    /**
     * @dev Represents a single shipment.
     */
    struct Shipment {
        uint256 id;
        string name;
        string description;
        string origin;
        string destination;
        string deliveryDate;
        uint256 units;
        uint256 weight;
        State currentState;
        address currentOwner;
    }

    /**
     * @dev Represents a transfer of a shipment.
     */
    struct Transfer {
        uint256 id;
        uint256 shipmentId;
        uint256 timestamp;
        State newState;
        string location;
        address newShipmentOwner;
        string transferNotes;
    }

    uint256 private nextShipmentId = 1;
    uint256 private nextTransferId = 1;

    mapping(uint256 => Shipment) private shipments;
    mapping(uint256 => Transfer[]) private transfersByShipment;

    /**
     * @dev Modifier to ensure that the caller is the current owner of the shipment.
     * @param shipmentId The ID of the shipment to check.
     */
    modifier onlyOwner(uint256 shipmentId) {
        require(shipments[shipmentId].currentOwner == msg.sender, "Only the current owner can perform this action.");
        _;
    }

    /**
     * @dev Modifier to ensure that a shipment exists.
     * @param shipmentId The ID of the shipment to check.
     */
    modifier exists(uint256 shipmentId) {
        require(shipmentId > 0, "Shipment ID must be greater than 0.");
        require(shipmentId < nextShipmentId, "Shipment does not exist.");
        _;
    }

    /**
     * @dev Creates a new shipment.
     * @param productName The name of the product in the shipment.
     * @param description A description of the shipment.
     * @param origin The origin location of the shipment.
     * @param destination The destination location of the shipment.
     * @param deliveryDate The expected delivery date of the shipment.
     * @param units The number of units in the shipment.
     * @param weight The weight of the shipment.
     * @return newShipmentId The ID of the newly created shipment.
     * @return currentOwner The address of the initial owner of the shipment.
     * @return deliveryDate The expected delivery date of the shipment.
     */
    function createShipment(
        string memory productName,
        string memory description,
        string memory origin,
        string memory destination,
        string memory deliveryDate,
        uint256 units,
        uint256 weight
    ) public returns (uint256, address, string memory) {
        require(units > 0, "Units must be greater than 0.");
        require(weight > 0, "Weight must be greater than 0.");
        
        uint256 newShipmentId = nextShipmentId++;
        shipments[newShipmentId] = Shipment({
            id: newShipmentId,
            name: productName,
            description: description,
            origin: origin,
            destination: destination,
            deliveryDate: deliveryDate,
            units: units,
            weight: weight,
            currentState: State.CREATED,
            currentOwner: msg.sender
        });

        return (newShipmentId, msg.sender, deliveryDate);
    }

    /**
     * @dev Transfers a shipment to a new owner and updates its state.
     * @param shipmentId The ID of the shipment to transfer.
     * @param newShipmentOwner The address of the new owner.
     * @param newState The new state of the shipment.
     * @param location The current location of the shipment.
     * @param transferNotes Notes about the transfer.
     * @return shipmentId The ID of the transferred shipment.
     * @return newState The new state of the shipment.
     */
    function shipmentTransfer(
        uint256 shipmentId,
        address newShipmentOwner,
        State newState,
        string memory location,
        string memory transferNotes
    ) public onlyOwner(shipmentId) returns (uint256, State) {
        Shipment storage shipment = shipments[shipmentId];
        
        shipment.currentOwner = newShipmentOwner;
        shipment.currentState = newState;

        uint256 transferId = nextTransferId++;
        transfersByShipment[shipmentId].push(Transfer({
            id: transferId,
            shipmentId: shipmentId,
            timestamp: block.timestamp,
            newState: newState,
            location: location,
            newShipmentOwner: newShipmentOwner,
            transferNotes: transferNotes
        }));

        return (shipmentId, newState);
    }

    /**
     * @dev Retrieves the details of a shipment.
     * @param shipmentId The ID of the shipment to retrieve.
     * @return id The ID of the shipment.
     * @return name The name of the product in the shipment.
     * @return description A description of the shipment.
     * @return origin The origin location of the shipment.
     * @return destination The destination location of the shipment.
     * @return deliveryDate The expected delivery date of the shipment.
     * @return units The number of units in the shipment.
     * @return weight The weight of the shipment.
     * @return currentState The current state of the shipment.
     * @return currentOwner The address of the current owner of the shipment.
     */
    function getShipment(uint256 shipmentId) public view exists(shipmentId) 
        returns (
            uint256 id, 
            string memory name, 
            string memory description, 
            string memory origin, 
            string memory destination, 
            string memory deliveryDate, 
            uint256 units, 
            uint256 weight, 
            uint256 currentState, 
            address currentOwner
        ) 
    {    
        Shipment storage shipment = shipments[shipmentId];

        return (
            shipment.id,
            shipment.name,
            shipment.description,
            shipment.origin,
            shipment.destination,
            shipment.deliveryDate,
            shipment.units,
            shipment.weight,
            uint256(shipment.currentState),
            shipment.currentOwner
        );
    }

    /**
     * @dev Retrieves the transfer history of a shipment.
     * @param shipmentId The ID of the shipment.
     * @return A list of transfers for the shipment.
     */
    function getTransfers(uint256 shipmentId) public view exists(shipmentId) returns (Transfer[] memory) {
        return transfersByShipment[shipmentId];
    }

    /**
     * @dev Retrieves the next available shipment ID.
     * @return The next shipment ID.
     */
    function getNextShipmentId() public view returns (uint256) {
        return nextShipmentId;
    }

    /**
     * @dev Retrieves the next available transfer ID.
     * @return The next transfer ID.
     */
    function getNextTransferId() public view returns (uint256) {
        return nextTransferId;
    }
}
