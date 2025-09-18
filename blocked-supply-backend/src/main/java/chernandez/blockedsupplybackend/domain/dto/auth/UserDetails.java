package chernandez.blockedsupplybackend.domain.dto.auth;

/**
 * A Data Transfer Object (DTO) for representing user details.
 *
 * @param name              The user's name.
 * @param email             The user's email.
 * @param blockchainAddress The user's blockchain address.
 */
public record UserDetails(
        String name,
        String email,
        String blockchainAddress
) {
}
