package chernandez.blockedsupplybackend.repositories;

import chernandez.blockedsupplybackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * <p>
 * This interface provides methods for querying users from the database.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user.
     * @return An optional containing the user if found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their blockchain address.
     *
     * @param blockchainAddress The blockchain address of the user.
     * @return An optional containing the user if found.
     */
    Optional<User> findByBlockchainAddress(String blockchainAddress);
}
