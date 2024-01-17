package pl.kurs.currencypersistanceservice.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.service.CurrencyService;

@Component
@RequiredArgsConstructor
public class RateReceiver {
    private final CurrencyService service;
    private final ObjectMapper mapper;
    @RabbitListener(queues = "rates")
    public void fetchCurrencyRates(String rateJson) throws JsonProcessingException {
        CurrencyRate currencyRate = mapper.readValue(rateJson, CurrencyRate.class);
        service.saveExchangeRate(currencyRate);
    }
}
