package pl.kurs.currencypersistanceservice.receiver;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.service.CurrencyService;

@Component
@RequiredArgsConstructor
public class RateReceiver {

    private final CurrencyService service;


    @RabbitListener(queues = "ratesQueue")
    public void fetchCurrencyRates(CreateCurrencyRateCommand command) {
        service.saveExchangeRate(command);
        System.out.println(command);
    }
}
