package chernandez.blockedsupplybackend.services;

import chernandez.blockedsupplybackend.domain.Notification;
import chernandez.blockedsupplybackend.domain.User;
import chernandez.blockedsupplybackend.domain.dto.NotificationOutput;
import chernandez.blockedsupplybackend.repositories.NotificationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service for handling notification-related operations.
 * <p>
 * This class provides methods for retrieving and managing user notifications.
 * </p>
 */
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AuthService authService;

    public NotificationService(NotificationRepository notificationRepository, AuthService authService) {
        this.notificationRepository = notificationRepository;
        this.authService = authService;
    }

    /**
     * Retrieves all unread notifications for the authenticated user.
     *
     * @return A {@link ResponseEntity} containing a list of {@link NotificationOutput}.
     */
    public ResponseEntity<List<NotificationOutput>> getNotifications() {
        User user = authService.getUserFromJWT();
        List<Notification> notifications = notificationRepository.findByToUserIdAndIsReadFalseOrderByCreatedAtDesc(user.getId());

        if (notifications.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        List<NotificationOutput> notificationOutputs = notifications.stream()
                .map(notification -> new NotificationOutput(
                        notification.getId(),
                        notification.getMessage(),
                        notification.getCreatedAt(),
                        notification.getIsRead()
                ))
                .toList();
        return new ResponseEntity<>(notificationOutputs, HttpStatus.OK);
    }

    /**
     * Marks a specific notification as read.
     *
     * @param notificationId The ID of the notification to mark as read.
     * @return A {@link ResponseEntity} with the result of the operation.
     */
    public ResponseEntity<?> markAsRead(int notificationId) {
        User user = authService.getUserFromJWT();
        Optional<Notification> notifOpt = notificationRepository.findById((long) notificationId);

        if (notifOpt.isPresent()) {
            Notification notif = notifOpt.get();
            if (!notif.getToUserId().equals(user.getId())) {
                return new ResponseEntity<>("The notification can only be marked as read by the owner", HttpStatus.FORBIDDEN);
            }
            notif.setIsRead(true);
            notificationRepository.save(notif);
            return new ResponseEntity<>("Notification " + notif.getId() + " read", HttpStatus.OK);
        }
        return new ResponseEntity<>("Notification does not exist.", HttpStatus.NOT_FOUND);
    }

    /**
     * Marks all unread notifications for the authenticated user as read.
     *
     * @return A {@link ResponseEntity} with the result of the operation.
     */
    public ResponseEntity<?> markAllAsRead() {
        User user = authService.getUserFromJWT();
        List<Notification> notifications = notificationRepository.findByToUserIdAndIsReadFalseOrderByCreatedAtDesc(user.getId());

        if (notifications.isEmpty()) {
            return new ResponseEntity<>("No unread notifications to mark as read.", HttpStatus.OK);
        }

        for (Notification notification : notifications) {
            notification.setIsRead(true);
            notificationRepository.save(notification);
        }

        return new ResponseEntity<>("All notifications marked as read.", HttpStatus.OK);
    }
}
