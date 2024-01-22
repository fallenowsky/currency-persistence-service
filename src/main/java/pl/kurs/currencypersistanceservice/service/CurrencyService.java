package pl.kurs.currencypersistanceservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.repository.CurrencyRateRepository;

@RequiredArgsConstructor
@Service
public class CurrencyService {
    private final CurrencyRateRepository repository;

    @Transactional
    public void saveExchangeRate(CurrencyRate rate) {
        repository.save(rate);
    }
}
