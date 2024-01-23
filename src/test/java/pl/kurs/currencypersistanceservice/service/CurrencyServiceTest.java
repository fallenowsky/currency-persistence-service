package pl.kurs.currencypersistanceservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.repository.CurrencyRateRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRateRepository currencyRateRepository;

    @InjectMocks
    private CurrencyService currencyService;

    private CurrencyRate sampleCurrencyRate;

    @Captor
    private ArgumentCaptor<CurrencyRate> currencyRateArgumentCaptor;

    @BeforeEach
    void setUp() {
        sampleCurrencyRate = CurrencyRate.builder()
                .currency("USD")
                .code("USD")
                .ask(BigDecimal.valueOf(3.75))
                .bid(BigDecimal.valueOf(3.80))
                .build();
    }

    @Test
    public void testSaveExchangeRate_MethodCall() {

    currencyService.saveExchangeRate(sampleCurrencyRate);

    verify(currencyRateRepository).save(currencyRateArgumentCaptor.capture());
    CurrencyRate saved = currencyRateArgumentCaptor.getValue();
    assertEquals(saved.getCurrency(),sampleCurrencyRate.getCurrency());
    assertEquals(saved.getCode(),sampleCurrencyRate.getCode());
    assertEquals(saved.getAsk(),sampleCurrencyRate.getAsk());
    assertEquals(saved.getBid(),sampleCurrencyRate.getBid());
    verify(currencyRateRepository).save(sampleCurrencyRate);
    verifyNoMoreInteractions(currencyRateRepository);
    }

}