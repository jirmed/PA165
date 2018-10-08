/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xmedved4
 */
public class ExchangeRateTableImplTest {

    private static Currency CZK = Currency.getInstance("CZK");
    private static Currency EUR = Currency.getInstance("EUR");
    private static Currency USD = Currency.getInstance("USD");

    public ExchangeRateTableImplTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getExchangeRate method, of class ExchangeRateTableImpl.
     */
    @Test
    public void testGetExchangeRate() throws Exception {
        System.out.println("getExchangeRate");
        Currency sourceCurrency = EUR;
        Currency targetCurrency = CZK;
        ExchangeRateTableImpl instance = new ExchangeRateTableImpl();
        BigDecimal expResult = BigDecimal.valueOf(27);
        BigDecimal result = instance.getExchangeRate(sourceCurrency, targetCurrency);
        assertEquals(expResult, result);
    }

}
