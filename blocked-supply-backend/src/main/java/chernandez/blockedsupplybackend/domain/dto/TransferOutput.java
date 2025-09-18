package chernandez.blockedsupplybackend.domain.dto;

import chernandez.blockedsupplybackend.domain.State;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) for representing transfer output.
 * <p>
 * This class is used to send transfer data to clients.
 * </p>
 */
@Data
public class TransferOutput {
    private int id;
    private int shipmentId;
    private int timestamp;
    private State newState;
    private String location;
    private String newOwner;
    private String transferNotes;

    /**
     * Constructs a new TransferOutput.
     *
     * @param id            The ID of the transfer.
     * @param shipmentId    The ID of the shipment.
     * @param timestamp     The timestamp of the transfer.
     * @param newState      The new state of the shipment.
     * @param location      The location of the transfer.
     * @param newOwner      The new owner of the shipment.
     * @param transferNotes Notes about the transfer.
     */
    public TransferOutput(int id, int shipmentId, int timestamp, State newState, String location, String newOwner, String transferNotes) {
        this.id = id;
        this.shipmentId = shipmentId;
        this.timestamp = timestamp;
        this.newState = newState;
        this.location = location;
        this.newOwner = newOwner;
        this.transferNotes = transferNotes;
    }
}
