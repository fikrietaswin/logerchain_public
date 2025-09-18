package chernandez.blockedsupplybackend.domain.dto.auth;

/**
 * A Data Transfer Object (DTO) for representing a registration request.
 *
 * @param email    The user's email.
 * @param password The user's password.
 * @param name     The user's name.
 */
public record RegisterRequest(
        String email,
        String password,
        String name
) {
}
