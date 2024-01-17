package pl.kurs.currencypersistanceservice.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.service.CurrencyService;

@Component
@Slf4j
@RequiredArgsConstructor
public class RateReceiver {
    private final CurrencyService service;
    private final ObjectMapper mapper;
    @RabbitListener(queues = "rate")
    public void fetchCurrencyRates(String rateJson) throws JsonProcessingException {// TODO: 17.01.2024 obsłużyć przychodzący obiekt CurrencyRateDto
        CurrencyRate currencyRate = mapper.readValue(rateJson, CurrencyRate.class);
        service.saveExchangeRate(currencyRate);
        log.info("processed: {}", currencyRate);
    }
}
