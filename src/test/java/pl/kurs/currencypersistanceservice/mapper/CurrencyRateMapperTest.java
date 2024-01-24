package pl.kurs.currencypersistanceservice.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateMapperTest {

    private final CurrencyRateMapper currencyRateMapper = new CurrencyRateMapper();

    private CreateCurrencyRateCommand sampleCurrencyRate;

    @BeforeEach
    void setUp() {
        sampleCurrencyRate = CreateCurrencyRateCommand.builder()
                .currency("USD")
                .code("USD")
                .bid(BigDecimal.valueOf(3.75))
                .ask(BigDecimal.valueOf(3.80))
                .build();
    }

    @Test
    void testToDto_Success() {

        CurrencyRate result = currencyRateMapper.toEntity(sampleCurrencyRate);

        assertNotNull(result);
        assertEquals("USD", result.getCurrency());
        assertEquals("USD", result.getCode());
        assertEquals(BigDecimal.valueOf(3.75), result.getBid());
        assertEquals(BigDecimal.valueOf(3.80), result.getAsk());
    }

    @Test
    void testToDto_NullCurrencyRate_ThrowsNullPointerException() {

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> currencyRateMapper.toEntity(null));
    }

    @Test
    void testToDto_NullParameters_ResultsInNullParameters() {
        CreateCurrencyRateCommand rate = CreateCurrencyRateCommand.builder().build();

        CurrencyRate result = currencyRateMapper.toEntity(rate);

        assertNull(result.getCurrency());
        assertNull(result.getAsk());
        assertNull(result.getBid());
        assertNull(result.getCode());
    }

}