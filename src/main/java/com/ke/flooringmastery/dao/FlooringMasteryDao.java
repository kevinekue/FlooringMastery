/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.dao;

import com.ke.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Owner
 */
public interface FlooringMasteryDao {
    
//    String getTax (String state);
//    String [] getProductInfo (String productType);
//    String getOrderInfo(); // use getTax and getProductInfo to create the order's values
    
    Order addOrder (Order order /*, String newDate*/) throws FlooringMasteryDaoException , FileNotFoundException, IOException;
    
    Order editOrder(Order order, String orderDate) throws FlooringMasteryDaoException , FileNotFoundException, IOException;
    
    Order getOrder(String orderDate, int orderNumber) throws FlooringMasteryDaoException , FileNotFoundException;
    
    List<Order> getAllOrders(String orderDate) throws FlooringMasteryDaoException , FileNotFoundException;
    
    Order removeOrder (String orderDate, int orderNumber) throws FlooringMasteryDaoException , IOException;
    
//    List<String> getAllTaxes();
    
    BigDecimal getTax(Order order) throws FlooringMasteryDaoException;
    
    BigDecimal [] getProductAndLaborSpfCost(String productType) throws FlooringMasteryDaoException;
    
//    public void /*List<String>*/ productsList();
    public Set<String> productsList() throws FlooringMasteryDaoException;
    public Set<String> statesList() throws FlooringMasteryDaoException ;
}
