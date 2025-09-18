package chernandez.blockedsupplybackend.domain.dto;

import java.time.LocalDateTime;

/**
 * A Data Transfer Object (DTO) for representing notification output.
 *
 * @param id        The ID of the notification.
 * @param message   The content of the notification.
 * @param createdAt The timestamp when the notification was created.
 * @param isRead    A boolean indicating whether the notification has been read.
 */
public record NotificationOutput(
        Long id,
        String message,
        LocalDateTime createdAt,
        boolean isRead
) {
}
