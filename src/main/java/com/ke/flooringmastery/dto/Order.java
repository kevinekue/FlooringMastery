/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Owner
 */
public class Order {
//    OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost
//PerSquareFoot,MaterialCost,LaborCost,Tax,Total
//1,Wise,OH,6.25,Wood,100.00,5.15,4.75,515.00,475.00,61.88,1051.88

 int orderNumber; //automatically generate.
 String customerName;
 String state;
 
 String productType; //customer makes choice/ input choice. 
 BigDecimal productCostPsf; //get from doc
// BigDecimal area; //customer input 
 String area;
 BigDecimal laborCostPsf; // get from prod doc
 BigDecimal productCostArea; // cost for area => method
 BigDecimal laborCostArea; // use cost for area 
 BigDecimal taxRate; //get from tax doc
 BigDecimal totalTax; // method
 BigDecimal totalCost;    
 BigDecimal totalCostAfterTax; //  method

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.orderNumber;
        hash = 71 * hash + Objects.hashCode(this.customerName);
        hash = 71 * hash + Objects.hashCode(this.state);
        hash = 71 * hash + Objects.hashCode(this.productType);
        hash = 71 * hash + Objects.hashCode(this.productCostPsf);
        hash = 71 * hash + Objects.hashCode(this.area);
        hash = 71 * hash + Objects.hashCode(this.laborCostPsf);
        hash = 71 * hash + Objects.hashCode(this.productCostArea);
        hash = 71 * hash + Objects.hashCode(this.laborCostArea);
        hash = 71 * hash + Objects.hashCode(this.taxRate);
        hash = 71 * hash + Objects.hashCode(this.totalTax);
        hash = 71 * hash + Objects.hashCode(this.totalCost);
        hash = 71 * hash + Objects.hashCode(this.totalCostAfterTax);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.productCostPsf, other.productCostPsf)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPsf, other.laborCostPsf)) {
            return false;
        }
        if (!Objects.equals(this.productCostArea, other.productCostArea)) {
            return false;
        }
        if (!Objects.equals(this.laborCostArea, other.laborCostArea)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.totalCostAfterTax, other.totalCostAfterTax)) {
            return false;
        }
        return true;
    }

 
    public Order(int orderNumber){
        this.orderNumber = orderNumber;
    }
    
    public Order(){
        
    }
 
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductCostPsf() {
        return productCostPsf;
    }

    public void setProductCostPsf(BigDecimal productCostPsf) {
        this.productCostPsf = productCostPsf;
    }

//    public BigDecimal getArea() {
//        return area;
//    }
//
//    public void setArea(BigDecimal area) {
//        this.area = area;
//    } 
    
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public BigDecimal getLaborCostPsf() {
        return laborCostPsf;
    }

    public void setLaborCostPsf(BigDecimal laborCostPsf) {
        this.laborCostPsf = laborCostPsf;
    }

    public BigDecimal getProductCostArea() {
        return productCostArea;
    }

    public void setProductCostArea(BigDecimal productCostArea) {
        this.productCostArea = productCostArea;
    }

    public BigDecimal getLaborCostArea() {
        return laborCostArea;
    }

    public void setLaborCostArea(BigDecimal laborCostArea) {
        this.laborCostArea = laborCostArea;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCostAfterTax() {
        return totalCostAfterTax;
    }

    public void setTotal(BigDecimal totalCostAfterTax) {
        this.totalCostAfterTax = totalCostAfterTax;
    }
 
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

}
