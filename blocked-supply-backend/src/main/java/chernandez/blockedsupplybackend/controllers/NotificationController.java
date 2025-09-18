package chernandez.blockedsupplybackend.controllers;

import chernandez.blockedsupplybackend.domain.dto.NotificationOutput;
import chernandez.blockedsupplybackend.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling notifications.
 * <p>
 * This class provides endpoints for retrieving and managing user notifications.
 * </p>
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Retrieves all notifications for the authenticated user.
     *
     * @return A {@link ResponseEntity} containing a list of {@link NotificationOutput}.
     */
    @GetMapping()
    public ResponseEntity<List<NotificationOutput>> getNotifications() {
        return notificationService.getNotifications();
    }

    /**
     * Marks a specific notification as read.
     *
     * @param notiId The ID of the notification to mark as read.
     * @return A {@link ResponseEntity} with the result of the operation.
     */
    @PostMapping("/read/{notiId}")
    public ResponseEntity<?> readNotification(@PathVariable int notiId) {
        return notificationService.markAsRead(notiId);
    }

    /**
     * Marks all notifications for the authenticated user as read.
     *
     * @return A {@link ResponseEntity} with the result of the operation.
     */
    @PostMapping("/read")
    public ResponseEntity<?> readAllNotifications() {
        return notificationService.markAllAsRead();
    }

}
