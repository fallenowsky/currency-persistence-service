package pl.kurs.currencypersistanceservice.model.command;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateCurrencyRateCommand {

    private String currency;
    private String code;
    private BigDecimal bid;
    private BigDecimal ask;
}
