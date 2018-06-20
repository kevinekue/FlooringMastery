/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.dao;

import com.ke.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import static java.util.Collections.max;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    private Map<String, String> taxes = new HashMap<>();
    private Map<String, BigDecimal[]> products = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();
    private static final String TAXES_FILE = "Taxes.txt";
    private static final String PRODUCTS_INFO = "Products.txt";
    public static final String DELIMITER = ",";
    boolean choice;
    String testDatea = "09121999";
    String testDateb = "09/12/1999";
    
    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    String dateFormatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
    String dateOtherFormat = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    //FIND A WAY TO WRITE NULL FOR DELETED ORDERS

    public FlooringMasteryDaoFileImpl() throws FlooringMasteryDaoException {
        this.choice = loadMode();
    }

    //Insert a training or prod boolean. 
//    public boolean modeChoice(boolean choice) {
//        choice = true;
//        return choice;
//    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryDaoException, FileNotFoundException, IOException {
        int maximum;
        
        try {
            if (choice == true) {
                dateOtherFormat = "06012013";
            }
            if (!testDateb.isEmpty()){
            loadOrders(dateOtherFormat); //if training, dateOtherFormat
            }
            else if (testDateb.length()==10){
                loadOrders(testDateb);
            }
            else{
            loadOrders(dateOtherFormat);
            }
        } catch (FlooringMasteryDaoException e) {
//            throw new FlooringMasteryDaoException("Could probably not format the date.");
        }
        Set<Integer> KeySet = orders.keySet();
        try {

            if (KeySet.isEmpty()) {
                order.setOrderNumber(1);
                order = orders.put(order.getOrderNumber(), order);

            } else {
                maximum = max(KeySet);
                order.setOrderNumber(maximum + 1);
                order = orders.put(order.getOrderNumber(), order);

//            writeOrders(dateFormatted);     
            }
            if (choice == false && testDatea.isEmpty()) {
                writeOrders(dateFormatted);
            }
            else if(testDatea.length()==8){
                writeOrders(testDatea);
            }
        } catch (FlooringMasteryDaoException e) {
            throw new FlooringMasteryDaoException("Order number maybe?");
        }

