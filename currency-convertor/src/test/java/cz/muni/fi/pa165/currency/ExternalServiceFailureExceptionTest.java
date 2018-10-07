/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jirka
 */
public class ExternalServiceFailureExceptionTest {
    
    public ExternalServiceFailureExceptionTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test(expected = ExternalServiceFailureException.class)
    public void testConstructorWithMessage() throws ExternalServiceFailureException {
        throw new ExternalServiceFailureException("Exception message");
    }
    @Test(expected = ExternalServiceFailureException.class)
    public void testConstructorWithMessageAndCause() throws ExternalServiceFailureException {
        Exception cause = new Exception();
        throw new ExternalServiceFailureException("Exception message",cause);
    }

    
}
