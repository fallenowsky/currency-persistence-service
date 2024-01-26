package pl.kurs.currencypersistanceservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "rates")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias("currencyRate")
public class CurrencyRate {

    @Id
    private String code;

    private String currency;
    private BigDecimal bid;
    private BigDecimal ask;
}
