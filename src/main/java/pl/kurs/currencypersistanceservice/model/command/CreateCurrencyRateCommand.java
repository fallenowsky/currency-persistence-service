package pl.kurs.currencypersistanceservice.model.command;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateCurrencyRateCommand {

    private String currency;

    private String code;

    private BigDecimal bid;

    private BigDecimal ask;
}
