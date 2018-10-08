/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author xmedved4
 */
public class MainXml {

    private static final Currency CZK = Currency.getInstance("CZK");
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final Currency USD = Currency.getInstance("USD");

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext("cz.muni.fi.pa165.currency");
        CurrencyConvertor currenycConvertor = context.getBean(CurrencyConvertor.class);
        BigDecimal result = currenycConvertor.convert(EUR,CZK, BigDecimal.ONE);
        System.out.println(result);
    }
}
