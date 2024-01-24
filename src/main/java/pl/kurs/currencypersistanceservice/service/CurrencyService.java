package pl.kurs.currencypersistanceservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.currencypersistanceservice.mapper.CurrencyRateMapper;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.repository.CurrencyRateRepository;

@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final CurrencyRateRepository repository;
    private final CurrencyRateMapper currencyRateMapper;

    public void saveExchangeRate(CreateCurrencyRateCommand command) {
        CurrencyRate currencyRate = currencyRateMapper.toEntity(command);
        repository.save(currencyRate);
    }
}
