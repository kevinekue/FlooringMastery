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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Owner
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao{
    
    Order onlyOrder;
    List<Order> ordersList = new ArrayList<>();
    String date = "09121999";
//    List<Order> orders = dao.getAllOrders("09/12/1999");
    FlooringMasteryDaoStubImpl(){
        onlyOrder = new Order(1);
        onlyOrder.setCustomerName("Rich");
        onlyOrder.setState("OH");
        onlyOrder.setArea("345");
        ordersList.add(onlyOrder);
    }

    @Override
    public Order addOrder(Order order /*, String newDate*/) throws FlooringMasteryDaoException, FileNotFoundException, IOException {
        if(order.getOrderNumber() == onlyOrder.getOrderNumber()){
            return order;
        } else{
            return null;
        }
    }

    @Override
    public Order editOrder(Order order, String orderDate) throws FlooringMasteryDaoException, FileNotFoundException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return order;
    }

    @Override
    public Order getOrder(String orderDate, int orderNumber) throws FlooringMasteryDaoException, FileNotFoundException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders(String orderDate) throws FlooringMasteryDaoException, FileNotFoundException {
        return ordersList;
    }

    @Override
    public Order removeOrder(String orderDate, int orderNumber) throws FlooringMasteryDaoException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getTax(Order order) throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal[] getProductAndLaborSpfCost(String productType) throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> productsList() throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> statesList() throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
