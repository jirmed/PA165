/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.ws.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 *
 * @author jirka
 */
@SoapFault(faultCode = FaultCode.SERVER, faultStringOrReason = "Product not found." )
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productName) {
        super("Product " + productName + " not found");
    }
}
