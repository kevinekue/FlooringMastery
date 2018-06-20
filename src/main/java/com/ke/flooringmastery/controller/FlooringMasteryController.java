/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.controller;

import com.ke.flooringmastery.dao.FlooringMasteryDaoException;
import com.ke.flooringmastery.dto.Order;
import com.ke.flooringmastery.service.FlooringMasteryDataValidationException;
import com.ke.flooringmastery.ui.FlooringMasteryView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import com.ke.flooringmastery.service.FlooringMasteryServiceLayer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class FlooringMasteryController {
    
    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;
    boolean choice;    
    
    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() /*throws FlooringMasteryDaoException, FileNotFoundException, IOException, FlooringMasteryDataValidationException*/ {
//        choice = getModeChoice();
//        view.displayMenu();
        try {
            boolean keepGoing = true;
            int userAppChoice = 0;
            
            while (keepGoing) {
                view.displayMenu();
                userAppChoice = view.promptUserAppChoice();
                
                switch (userAppChoice) {
                    case 1: {
                        try {
                            displayAllOrders();
                        } catch (FileNotFoundException ex) {
//                        throw new FileNotFoundException("Sorry, we couldn't locate the file corresponding to the order date entered");
                            view.displayErrorMessage("Sorry, we couldn't locate the file corresponding to the order date entered");
                        }
                    }
                    break;
                    case 2: {
                        try {
                            addOrderInfo();
                        } catch (FlooringMasteryDataValidationException ex) {
                            view.displayErrorMessage("Invalid Data");
                        } catch (IOException ex) {
                            view.displayErrorMessage("Please review the data entered.");
                        }
                    }
                    break;
                    case 3: {
                        try {
                            editOrder();
                        } catch (IOException ex) {
                            view.displayErrorMessage("Please review the data entered.");
                        } catch (FlooringMasteryDataValidationException ex) {
                            view.displayErrorMessage("Invalid Data");
                        }
                    }
                    break;
                    case 4: {
                        try {
                            removeOrder();
                        } catch (IOException ex) {
                            view.displayErrorMessage("Please review data entered");
                        }
                    }
                    break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (FlooringMasteryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }        
    }
    
    public void addOrderInfo() throws FlooringMasteryDaoException, FlooringMasteryDataValidationException, IOException, FileNotFoundException {
        view.displayAddOrderBanner();
        
        boolean hasErrors = false;
        do {
            Order currentOrder = view.getNewOrderInfo(service.getStateList(), service.getProductList());
            try {
                service.getAllOrderInfo(currentOrder);
                view.displaySingleOrderInfo(currentOrder);
                if (view.promptUserAddOrder().equalsIgnoreCase("y")) {
                    service.createOrder(currentOrder);
                    view.orderCreatedSuccess();
                    
                } else {
                    view.orderFilingProcessCancelled();
                }
                hasErrors = false;
            } catch (FlooringMasteryDataValidationException | FlooringMasteryDaoException | NumberFormatException e) {
                
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);
        
    }
    
    public void displayAllOrders() throws FlooringMasteryDaoException, FileNotFoundException {
        view.displayAllOrdersBanner();
        boolean hasErrors = false;
        do {
            String orderDate = view.getOrderDate();
            try {
                List<Order> ordersList = service.getAllOrders(orderDate);
                view.displayOrdersList(ordersList);
                hasErrors = false;
            } catch (FlooringMasteryDaoException | FileNotFoundException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
//                throw new FileNotFoundException(" We couldn't locate the requested file.");
                
            }
        } while (hasErrors);
    }
    
    public void editOrder() throws FlooringMasteryDaoException, FileNotFoundException, IOException, FlooringMasteryDataValidationException {
        view.displayEditOrderBanner();
        boolean hasErrors = false;
        do {
            String orderDate = view.getOrderDate();
            int orderId = view.getOrderIdEntry();
            try {
                Order currentOrder = service.getOrderToEdit(orderDate, orderId);
                view.displaySingleOrderInfo(currentOrder);
                Order updatedOrder = view.updateOrderInfo(currentOrder, orderId);
                service.getAllOrderInfo(updatedOrder);
                service.updateOrder(updatedOrder, orderDate);
//                hasErrors = false;

            } catch (FileNotFoundException | FlooringMasteryDaoException ex) {
                view.displayErrorMessage(ex.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);
    }
    
    public void removeOrder() throws FlooringMasteryDaoException, FileNotFoundException, IOException {
        view.displayRemoveOrderBanner();
        boolean hasErrors = false;
        do {
            String orderDate = view.getOrderDate();
            int orderId = view.getOrderIdEntry();
            try {
                Order orderToRemove = service.getOrderToEdit(orderDate, orderId);
                view.displaySingleOrderInfo(orderToRemove);
                if (view.promptUserRemoveOrder().equalsIgnoreCase("y")) {
                    service.removeOrder(orderDate, orderId);
                    view.orderRemovedSuccess();
                } else {
                    view.orderRemovalCancelled(orderToRemove);
                }
                hasErrors = false;
                
            } catch (FileNotFoundException | FlooringMasteryDaoException ex) {
                view.displayErrorMessage(ex.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);
        
    }
    
    public boolean getModeChoice() {
        String choice = view.queryModeChoice();
        if (choice.equalsIgnoreCase("T")) {
            return true;
        } else {
            return false;
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitMessage();
    }
}
