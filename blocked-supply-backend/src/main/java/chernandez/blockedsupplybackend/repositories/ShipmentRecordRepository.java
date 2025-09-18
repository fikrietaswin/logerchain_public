package chernandez.blockedsupplybackend.repositories;

import chernandez.blockedsupplybackend.domain.ShipmentRecord;
import chernandez.blockedsupplybackend.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link ShipmentRecord} entities.
 * <p>
 * This interface provides methods for querying shipment records from the database.
 * </p>
 */
public interface ShipmentRecordRepository extends JpaRepository<ShipmentRecord, Long> {

    /**
     * Finds all shipment records that are not in the given state.
     *
     * @param status The state to exclude.
     * @return A list of shipment records.
     */
    List<ShipmentRecord> findByStateNot(State status);

    /**
     * Finds all shipment records in a given state that were created between two timestamps.
     *
     * @param status The state to match.
     * @param start  The start of the time range.
     * @param end    The end of the time range.
     * @return A list of shipment records.
     */
    List<ShipmentRecord> findByStateAndCreatedAtBetween(State status, LocalDateTime start, LocalDateTime end);

    /**
     * Finds all shipment records where the given user is a participant.
     *
     * @param userId The ID of the user.
     * @return A list of shipment records.
     */
    List<ShipmentRecord> findByParticipantsContaining(Long userId);

    /**
     * Finds all shipment records owned by a specific user.
     *
     * @param ownerId The ID of the owner.
     * @return A list of shipment records.
     */
    List<ShipmentRecord> findByOwnerId(Long ownerId);

    /**
     * Finds a shipment record by its SKU.
     *
     * @param sku The SKU of the shipment.
     * @return An optional containing the shipment record if found.
     */
    Optional<ShipmentRecord> findBySku(String sku);
}
