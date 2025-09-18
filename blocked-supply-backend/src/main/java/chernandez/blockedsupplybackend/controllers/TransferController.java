package chernandez.blockedsupplybackend.controllers;

import chernandez.blockedsupplybackend.domain.dto.TransferInput;
import chernandez.blockedsupplybackend.services.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling shipment transfer operations.
 * <p>
 * This class provides endpoints for creating transfers and retrieving transfer history.
 * </p>
 */
@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    /**
     * Creates a new shipment transfer.
     *
     * @param transferInput The input data for creating the transfer.
     * @return A {@link ResponseEntity} with the result of the transfer operation.
     * @throws Exception if an error occurs during the transfer.
     */
    @PostMapping("/create")
    public ResponseEntity<?> transferShipment(@RequestBody TransferInput transferInput) throws Exception {
        return transferService.transferShipment(transferInput);
    }

    /**
     * Retrieves the transfer history for a specific shipment SKU.
     *
     * @param sku The SKU of the shipment to retrieve the transfer history for.
     * @return A {@link ResponseEntity} containing the transfer history.
     */
    @GetMapping("/{sku}")
    public ResponseEntity<?> getTransferHistory(@PathVariable String sku) {
        return transferService.getTransferHistory(sku);
    }

    /**
     * Retrieves the next available transfer ID.
     *
     * @return A {@link ResponseEntity} containing the next transfer ID.
     */
    @GetMapping("/nextId")
    public ResponseEntity<?> getNextTransferId() {
        return transferService.getNextTransferId();
    }


}
