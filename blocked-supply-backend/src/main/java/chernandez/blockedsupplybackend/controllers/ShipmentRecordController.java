package chernandez.blockedsupplybackend.controllers;

import chernandez.blockedsupplybackend.services.ShipmentRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller for handling shipment record-related operations.
 * <p>
 * This class provides endpoints for retrieving shipment records and statistics.
 * </p>
 */
@RestController
@RequestMapping("/api/records")
public class ShipmentRecordController {

    private final ShipmentRecordService shipmentRecordService;

    public ShipmentRecordController(ShipmentRecordService shipmentRecordService) {
        this.shipmentRecordService = shipmentRecordService;
    }

    /**
     * Retrieves the record for a specific shipment.
     *
     * @param shipmentId The ID of the shipment to retrieve the record for.
     * @return A {@link ResponseEntity} containing the shipment record.
     */
    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<?> getShipmentRecord(@PathVariable int shipmentId) {
        return shipmentRecordService.getShipmentRecord(shipmentId);
    }

    /**
     * Retrieves shipment statistics.
     *
     * @return A {@link ResponseEntity} containing a map of shipment statistics.
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getShipmentStatistics() {
        return shipmentRecordService.getShipmentStatistics();
    }

    /**
     * Retrieves shipment records where the authenticated user is a participant.
     *
     * @return A {@link ResponseEntity} containing a list of shipment records.
     */
    @GetMapping("/participant")
    public ResponseEntity<?> getShipmentRecordsByParticipant() {
        return shipmentRecordService.getShipmentRecordsByParticipant();
    }

    /**
     * Retrieves shipment records where the authenticated user is the owner.
     *
     * @return A {@link ResponseEntity} containing a list of shipment records.
     */
    @GetMapping("/owner")
    public ResponseEntity<?> getShipmentRecordsByowner() {
        return shipmentRecordService.getShipmentRecordsByOwner();
    }


}
