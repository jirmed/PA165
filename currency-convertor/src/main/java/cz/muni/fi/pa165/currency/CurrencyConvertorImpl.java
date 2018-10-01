package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
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
            throw new IllegalArgumentException("Source currency may not be null");
        }
        BigDecimal rate = null;
        try {
            rate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
        } catch (ExternalServiceFailureException ex) {
            Logger.getLogger(CurrencyConvertorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        BigDecimal result;
        result = sourceAmount.multiply(rate);
        return result;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

}
