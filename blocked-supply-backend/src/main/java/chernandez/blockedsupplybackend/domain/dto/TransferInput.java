package chernandez.blockedsupplybackend.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object (DTO) for representing transfer input.
 * <p>
 * This class is used to capture the data required to perform a shipment transfer.
 * </p>
 */
@Data
@NoArgsConstructor
public class TransferInput {
    private int shipmentId;
    private String newShipmentOwner;
    private int newState;
    private String location;
    private String transferNotes;
    private String from;

    /**
     * Constructs a new TransferInput.
     *
     * @param shipmentId       The ID of the shipment to transfer.
     * @param newShipmentOwner The new owner of the shipment.
     * @param newState         The new state of the shipment.
     * @param location         The current location of the shipment.
     * @param transferNotes    Notes about the transfer.
     */
    public TransferInput(int shipmentId, String newShipmentOwner, int newState, String location, String transferNotes) {
        this.shipmentId = shipmentId;
        this.newShipmentOwner = newShipmentOwner;
        this.newState = newState;
        this.location = location;
        this.transferNotes = transferNotes;
    }
}
