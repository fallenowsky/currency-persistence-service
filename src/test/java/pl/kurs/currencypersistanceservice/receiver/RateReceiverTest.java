package pl.kurs.currencypersistanceservice.receiver;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.ActiveProfiles;
import pl.kurs.currencypersistanceservice.common.TestContainers;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.service.CurrencyService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(OutputCaptureExtension.class)
class RateReceiverTest implements TestContainers {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private CurrencyService service;

    private CreateCurrencyRateCommand rate;

    @Value("${app.queue-name}")
    private String queueName;


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
    public void testConnectionEstablishedRabbit() {
        assertTrue(rabbitmq.isCreated());
        assertTrue(rabbitmq.isRunning());
    }

    @Test
    public void testConnectionEstablishedPostgres() {
        assertTrue(postgres.isCreated());
        assertTrue(postgres.isRunning());
    }

    @Test
    public void testFetchCurrencyRates_HappyPath_ResultsInMockMethodsInvocations(CapturedOutput output) {
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(queueName, rate);
        }

        await()
                .atMost(Duration.of(101, ChronoUnit.MILLIS))
                .untilAsserted(() -> verify(service, times(5))
                        .saveExchangeRate(rate));

        assertTrue(output.getErr().isEmpty());
    }
}