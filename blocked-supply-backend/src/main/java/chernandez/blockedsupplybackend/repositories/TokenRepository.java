package chernandez.blockedsupplybackend.repositories;

import chernandez.blockedsupplybackend.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Token} entities.
 * <p>
 * This interface provides methods for querying tokens from the database.
 * </p>
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
    /**
     * Finds a token by its token string.
     *
     * @param token The token string.
     * @return An optional containing the token if found.
     */
    Optional<Token> findByToken(String token);

    /**
     * Finds all valid tokens for a specific user.
     *
     * @param id The ID of the user.
     * @return A list of valid tokens.
     */
    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long id);
}
