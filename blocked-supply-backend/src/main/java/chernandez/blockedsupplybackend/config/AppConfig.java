package chernandez.blockedsupplybackend.config;

import chernandez.blockedsupplybackend.domain.User;
import chernandez.blockedsupplybackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Application configuration class.
 * <p>
 * This class provides configuration for Spring Security, including beans for
 * UserDetailsService, AuthenticationProvider, AuthenticationManager, and PasswordEncoder.
 * </p>
 */
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    /**
     * Provides a UserDetailsService bean that loads user-specific data.
     *
     * @return A {@link UserDetailsService} implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .build();
        };
    }

    /**
     * Provides an AuthenticationProvider bean that uses the UserDetailsService and PasswordEncoder.
     *
     * @return An {@link AuthenticationProvider} implementation.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Provides an AuthenticationManager bean.
     *
     * @param config The authentication configuration.
     * @return An {@link AuthenticationManager} implementation.
     * @throws Exception if an error occurs while getting the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Provides a PasswordEncoder bean that uses BCrypt hashing.
     *
     * @return A {@link PasswordEncoder} implementation.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
