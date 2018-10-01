package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;

public class CurrencyConvertorImplTest {
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency CZK = Currency.getInstance("CZK");

    @Mock
    ExchangeRateTable exchangeRateTableMock = mock(ExchangeRateTable.class);

    @Test
    public void testConvertZeroAmount() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(any(Currency.class), any(Currency.class))).thenReturn(BigDecimal.TEN);
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);

        BigDecimal result = converter.convert(CZK, USD, BigDecimal.ZERO);
        assertThat("Shoud be zero", result, is(BigDecimal.valueOf(0)));
        // Don't forget to test border values and proper rounding.
        // fail("Test is not implemented yet.");
    }

    @Test
    public void testConvert() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(any(Currency.class), any(Currency.class))).thenReturn(BigDecimal.valueOf(20));
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        BigDecimal result = converter.convert(Currency.getInstance("USD"), Currency.getInstance("CZK"), BigDecimal.ONE);
        assertThat("Shoud be 20", result, is(BigDecimal.valueOf(20)));
        // Don't forget to test border values and proper rounding.
        // fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithNullSourceCurrency() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(any(Currency.class), any(Currency.class))).thenReturn(BigDecimal.valueOf(20));
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        try {
            BigDecimal result = converter.convert(null, Currency.getInstance("CZK"), BigDecimal.ONE);
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            assertThat("Should throw exception",e.getMessage(),is("Source currency may not be null"));
        }
    }

    @Test
    public void testConvertWithNullTargetCurrency() throws ExternalServiceFailureException {
        when(exchangeRateTableMock.getExchangeRate(any(Currency.class), any(Currency.class))).thenReturn(BigDecimal.valueOf(20));
        CurrencyConvertorImpl converter = new CurrencyConvertorImpl(exchangeRateTableMock);
        try {
            BigDecimal result = converter.convert(null, Currency.getInstance("CZK"), BigDecimal.ONE);
            fail("Should throw exception");
        } catch (IllegalArgumentException e) {
            assertThat("Should throw exception",e.getMessage(),is("Source currency may not be null"));
        }
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
        fail("Test is not implemented yet.");
    }

}
