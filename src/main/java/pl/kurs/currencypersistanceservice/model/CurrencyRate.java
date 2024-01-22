package pl.kurs.currencypersistanceservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate {

    private String currency;

    @Id
    private String code;

    private BigDecimal bid;

    private BigDecimal ask;
}
