package chernandez.blockedsupplybackend.domain.dto.auth;

/**
 * A Data Transfer Object (DTO) for representing a login request.
 *
 * @param email    The user's email.
 * @param password The user's password.
 */
public record LoginRequest(
        String email,
        String password
) {
}
