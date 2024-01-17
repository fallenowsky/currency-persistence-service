package pl.kurs.currencypersistanceservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
public class CurrencyRate {
    @Id
    private String currency;
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;
}
