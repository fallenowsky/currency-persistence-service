package pl.kurs.currencypersistanceservice.common;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public interface TestContainers {

    String POSTGRES_IMAGE_NAME = "postgres:15-alpine";
    String RABBIT_IMAGE_NAME = "rabbitmq:3.9-management-alpine";

    @Container
    @ServiceConnection
    PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(POSTGRES_IMAGE_NAME);

    @Container
    @ServiceConnection
    RabbitMQContainer rabbitmq = new RabbitMQContainer(RABBIT_IMAGE_NAME);

}
