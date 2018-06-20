/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.service;

import com.ke.flooringmastery.dao.FlooringMasteryDaoException;
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
public interface FlooringMasteryServiceLayer {

    BigDecimal getTax(Order order) throws FlooringMasteryDaoException;
    BigDecimal [] getUnitCosts(Order order) throws FlooringMasteryDaoException;
    BigDecimal [] Total (BigDecimal[] unitCosts, BigDecimal tax, BigDecimal area);
    public void getAllOrderInfo (Order order) throws FlooringMasteryDataValidationException, FlooringMasteryDaoException;
    public void createOrder(Order order) throws FlooringMasteryDaoException, IOException;
    List<Order> getAllOrders(String orderDate) throws FlooringMasteryDaoException, FileNotFoundException;
    Order getOrderToEdit(String orderDate, int orderId) throws FlooringMasteryDaoException, FileNotFoundException;
    public void updateOrder(Order order, String orderDate) throws FlooringMasteryDaoException, IOException;
    public Order removeOrder(String orderDate, int orderId) throws FlooringMasteryDaoException, IOException;
    Set<String> getProductList() throws FlooringMasteryDaoException ;
    Set<String> getStateList() throws FlooringMasteryDaoException ;
//    BigDecimal 
    
}
