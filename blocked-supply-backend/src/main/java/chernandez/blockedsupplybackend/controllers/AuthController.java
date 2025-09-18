package chernandez.blockedsupplybackend.controllers;

import chernandez.blockedsupplybackend.domain.dto.auth.LoginRequest;
import chernandez.blockedsupplybackend.domain.dto.auth.RegisterRequest;
import chernandez.blockedsupplybackend.domain.dto.auth.TokenResponse;
import chernandez.blockedsupplybackend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user authentication operations.
 * <p>
 * This class provides endpoints for user registration, login, token refreshing,
 * and retrieving user information.
 * </p>
 */
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Registers a new user in the system.
     *
     * @param request The registration request containing user details.
     * @return A {@link ResponseEntity} with the result of the registration process.
     * @throws Exception if an error occurs during registration.
     */
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody final RegisterRequest request) throws Exception {
        return authService.register(request);
    }

    /**
     * Authenticates a user and returns a JWT.
     *
     * @param request The login request containing user credentials.
     * @return A {@link ResponseEntity} containing the authentication token.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> login(@RequestBody final LoginRequest request) {
        final TokenResponse token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    /**
     * Refreshes an authentication token.
     *
     * @param authHeader The Authorization header containing the refresh token.
     * @return A {@link TokenResponse} with the new authentication token.
     */
    @PostMapping("/auth/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader) {
        return authService.refreshToken(authHeader);
    }

    /**
     * Retrieves the authenticated user's information.
     *
     * @return A {@link ResponseEntity} with the user's details.
     * @throws Exception if an error occurs while fetching user information.
     */
    @GetMapping("/api/user")
    public ResponseEntity<?> getUser() throws Exception {
        return authService.getUser();
    }
}
