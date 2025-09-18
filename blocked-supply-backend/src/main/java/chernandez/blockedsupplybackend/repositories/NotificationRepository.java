package chernandez.blockedsupplybackend.repositories;

import chernandez.blockedsupplybackend.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for {@link Notification} entities.
 * <p>
 * This interface provides methods for querying notifications from the database.
 * </p>
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    /**
     * Finds all unread notifications for a specific user, ordered by creation date descending.
     *
     * @param toUserId The ID of the user.
     * @return A list of unread notifications.
     */
    List<Notification> findByToUserIdAndIsReadFalseOrderByCreatedAtDesc(Long toUserId);
}
