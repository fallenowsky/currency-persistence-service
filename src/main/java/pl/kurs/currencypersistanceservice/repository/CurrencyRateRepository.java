package pl.kurs.currencypersistanceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Integer> {
    CurrencyRate findByCode(String code);
}
