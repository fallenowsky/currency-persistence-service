package pl.kurs.currencypersistanceservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;

public interface CurrencyRateRepository extends MongoRepository<CurrencyRate, Integer> {
}
