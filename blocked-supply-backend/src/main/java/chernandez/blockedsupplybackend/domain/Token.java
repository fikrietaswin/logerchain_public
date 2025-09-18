package chernandez.blockedsupplybackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a JWT token entity.
 * <p>
 * This class is an entity that maps to the "tokens" table in the database.
 * It contains information about a JWT, such as the token string, its type,
 * whether it's revoked or expired, and the user it belongs to.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tokens")
public class Token {

    public boolean revoked;
    public boolean expired;
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String token;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TokenType tokenType = TokenType.BEARER;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Represents the type of a token.
     */
    public enum TokenType {
        /**
         * Bearer token type.
         */
        BEARER
    }

}
