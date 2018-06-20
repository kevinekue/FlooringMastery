/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.dao;

/**
 *
 * @author Owner
 */
public class FlooringMasteryDaoException extends Exception {
    private String ex;

    public FlooringMasteryDaoException (String message) {
        super(message);
    }
    
    public  FlooringMasteryDaoException (String message, Throwable cause){
                super(message, cause);
    }

    FlooringMasteryDaoException(FlooringMasteryDaoException ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
