/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.service;

import com.ke.flooringmastery.controller.FlooringMasteryController;
import com.ke.flooringmastery.dao.FlooringMasteryDaoException;
import com.ke.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.ke.flooringmastery.dto.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ke.flooringmastery.dao.FlooringMasteryDao;

/**
 *
 * @author Owner
 */
public class FlooringMasteryServiceFileImpl implements FlooringMasteryServiceLayer {

//    Order order;
    FlooringMasteryDao dao;

    public FlooringMasteryServiceFileImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    /**
     *
     * @param order
     * @return
     */
    @Override
    public BigDecimal[] getUnitCosts(Order order) throws FlooringMasteryDaoException {

        String productType = order.getProductType();
        if (productType == null) {
            throw new FlooringMasteryDaoException("Unrecognized product type.");
        } else {
        }
        return dao.getProductAndLaborSpfCost(productType);
    }

    @Override
    public BigDecimal[] Total(BigDecimal[] unitCosts, BigDecimal tax, BigDecimal area) {
        BigDecimal totalLaborCost = unitCosts[1].multiply(area).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalProdCost = unitCosts[0].multiply(area).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCost = totalLaborCost.add(totalProdCost).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalTax = totalCost.multiply(tax.divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCostAfterTax = totalTax.add(totalCost).setScale(2, RoundingMode.HALF_UP);

        BigDecimal[] arrayOfTotals = {totalLaborCost, totalProdCost, totalCost, totalTax, totalCostAfterTax};
        return arrayOfTotals;
    }

    @Override
    public void getAllOrderInfo(Order order) throws FlooringMasteryDataValidationException, FlooringMasteryDaoException {

        order.setTaxRate(getTax(order));
        order.setLaborCostPsf(getUnitCosts(order)[1]);
        order.setProductCostPsf(getUnitCosts(order)[0]);
//        String areaTest = order.getArea().trim();
        try {
            BigDecimal area = new BigDecimal(order.getArea().trim());
            BigDecimal[] totalCosts = Total(getUnitCosts(order), getTax(order), area);
            order.setLaborCostArea(totalCosts[0]);
            order.setProductCostArea(totalCosts[1]);
            order.setTotalCost(totalCosts[2]);
            order.setTotalTax(totalCosts[3]);
            order.setTotal(totalCosts[4]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("\n ERROR!!! Wrong format input. The Area entry needs to be an integer or a decimal value.  \n");
        }

    }

    @Override
    public void createOrder(Order order) throws FlooringMasteryDaoException, IOException {

        dao.addOrder(order);
    }

    @Override
    public BigDecimal getTax(Order order) throws FlooringMasteryDaoException {

        return dao.getTax(order);
    }

    @Override
    public List<Order> getAllOrders(String orderDate) throws FlooringMasteryDaoException, FileNotFoundException {

        return dao.getAllOrders(orderDate);
    }

    @Override
    public Order getOrderToEdit(String orderDate, int orderId) throws FlooringMasteryDaoException, FileNotFoundException {
        return dao.getOrder(orderDate, orderId);
    }

    @Override
    public void updateOrder(Order order, String orderDate) throws FlooringMasteryDaoException, IOException {
        dao.editOrder(order, orderDate);

    }

    @Override
    public Order removeOrder(String orderDate, int orderId) throws FlooringMasteryDaoException, IOException {
        return dao.removeOrder(orderDate, orderId);

    }

    @Override
    public Set<String> getProductList() throws FlooringMasteryDaoException {

        return dao.productsList();

    }

    @Override
    public Set<String> getStateList() throws FlooringMasteryDaoException {
        return dao.statesList();
    }
}
