package pl.kurs.currencypersistanceservice.receiver;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.kurs.currencypersistanceservice.mapper.CurrencyRateMapper;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.service.CurrencyService;

@Component
@Slf4j
@RequiredArgsConstructor
public class RateReceiver {
    private final CurrencyService service;
    private final CurrencyRateMapper currencyRateMapper;

    @RabbitListener(queues = "rate")
    public void fetchCurrencyRates(CreateCurrencyRateCommand command) {
        CurrencyRate currencyRate = currencyRateMapper.toEntity(command);
        service.saveExchangeRate(currencyRate);
        log.info("processed: {}", currencyRate);
    }
}
