package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    //private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        if (sourceCurrency == null) {
            throw new IllegalArgumentException("Source currency must not be null");
        }
        if (targetCurrency == null) {
            throw new IllegalArgumentException("Target currency must not be null");
        }

        if (sourceAmount == null) {
            throw new IllegalArgumentException("Source amount must not be null");
        }
        BigDecimal rate = null;
        try {
            rate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
        } catch (ExternalServiceFailureException ex) {
            Logger.getLogger(CurrencyConvertorImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnknownExchangeRateException("Getting Exchange Rate Table failed", ex);
        }
        if (rate == null) {
            throw new UnknownExchangeRateException("Unknown rate");
        }

        return sourceAmount.multiply(rate).setScale(2, RoundingMode.HALF_EVEN);
    }

}
