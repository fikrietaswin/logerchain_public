package chernandez.blockedsupplybackend.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a shipment record entity.
 * <p>
 * This class is an entity that maps to the "shipment_records" table in the database.
 * It contains information about a shipment, such as its ID, SKU, owner, dates,
 * state, and participants.
 * </p>
 */
@Data
@Entity
@Table(name = "shipment_records")
public class ShipmentRecord {

    @Id
    private Long shipmentId;

    @Column(unique = true)
    private String sku;

    private String ownerAddress;
    private long ownerId;

    private LocalDateTime createdAt;
    private LocalDateTime deliveryDate;
    private LocalDateTime deliveredAt;

    @Enumerated(EnumType.STRING)
    private State state;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "shipment_participants", joinColumns = @JoinColumn(name = "shipment_id"))
    @Column(name = "participant_id")
    private List<Long> participants = new ArrayList<>();

    public ShipmentRecord() {
    }

    /**
     * Constructs a new ShipmentRecord.
     *
     * @param shipmentId   The ID of the shipment.
     * @param ownerAddress The blockchain address of the owner.
     * @param deliveryDate The expected delivery date.
     * @param state        The initial state of the shipment.
     * @param owner        The ID of the owner.
     */
    public ShipmentRecord(Long shipmentId, String ownerAddress, LocalDateTime deliveryDate, State state, Long owner) {
        this.shipmentId = shipmentId;
        this.ownerAddress = ownerAddress;
        this.createdAt = LocalDateTime.now();
        this.deliveryDate = deliveryDate;
        this.deliveredAt = null;
        this.state = state;
        generateSku();
        addParticipant(owner);
    }

    private void generateSku() {
        this.sku = "SKU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    /**
     * Adds a participant to the shipment.
     *
     * @param participantId The ID of the participant to add.
     */
    public void addParticipant(Long participantId) {
        this.ownerId = participantId;
        if (this.participants == null) {
            this.participants = new ArrayList<>();
        }
        if (!this.participants.contains(participantId)) {
            this.participants.add(participantId);
        }
    }
}
