package dev.andrewjfei;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {
    /**
     * Images used to generate the container.
     */
    private static final String POSTGRESQL_IMAGE = "postgres:latest";
    private static final String RABBITMQ_IMAGE = "rabbitmq:latest";

    @Container
    public static PostgreSQLContainer postgreSqlContainer = new PostgreSQLContainer(POSTGRESQL_IMAGE)
            .withDatabaseName("test")
            .withUsername("username")
            .withPassword("password");

    @Container
    public static RabbitMQContainer rabbitMqContainer = new RabbitMQContainer(RABBITMQ_IMAGE)
            .withExposedPorts(5672, 15672);

    /**
     * The {@code applicationProperties} method is used to set the application properties via the source code.
     * @param registry the application properties registry.
     */
    @DynamicPropertySource
    public static void applicationProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
        registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
    }
}
