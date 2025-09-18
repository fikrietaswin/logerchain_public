package chernandez.blockedsupplybackend.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Controller for retrieving application profile information.
 * <p>
 * This class provides an endpoint to check the currently active Spring profiles.
 * </p>
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final Environment environment;

    public ProfileController(Environment environment) {
        this.environment = environment;
    }

    /**
     * Retrieves the list of active Spring profiles.
     *
     * @return A list of strings representing the active profiles.
     */
    @GetMapping()
    public List<String> getActiveProfiles() {
        return Arrays.asList(environment.getActiveProfiles());
    }
}

