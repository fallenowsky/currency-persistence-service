package pl.kurs.currencypersistanceservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kurs.currencypersistanceservice.mapper.CurrencyRateMapper;
import pl.kurs.currencypersistanceservice.model.CurrencyRate;
import pl.kurs.currencypersistanceservice.model.command.CreateCurrencyRateCommand;
import pl.kurs.currencypersistanceservice.repository.CurrencyRateRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {//todo testy @testCointeier

    @Mock
    private CurrencyRateRepository currencyRepository;

    private CurrencyService currencyService;

    private CreateCurrencyRateCommand command;

    @Captor
    private ArgumentCaptor<CurrencyRate> currencyRateArgumentCaptor;

    private CurrencyRate rate;

    @BeforeEach
    void setUp() {
        currencyService = new CurrencyService(currencyRepository, new CurrencyRateMapper());
        rate = CurrencyRate.builder()
                .currency("USD")
                .code("USD")
                .ask(BigDecimal.valueOf(3.75))
                .bid(BigDecimal.valueOf(3.80))
                .build();
        command = CreateCurrencyRateCommand.builder()
                .currency("USD")
                .code("USD")
                .ask(BigDecimal.valueOf(3.75))
                .bid(BigDecimal.valueOf(3.80))
                .build();
    }


    @Test
    public void testSaveExchangeRate_ResultsInCaptor() {
        when(currencyRepository.save(any(CurrencyRate.class))).thenReturn(rate);

        currencyService.saveExchangeRate(command);

        verify(currencyRepository).save(currencyRateArgumentCaptor.capture());
        CurrencyRate saved = currencyRateArgumentCaptor.getValue();
        assertEquals(saved, rate);
        verifyNoMoreInteractions(currencyRepository);
    }

    @Test
    public void testSaveExchangeRate_ResultsInMockInvocation() {
        when(currencyRepository.save(any(CurrencyRate.class))).thenReturn(rate);

        currencyService.saveExchangeRate(command);

        verify(currencyRepository, times(1)).save(rate);
        verifyNoMoreInteractions(currencyRepository);
    }

    @Test
    public void testSaveExchangeRate_CommandNull_ResultsInNullPointerException() {

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> currencyService.saveExchangeRate(null));
    }

}