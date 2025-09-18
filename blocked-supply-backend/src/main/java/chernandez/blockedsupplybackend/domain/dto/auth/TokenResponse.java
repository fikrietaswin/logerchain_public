package chernandez.blockedsupplybackend.domain.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Data Transfer Object (DTO) for representing a token response.
 *
 * @param accessToken  The access token.
 * @param refreshToken The refresh token.
 */
public record TokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken
) {
}
