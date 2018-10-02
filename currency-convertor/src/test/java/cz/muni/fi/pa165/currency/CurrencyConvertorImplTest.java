package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConvertorImplTest {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency CZK = Currency.getInstance("CZK");

    @Mock
    ExchangeRateTable exchangeRateTableMock; //= mock(ExchangeRateTable.class);

    @Test
    public void testConvert() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(USD, CZK)).thenReturn(BigDecimal.valueOf(22.123));
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        BigDecimal result = converter.convert(USD, CZK, BigDecimal.valueOf(10.15));
        assertThat(result, is(BigDecimal.valueOf(224.55)));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConvertWithNullSourceCurrency() {
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Source currency must not be null");
        converter.convert(null, CZK, BigDecimal.ONE);
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Target currency must not be null");
        converter.convert(USD, null, BigDecimal.ONE);
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Source amount must not be null");
        converter.convert(USD, CZK, null);
    }

    @Test
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(USD, CZK)).thenReturn(null);
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        thrown.expect(UnknownExchangeRateException.class);
        thrown.expectMessage("Unknown rate");
        converter.convert(USD, CZK, BigDecimal.ONE);
    }

    @Test
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(USD, CZK)).thenThrow(ExternalServiceFailureException.class);
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        thrown.expect(UnknownExchangeRateException.class);
        thrown.expectMessage("Getting Exchange Rate Table failed");
        converter.convert(USD, CZK, BigDecimal.ONE);
    }

}
