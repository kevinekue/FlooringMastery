/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.service;

/**
 *
 * @author Owner
 */
public class FlooringMasteryDataValidationException extends Exception {
    
    private String ex;

    public FlooringMasteryDataValidationException (String message) {
        super(message);
    }
    
    public  FlooringMasteryDataValidationException (String message, Throwable cause){
                super(message, cause);
    }
}
