package chernandez.blockedsupplybackend.domain.dto;

import lombok.Data;

/**
 * A Data Transfer Object (DTO) for representing shipment input.
 * <p>
 * This class is used to capture the data required to create a new shipment.
 * </p>
 */
@Data
public class ShipmentInput {
    private String productName;
    private String description;
    private String origin;
    private String destination;
    private String deliveryDate;
    private int units;
    private int weight;
    private String from;

    /**
     * Constructs a new ShipmentInput.
     *
     * @param productName  The name of the product.
     * @param description  The description of the shipment.
     * @param origin       The origin of the shipment.
     * @param destination  The destination of the shipment.
     * @param deliveryDate The expected delivery date.
     * @param units        The number of units in the shipment.
     * @param weight       The weight of the shipment.
     */
    public ShipmentInput(String productName, String description, String origin, String destination, String deliveryDate, int units, int weight) {
        this.productName = productName;
        this.description = description;
        this.origin = origin;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.units = units;
        this.weight = weight;
    }
}
