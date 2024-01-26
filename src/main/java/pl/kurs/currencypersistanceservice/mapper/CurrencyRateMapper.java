package pl.kurs.currencypersistanceservice.mapper;

import org.springframework.stereotype.Component;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;

@Component
public class CurrencyRateMapper {

    public CurrencyRate toEntity(CreateCurrencyRateCommand command) {
        return CurrencyRate.builder()
                .currency(command.getCurrency())
                .code(command.getCode())
                .bid(command.getBid())
                .ask(command.getAsk())
                .build();
    }
}
