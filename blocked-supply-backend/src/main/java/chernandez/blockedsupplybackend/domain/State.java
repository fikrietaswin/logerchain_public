package chernandez.blockedsupplybackend.domain;

import java.math.BigInteger;

/**
 * Represents the state of a shipment.
 */
public enum State {
    /**
     * The shipment has been created.
     */
    CREATED,
    /**
     * The shipment is in transit.
     */
    IN_TRANSIT,
    /**
     * The shipment is stored in a warehouse.
     */
    STORED,
    /**
     * The shipment has been delivered.
     */
    DELIVERED;

    /**
     * Converts a BigInteger to a State enum.
     *
     * @param value The BigInteger value representing the state.
     * @return The corresponding State enum.
     * @throws IllegalArgumentException if the value is invalid.
     */
    public static State fromBigInt(BigInteger value) {
        return switch (value.intValue()) {
            case 0 -> CREATED;
            case 1 -> IN_TRANSIT;
            case 2 -> STORED;
            case 3 -> DELIVERED;
            default -> throw new IllegalArgumentException("Invalid state value: " + value);
        };
    }

    /**
     * Converts an integer to a State enum.
     *
     * @param value The integer value representing the state.
     * @return The corresponding State enum.
     * @throws IllegalArgumentException if the value is invalid.
     */
    public static State fromInt(int value) {
        return switch (value) {
            case 0 -> CREATED;
            case 1 -> IN_TRANSIT;
            case 2 -> STORED;
            case 3 -> DELIVERED;
            default -> throw new IllegalArgumentException("Invalid state value: " + value);
        };
    }
}