//        order = orders.put(order.getOrderNumber(), order);
//        writeOrders();
        return order;
    }

    @Override
    public Order editOrder(Order order, String orderDate) throws FlooringMasteryDaoException, FileNotFoundException, IOException {

        loadOrders(orderDate);
        order = orders.put(order.getOrderNumber(), order);
        dateFormatted = formatDate(orderDate);
        
        
        if(choice == false){
        writeOrders(dateFormatted);}
        return order;
    }

    @Override
    public Order getOrder(String orderDate, int orderId) throws FlooringMasteryDaoException, FileNotFoundException {
        getAllOrders(orderDate);
        Order currentOrder = orders.get(orderId);
        if (currentOrder == null) {
            throw new FileNotFoundException(" \n We couldn't find this orderId: " + orderId + " from the following date: " + orderDate + ". Please re-enter your order information.");
        } else {
            return currentOrder;
        }

    }

    @Override
    public List<Order> getAllOrders(String orderDate) throws FlooringMasteryDaoException, FileNotFoundException {
        orders.clear();
//        if(choice == true)
        if (choice == true) {
                orderDate = "06012013";
            }
//        else{
        loadOrders(orderDate);
//        }
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public Order removeOrder(String orderDate, int orderId) throws FlooringMasteryDaoException, IOException {
        getAllOrders(orderDate);
        Order removedOrder = orders.remove(orderId);
        dateFormatted = formatDate(orderDate);
        if(choice == false){
        writeOrders(dateFormatted);
        }
        return removedOrder;
    }

    @Override
    public BigDecimal getTax(Order order) throws FlooringMasteryDaoException {

        loadTaxes();
        String state = order.getState();
        String tax = taxes.get(state);
        BigDecimal taxU;
        if (tax == null) {
            throw new FlooringMasteryDaoException("\n ERROR: Wrong State input. \n");
        } else {
            taxU = new BigDecimal(tax);
        }
        return taxU;
    }

    @Override
    public BigDecimal[] getProductAndLaborSpfCost(String productType) throws FlooringMasteryDaoException {
        loadProducts();
        BigDecimal[] unitCostArrays = products.get(productType);
        if (unitCostArrays == null) {
            throw new FlooringMasteryDaoException("\n ERROR: Wrong Product type entry. \n");
        }

        return unitCostArrays;
    }

    @Override
    public Set<String> productsList() throws FlooringMasteryDaoException {
        loadProducts();
        return products.keySet();
    }

    @Override
    public Set<String> statesList() throws FlooringMasteryDaoException {
        loadTaxes();
        return taxes.keySet();
    }

    private List<Order> getAllOrders() {
        return new ArrayList<Order>(orders.values());
    }

    private String formatDate(String orderDate) {
        Scanner scanner;
        DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate datee;

        datee = LocalDate.parse(orderDate, formatterr);
        String dateF = datee.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return dateF;
    }

    private boolean loadMode() throws FlooringMasteryDaoException {
        try {
            Scanner scanner;
            String mode;
            scanner = new Scanner(new BufferedReader(new FileReader("ModeChoice.txt")));
            String currentLine;
//            String[] currentTokens;
            scanner.nextLine().isEmpty();
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

//                currentTokens = currentLine.split(DELIMITER);
//                String state = currentTokens[0];
                mode = currentLine;
//                taxes.put(state, tax);
                if (mode.equalsIgnoreCase("training")){
                    return true;
                }      

            }
        } catch (FileNotFoundException ex) {
            throw new FlooringMasteryDaoException(" Could not load ModeChoice file into memory", ex);
        }
        return false;

    }
    
    private void loadTaxes() throws FlooringMasteryDaoException {
        try {
            Scanner scanner;

            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));
            String currentLine;
            String[] currentTokens;
            scanner.nextLine().isEmpty();
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);
                String state = currentTokens[0];
                String tax = currentTokens[1];
                taxes.put(state, tax);

            }
        } catch (FileNotFoundException ex) {
            throw new FlooringMasteryDaoException(" Could not load file into memory", ex);
        }

    }

    private void loadProducts() throws FlooringMasteryDaoException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_INFO)));
            String currentLine;
            String[] currentTokens;
            scanner.nextLine().isEmpty();
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);
                String loadType = currentTokens[0];
                BigDecimal loadProductCostPsf = new BigDecimal(currentTokens[1]);
                BigDecimal loadLaborCostPsf = new BigDecimal(currentTokens[2]);
                BigDecimal[] test = {loadProductCostPsf, loadLaborCostPsf};
                products.put(loadType, test);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            throw new FlooringMasteryDaoException(" Could not load file into memory", ex);
        }
    }

    private void writeOrders(String orderDate) throws IOException, FlooringMasteryDaoException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("Orders_" + orderDate + ".txt"));
        } catch (IOException ex) {
            throw new FlooringMasteryDaoException(" Could not load file into memory", ex);
        }
        List<Order> ordersList = this.getAllOrders();

        for (Order currentOrder : ordersList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getLaborCostPsf() + DELIMITER
                    + currentOrder.getLaborCostArea() + DELIMITER
                    + currentOrder.getProductCostPsf() + DELIMITER
                    + currentOrder.getProductCostArea() + DELIMITER
                    + currentOrder.getTotalCost() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getTotalTax() + DELIMITER
                    + currentOrder.getTotalCostAfterTax());
            out.flush();
        }
        out.close();

    }

    private void loadOrders(String orderDate) throws FlooringMasteryDaoException {
        try {
            Scanner scanner;
            DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate datee;

            datee = LocalDate.parse(orderDate, formatterr);
            String dateF = datee.format(DateTimeFormatter.ofPattern("MMddyyyy"));
            String datetest = "Orders_" + dateF + ".txt";

            scanner = new Scanner(new BufferedReader(new FileReader(datetest)));

            String currentLine;
            String[] currentTokens;
//        scanner.nextLine().isEmpty();
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                currentOrder.setProductType(currentTokens[3]);
//                currentOrder.setArea(new BigDecimal(currentTokens[4]));
                currentOrder.setArea(currentTokens[4]);
                currentOrder.setLaborCostPsf(new BigDecimal(currentTokens[5]));
                currentOrder.setLaborCostArea(new BigDecimal(currentTokens[6]));
                currentOrder.setProductCostPsf(new BigDecimal(currentTokens[7]));
                currentOrder.setProductCostArea(new BigDecimal(currentTokens[8]));
                currentOrder.setTotalCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTaxRate(new BigDecimal(currentTokens[10]));
                currentOrder.setTotalTax(new BigDecimal(currentTokens[11]));
                currentOrder.setTotal(new BigDecimal(currentTokens[12]));

                orders.put(currentOrder.getOrderNumber(), currentOrder);
            }
            scanner.close();
        } catch (FileNotFoundException | DateTimeParseException | NumberFormatException ex) {
            throw new FlooringMasteryDaoException(" Could not load file into memory", ex);
        }

    }

}
