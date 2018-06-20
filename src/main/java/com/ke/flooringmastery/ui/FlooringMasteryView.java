/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.ui;

import com.ke.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Owner
 */
public class FlooringMasteryView {
    private final UserIO io;
    
    public FlooringMasteryView(UserIO io){
        this.io = io;
    }
    
    public void displayMenu(){
        io.print("***");
        io.print("Flooring Program \n 1. Display Orders \n 2. Add an Order \n 3. Edit an Order \n 4. Remove an Order \n 5. Quit \n");
    }
    
    public int promptUserAppChoice(){
       return io.readInt("Select an item off the menu.", 1, 5);
    }
    
    public void displayAddOrderBanner(){
        io.print("== Add Order ==");
    }   
    
    public Order getNewOrderInfo(Set<String> states, Set<String> prodTypes){
        
        String orderCustomerName = io.readString("Please enter the customer's name: \n");
        displayStates(states);
        String orderCustomerState = io.readString("Please enter the customer's state identifier (2 letters): \n");
        displayProductTypes(prodTypes);
        String orderProductType = io.readString("Please enter the name of the product you're interested in. \n");
        //Consider not hardcoding the different types available.
        String orderSurfaceArea = io.readString("Please enter the area of the surface to cover. \n ");
        Order currentOrder = new Order();
        currentOrder.setCustomerName(orderCustomerName);
        currentOrder.setState(orderCustomerState);
        currentOrder.setProductType(orderProductType);
//        currentOrder.setArea(new BigDecimal (orderSurfaceArea));
        currentOrder.setArea(orderSurfaceArea);

        return currentOrder;
    }
    
    public String getOrderDate(){
        return io.readString("Please enter order's date (MM/dd/yyyy):");
    }
    
    public int getOrderIdEntry(){
        return io.readInt("Please enter your order's ID: ");
    }
    
    public Order updateOrderInfo(Order order, int orderId){
        
        String orderCustomerName = io.readString("Please enter the customer's name ("+order.getCustomerName()+"):");
        String orderCustomerState = io.readString("Please enter the customer's state identifier (2 letters) ("+order.getState()+"):");
        String orderProductType = io.readString("Please enter the name of the product you're interested in.("+order.getProductType()+"):  "
                + "\n (Our options are 'Carpet', 'Laminate', 'Tile' and 'Wood'.)");
        //Consider not hardcoding the different types available.
        String orderSurfaceArea = io.readString("Please enter the area of the surface to cover. ("+order.getArea()+"): \n");
        
        Order currentOrder = new Order();
        
        if(orderCustomerName.trim().length()!=0){
            currentOrder.setCustomerName(orderCustomerName);
        } else{
            currentOrder.setCustomerName(order.getCustomerName());
        }
        
        if(orderCustomerState.trim().length()!=0){
            currentOrder.setState(orderCustomerState);
        } else{
            currentOrder.setState(order.getState());
        }
        
        if(orderProductType.trim().length()!=0){
            currentOrder.setProductType(orderProductType);
        } else{
            currentOrder.setProductType(order.getProductType());
        }
        
        if(orderSurfaceArea.trim().length()!=0){
//            currentOrder.setArea(new BigDecimal(orderSurfaceArea));
            currentOrder.setArea(orderSurfaceArea);
        } else{
            currentOrder.setArea(order.getArea());
        }
        
        currentOrder.setOrderNumber(orderId);

        return currentOrder;
    }
    
    public void displayOrdersList(List<Order> ordersList){
        for (Order currentOrder: ordersList){
            io.print("\n"+String.valueOf(currentOrder.getOrderNumber())+"\n"
                    +"Customer Name: "+currentOrder.getCustomerName()+"\n"
                    +"State: "+currentOrder.getState()+"\n"
                    +"Product Type: "+currentOrder.getProductType()+"\n"
                    +"Area: "+currentOrder.getArea()+"\n"
                    +"Labor Cost psf: "+currentOrder.getLaborCostPsf()+"\n"
                    +"Product Cost psf: "+currentOrder.getProductCostPsf()+"\n"
                    +"Total Labor Cost: "+currentOrder.getLaborCostArea()+"\n"
                    +"Total Product Cost: "+currentOrder.getProductCostArea()+"\n"
                    +"Total Cost(Labor+Product): "+currentOrder.getTotalCost()+"\n"
                    +"Tax Rate: "+currentOrder.getTaxRate()+"\n"
                    +"Total Tax: "+currentOrder.getTotalTax()+"\n"
                    +"Total Cost(Tax included): "+currentOrder.getTotalCostAfterTax()+"\n");
        }
        
        io.readString("Please hit enter to continue. ");
    }
    
    private void displayProductTypes(Set<String> types){
        io.print("Before you proceed, please note that the types available to us are: "+types);
    }
    
    private void displayStates(Set<String> states){
        io.print("Please note that we only operate in these different states for the time being: \n"+states);
    }
    
    public void displaySingleOrderInfo(Order currentOrder){
        io.print(String.valueOf(currentOrder.getOrderNumber())+"|"
                    +currentOrder.getCustomerName()+"|"
                    +currentOrder.getState()+"|"
                    +currentOrder.getProductType()+"|"
                    +currentOrder.getArea()+"|"
                    +currentOrder.getLaborCostPsf()+"|"
                    +currentOrder.getProductCostPsf()+"|"
                    +currentOrder.getLaborCostArea()+"|"
                    +currentOrder.getProductCostArea()+"|"
                    +currentOrder.getTotalCost()+"|"
                    +currentOrder.getTaxRate()+"|"
                    +currentOrder.getTotalTax()+"|"
                    +currentOrder.getTotalCostAfterTax());
        
    }
    
    public void displayAllOrdersBanner(){
        io.print("== Display Orders ==");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command.");
    }
    
    public void displayEditOrderBanner(){
        io.print("== Edit Order ==");
    }
    
    public void displayRemoveOrderBanner(){
        io.print("== Remove Order ==");
    }
    
    public void displayExitMessage (){
        io.print("Thank you for trusting us with your flooring order.");
    }
    
    public String promptUserAddOrder(){
        return io.readString("Would you like to add this this order to your file? ");
    }
    
    public void orderFilingProcessCancelled(){
        io.print("You've cancelled the order placement.");
    }
    
    public String promptUserRemoveOrder(){
        return io.readString("Would you like to add this this order to your file? ");
    }
    
    public void orderRemovalCancelled(Order order){
        io.print("You've decided not to remove the Order ID: "+order.getOrderNumber()+" from your list.");
    }
    
    public void orderCreatedSuccess(){
        io.print("Thank you for filling an order with us. We look forward to collaborating with you.");
    }
            
    public void orderRemovedSuccess(){
        io.print("Your order has been removed. thank you for shopping with us.");
    }
    
    public void displayErrorMessage(String e){
        io.print("\nThere was an error in your input. please advise. -_- \n");
        io.print(e);
    }
    
    public String queryModeChoice(){
        return io.readString("\n Please type in 'T' to use the Training Mode. \n (The application is automatically set to Production Mode.) \n");
    }
    //Print out list of products.
}
