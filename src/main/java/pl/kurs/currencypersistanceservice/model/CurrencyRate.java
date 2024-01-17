package pl.kurs.currencypersistanceservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Entity
public class CurrencyRate {
    private String currency;
    @Id
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;
}
