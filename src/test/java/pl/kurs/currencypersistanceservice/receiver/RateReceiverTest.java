package pl.kurs.currencypersistanceservice.receiver;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.service.CurrencyService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
class RateReceiverTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private CurrencyService service;

    private CreateCurrencyRateCommand rate;

    @Value("${app.queueName}")
    private String queueName;

    @Container
    @ServiceConnection
    private static final RabbitMQContainer rabbitmq = new RabbitMQContainer(
            DockerImageName.parse("rabbitmq:3.7.25-management-alpine"));


    @BeforeEach
    public void init() {
        rate = CreateCurrencyRateCommand.builder()
                .currency("EURO")
                .code("EUR")
                .ask(BigDecimal.valueOf(4.22))
                .bid(BigDecimal.valueOf(4.22))
                .build();
    }


    @Test
    public void testConnectionEstablished() {
        assertTrue(rabbitmq.isCreated());
        assertTrue(rabbitmq.isRunning());
    }

    @Test
    public void testFetchCurrencyRates_HappyPath_ResultsInMockMethodsInvocations() {
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(queueName, rate);
        }

        await()
                .atMost(Duration.of(200, ChronoUnit.MILLIS))
                .untilAsserted(() -> verify(service, times(5))
                        .saveExchangeRate(rate));
    }
}