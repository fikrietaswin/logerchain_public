package chernandez.blockedsupplybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Blocked Supply Backend application.
 * <p>
 * This class initializes and runs the Spring Boot application.
 * </p>
 */
@SpringBootApplication
public class BlockedSupplyBackendApplication {

    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(BlockedSupplyBackendApplication.class, args);
    }

}
