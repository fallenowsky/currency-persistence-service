package pl.kurs.currencypersistanceservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.currencypersistanceservice.mapper.CurrencyRateMapper;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.repository.CurrencyRateRepository;

@RequiredArgsConstructor
@Service
public class CurrencyService {

    private final CurrencyRateRepository currencyRepository;
    private final CurrencyRateMapper currencyRateMapper;


    public void saveExchangeRate(CreateCurrencyRateCommand command) {
        currencyRepository.save(currencyRateMapper.toEntity(command));
    }
}
