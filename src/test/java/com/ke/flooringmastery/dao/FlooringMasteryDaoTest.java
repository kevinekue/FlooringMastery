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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class FlooringMasteryDaoTest {

    public FlooringMasteryDao dao;
    
    public FlooringMasteryDaoTest() throws FlooringMasteryDaoException {
        this.dao = new FlooringMasteryDaoFileImpl();
        
//        this.dao = dao;
    }
    
/**
     *
     */
        @BeforeClass
    public static void setUpClass()  {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringMasteryDaoException, FileNotFoundException {
//         List<Order> orders = dao.getAllOrders("09/12/1999");
         
   
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddOrderFail() throws Exception {
        Order order = new Order ();
        String date = "09121999";
        String orderDate = "09/12/1999";
        List<Order> ordersList = dao.getAllOrders(orderDate);
        order.setCustomerName("Ross");
        order.setState("OHIO");
        order.setProductType("Wood");
        order.setArea("Y");
        order.setLaborCostPsf(new BigDecimal("34"));
        order.setProductCostPsf(new BigDecimal ("239543"));
        order.setTotalTax(new BigDecimal("4325"));
        order.setTotal(BigDecimal.ZERO);
        order.setTotalCost(BigDecimal.ZERO);
        order.setTaxRate(BigDecimal.ZERO);
//        currentOrder.setState(orderDate);
        order.setLaborCostArea(BigDecimal.ZERO);
        order.setProductCostArea(BigDecimal.ONE);
        assertNull(dao.addOrder(order/*,date */));
//         correct / Update test.
    }
    
    
    
    /**
     * Test of addOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddOrderPass() throws Exception {
        Order order = new Order ();
        String date = "09121999";
        String orderDate = "09/12/1999";
        List<Order> ordersList = dao.getAllOrders(orderDate);
        order.setCustomerName("Ross");
        order.setState("OH");
        order.setProductType("Wood");
        order.setArea("20");
        order.setOrderNumber(4);
//        order.setLaborCostPsf(BigDecimal.ZERO);
        order.setLaborCostPsf(new BigDecimal("34"));
        order.setProductCostPsf(new BigDecimal ("239543"));
        order.setTotalTax(new BigDecimal("4325"));
        order.setTotal(BigDecimal.ZERO);
        order.setTotalCost(BigDecimal.ZERO);
        order.setTaxRate(BigDecimal.ZERO);
//        currentOrder.setState(orderDate);
        order.setLaborCostArea(BigDecimal.ZERO);
        order.setProductCostArea(BigDecimal.ONE);
        
        dao.addOrder(order/*, date*/);
        assertNotNull(order);
        //Normal not to get actual values?
    }

    @Test
    //CHECK EXCEPTION HERE.
    public void testRemoveOrder() throws Exception {
        String orderDate = "09/12/1999";
        int orderId = 1;
//        order
        Order currentOrder = new Order();
        currentOrder.setCustomerName("Will");
        currentOrder.setProductType("Wood");
        currentOrder.setState("PA");
        currentOrder.setArea("45");
        currentOrder.setLaborCostPsf(new BigDecimal("34"));
        currentOrder.setProductCostPsf(new BigDecimal ("239543"));
        currentOrder.setTotalTax(new BigDecimal("4325"));
        currentOrder.setTotal(BigDecimal.ZERO);
        currentOrder.setTotalCost(BigDecimal.ZERO);
        currentOrder.setTaxRate(BigDecimal.ZERO);
//        currentOrder.setState(orderDate);
        currentOrder.setLaborCostArea(BigDecimal.ZERO);
        currentOrder.setProductCostArea(BigDecimal.ONE);
        dao.addOrder(currentOrder);
        
//        currentOrder
        Order removedOrder = dao.removeOrder(orderDate, orderId);
        assertEquals("Will", removedOrder.getCustomerName()); 
        // add order, verify it's been added, and then remove it. 
        //Worked, refill Order later.
    }
    /**
     * Test of editOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        String orderDate = "09/12/1999";
        List<Order> ordersList = dao.getAllOrders(orderDate);
        Order currentOrder = new Order();
        currentOrder = dao.getOrder(orderDate, 1);
//        order.setOrderNumber(2);
        currentOrder.setCustomerName("Will");
        currentOrder.setProductType("Laminate");
        currentOrder.setState("PA");
        currentOrder.setArea("45");
        currentOrder.setLaborCostPsf(new BigDecimal("34"));
        currentOrder.setProductCostPsf(new BigDecimal ("239543"));
        currentOrder.setTotalTax(new BigDecimal("4325"));
        currentOrder.setTotal(BigDecimal.ZERO);
        currentOrder.setTotalCost(BigDecimal.ZERO);
        currentOrder.setTaxRate(BigDecimal.ZERO);
//        currentOrder.setState(orderDate);
        currentOrder.setLaborCostArea(BigDecimal.ZERO);
        currentOrder.setProductCostArea(BigDecimal.ONE);
        dao.editOrder(currentOrder, orderDate);
        assertEquals(currentOrder, dao.getOrder(orderDate, 1));
        
    }

    /**
     * Test of getOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        String orderDate = "09/12/1999";
        int orderId = 2;
//        assertNotNull(orderDate, orderId); //DO LATER
        dao.getOrder(orderDate, 1);
//        assertequalas( test name)
    }

    /**
     * Test of getAllOrders method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetAllOrdersPass() throws Exception {
        String orderDate = "09/12/1999";
        List<Order> ordersList = dao.getAllOrders(orderDate);
        
        assertNotNull(ordersList);
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    

    /**
     * Test of getTax method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetTax() throws Exception {
//        String orderDate = "09/12/1999";
//        List<Order> ordersList = dao.getAllOrders(orderDate);
//        Order currentOrder = ordersList.get(0);
        Order currentOrder = new Order();
        currentOrder.setState("MI");
        BigDecimal tax = dao.getTax(currentOrder);
        assertEquals(new BigDecimal("5.75"), tax);
    }

    /**
     * Test of getProductAndLaborSpfCost method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetProductAndLaborSpfCost() throws Exception {
//        String orderDate = "09/12/1999";
//        List<Order> ordersList = dao.getAllOrders(orderDate);
        String productType = "Laminate";
//        dao.getProductAndLaborSpfCost(productType);
        assertNotNull(dao.getProductAndLaborSpfCost(productType));
    }

    /**
     * Test of productsList method, of class FlooringMasteryDao.
     */
    @Test
    public void testProductsList() throws Exception {
        assertEquals(4, dao.productsList().size());
    }

    /**
     * Test of statesList method, of class FlooringMasteryDao.
     */
    @Test
    public void testStatesList() throws Exception {
        assertEquals(4, dao.statesList().size());
    }

    
    
}
