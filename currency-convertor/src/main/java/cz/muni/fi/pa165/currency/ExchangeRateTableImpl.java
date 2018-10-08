/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;

/**
 *
 * @author xmedved4
 */
public class ExchangeRateTableImpl implements ExchangeRateTable {

    private static Currency CZK = Currency.getInstance("CZK");
    private static Currency EUR = Currency.getInstance("EUR");
    private static Currency USD = Currency.getInstance("USD");

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if (sourceCurrency == EUR && targetCurrency == CZK) {
            return BigDecimal.valueOf(27);
        } else {
            return null;
        }
    }

}
