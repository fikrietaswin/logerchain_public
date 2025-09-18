package chernandez.blockedsupplybackend.domain;

/**
 * Represents the roles that a user can have in the system.
 */
public enum Roles {
    /**
     * Administrator role with full access.
     */
    ADMIN,
    /**
     * Customer role with limited access.
     */
    CUSTOMER,
    /**
     * Participant role in a shipment.
     */
    PARTICIPANT,
    /**
     * Creator of a shipment.
     */
    CREATOR
}
