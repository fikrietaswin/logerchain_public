package chernandez.blockedsupplybackend.controllers;

import chernandez.blockedsupplybackend.domain.dto.ShipmentInput;
import chernandez.blockedsupplybackend.services.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling shipment-related operations.
 * <p>
 * This class provides endpoints for creating and retrieving shipments.
 * </p>
 */
@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    /**
     * Creates a new shipment.
     *
     * @param shipmentInput The input data for creating the shipment.
     * @return A {@link ResponseEntity} with the result of the creation operation.
     * @throws Exception if an error occurs during shipment creation.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createShipment(@RequestBody ShipmentInput shipmentInput) throws Exception {
        return shipmentService.createShipment(shipmentInput);
    }

    /**
     * Retrieves a specific shipment by its ID.
     *
     * @param shipmentId The ID of the shipment to retrieve.
     * @return A {@link ResponseEntity} containing the shipment details.
     */
    @GetMapping("/{shipmentId}")
    public ResponseEntity<?> getShipment(@PathVariable int shipmentId) {
        return shipmentService.getShipment(shipmentId);
    }

    /**
     * Retrieves the next available shipment ID.
     *
     * @return A {@link ResponseEntity} containing the next shipment ID.
     */
    @GetMapping("/nextId")
    public ResponseEntity<?> getNextShipmentId() {
        return shipmentService.getNextShipmentId();
    }
}
