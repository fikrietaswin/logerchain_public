package chernandez.blockedsupplybackend.domain.dto;

import chernandez.blockedsupplybackend.domain.State;
import lombok.Data;

/**
 * A Data Transfer Object (DTO) for representing shipment output.
 * <p>
 * This class is used to send shipment data to clients.
 * </p>
 */
@Data
public class ShipmentOutput {
    private int id;
    private String sku;
    private String name;
    private String description;
    private String origin;
    private String destination;
    private String deliveryDate;
    private int units;
    private int weight;
    private State currentState;
    private String currentOwner;

    /**
     * Constructs a new ShipmentOutput.
     *
     * @param id           The ID of the shipment.
     * @param sku          The SKU of the shipment.
     * @param name         The name of the product.
     * @param description  The description of the shipment.
     * @param origin       The origin of the shipment.
     * @param destination  The destination of the shipment.
     * @param deliveryDate The expected delivery date.
     * @param units        The number of units in the shipment.
     * @param weight       The weight of the shipment.
     * @param currentState The current state of the shipment.
     * @param currentOwner The current owner of the shipment.
     */
    public ShipmentOutput(int id, String sku, String name, String description, String origin, String destination, String deliveryDate, int units, int weight, State currentState, String currentOwner) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.units = units;
        this.weight = weight;
        this.currentState = currentState;
        this.currentOwner = currentOwner;
    }
}
