/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.service;

import com.ke.flooringmastery.dao.FlooringMasteryDao;
import com.ke.flooringmastery.dao.FlooringMasteryDaoException;
import com.ke.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.ke.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.load;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Owner
 */

public class FlooringMasteryServiceTest {
    
    FlooringMasteryServiceLayer service;
    FlooringMasteryDao dao;
     List<Order> orders; 
    
    public FlooringMasteryServiceTest() throws FlooringMasteryDaoException, FileNotFoundException {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    service = 
        ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
//        dao = new FlooringMasteryDaoFileImpl();
//        service = new FlooringMasteryServiceFileImpl(dao);
        
//        orders = dao.getAllOrders("09/12/1999", false);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUnitCosts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetUnitCostsFail() throws Exception { //THIS ONE
//        String type = "Laminate";
        Order order = new Order();
//        String type;
        order.setProductType("Laminate");
        BigDecimal [] unitCosts = service.getUnitCosts(order);
        
        assertNotEquals(new BigDecimal("5.00"), unitCosts[1]);
        ///////////////NULL POINTER
    }
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetUnitCostsPass() throws Exception { //THIS ONE
        Order order = new Order();
//        String type;
        order.setProductType("Laminate");
        BigDecimal [] unitCosts = service.getUnitCosts(order);
        
        assertNotEquals(new BigDecimal("5.00"), unitCosts[1]);
        //////////////NULL POINTER
        
    }

    /**
     * Test of Total method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testTotal() throws FlooringMasteryDaoException { //THIS ONE
        Order order = new Order();
        order.setCustomerName("Roland");
        order.setArea("400");
        order.setProductType("Wood");
        order.setState("PA");
        BigDecimal [] unitCosts = service.getUnitCosts(order);
//        BigDecimal tax = dao.getTax(orders.get(2));
        BigDecimal tax = service.getTax(order);
        BigDecimal area = new BigDecimal(order.getArea());
        
        assertNotEquals(new BigDecimal ("520.4"), service.Total(unitCosts, tax, area)[4]);
        
        //////////NULL POINTER
    }

    /**
     * Test of getAllOrderInfo method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetAllOrderInfo() throws Exception { //THIS ONE
        Order currentOrder = new Order();
        currentOrder.setCustomerName("Marsellus");
        currentOrder.setState("OH");
        currentOrder.setProductType("Wood");
        currentOrder.setArea("345");
        service.getAllOrderInfo(currentOrder);
        assertEquals(new BigDecimal("6.25"), currentOrder.getTaxRate());
    }


    
    
}
