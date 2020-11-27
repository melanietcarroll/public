/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dao;

import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderDaoFileImpl implements FlooringOrderDao {
    
//    private final String ORDER_FILE;
//    
//     public FlooringOrderDaoFileImpl(String orderTextFile) {
//        ORDER_FILE = orderTextFile;
//    }

//    public static final String ORDER_FILE = "Orders_" + date + ".txt";
    public static final String PRODUCTS_FILE = "products.txt";
    public static final String TAX_FILE = "taxfile.txt";
//    public static final String ORDER_NUMBER = "odernumberfile.txt";
    public static final String DELIMITER = "::";

    private Map<Integer, Order> orders = new HashMap<>();
    private Map<String, StateTaxRate> taxRates = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    @Override
    public List<Order> displayOrders(String date) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(LocalDate orderDate, int orderNumber) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDate orderDate, int orderNumber) throws FlooringOrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(int orderNumber, Order order, String date) throws FlooringOrderPersistenceException {
        loadOrders(date);
        loadProduct();
        loadTax();

//        orderNumber = getOrderNumber();//do this in service to set order number?
//        orderNumber += 1;  //incrementing in method
        Order prevOrder = orders.put(orderNumber, order);//putting the order from param into a map
        //if user accepts order write order to file with order date Orders_MMDDYYYY.txt

//        writeOrderNumber(orderNumber); //only if user saves order
        writeOrderFile(date);
        return prevOrder;
    }

    private Product unmarshallProduct(String productAsText) {
        // productAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // ProductType::CostPerSquareFoot::LaborCostPerSquareFoot
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in productTokens.
        String[] productTokens = productAsText.split(DELIMITER);

        // Given the pattern above, the ProductType is in index 0 of the array.
        String productType = productTokens[0];

        // Which we can then use to create a new Product object to satisfy
        // the requirements of the Product constructor.
        Product productFromFile = new Product(productType);

        // However, there are 2 remaining tokens that need to be set into the
        // new product object. Do this manually by using the appropriate setters.
        // Index 1 - CostPerSquareFoot
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        productFromFile.setProductCostPerSquareFoot(costPerSquareFoot);

        // Index 2 - LaborCostPerSquareFoot
        BigDecimal laborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        productFromFile.setProductLaborCostPerSquareFoot(laborCostPerSquareFoot);

        return productFromFile;
    }

    private void loadProduct() throws FlooringOrderPersistenceException {
        Scanner myScanner;
        try {
            //create scanner for reading the file
            myScanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringOrderPersistenceException("-_- Could not load file data into memory.", e);
        }
        //currentLine holds the most recent line read from the file
        String currentLine;
        //currentProduct holds the most recent product unmarshalled
        Product currentProduct;
        //go through PRODUCTS_FILE line by line, decoding each line into a 
        //Product object by calling the unmarshallProduct method
        //process while we have more lines in the file

        //have to skip the first header line
        myScanner.hasNextLine();

        while (myScanner.hasNextLine()) {
            //get the next line in the file
            currentLine = myScanner.nextLine();
            //unmarshall the line into a Product 
            currentProduct = unmarshallProduct(currentLine);

            // Put currentProduct into the map using productType as the key
            products.put(currentProduct.getProductType(), currentProduct);
        }
        //close scanner
        myScanner.close();
    }

    private StateTaxRate unmarshallTaxFile(String taxFileAsText) {
        // taxFileAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // State::StateName::TaxRate
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in taxTokens.
        String[] taxTokens = taxFileAsText.split(DELIMITER);

        // Given the pattern above, the State is in index 0 of the array.
        String state = taxTokens[0];

        // Which we can then use to create a new StateTaxRate object to satisfy
        // the requirements of the StateTaxRate constructor.
        StateTaxRate taxFromFile = new StateTaxRate(state);

        // However, there are 2 remaining tokens that need to be set into the
        // new StateTaxRate object. Do this manually by using the appropriate setters.
        // Index 1 - StateName
        taxFromFile.setStateName(taxTokens[1]);

        // Index 2 - TaxRate
        BigDecimal taxRate = new BigDecimal(taxTokens[2]);
        taxFromFile.setStateTaxRate(taxRate);

        return taxFromFile;
    }

    private void loadTax() throws FlooringOrderPersistenceException {
        Scanner myScanner;
        try {
            //create scanner for reading the file
            myScanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringOrderPersistenceException("-_- Could not load file data into memory.", e);
        }
        //currentLine holds the most recent line read from the file
        String currentLine;
        //currentProduct holds the most recent product unmarshalled
        StateTaxRate currentTax;
        //go through TAX_FILE line by line, decoding each line into a 
        //StateTaxRate object by calling the unmarshallTaxFile method
        //process while we have more lines in the file
        //have to skip the first header line
        myScanner.hasNextLine();
        while (myScanner.hasNextLine()) {
            //get the next line in the file
            currentLine = myScanner.nextLine();
            //unmarshall the line into a StateTaxRate 
            currentTax = unmarshallTaxFile(currentLine);

            // Put currentTax into the map using state as the key
            taxRates.put(currentTax.getStateAbbreviation(), currentTax);
        }
        //close scanner
        myScanner.close();
    }

    private String marshallOrder(Order anOrder) {
        // We need to turn an Order object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // OrderNumber::CustomerName::State::TaxRate::ProductType::Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the order number, since that's supposed to be first.
        String orderAsText = anOrder.getOrderNumber() + DELIMITER;

        // add the rest of the properties in the correct order:
        // CustomerName
        orderAsText += anOrder.getCustomerName() + DELIMITER;

        // State
        orderAsText += anOrder.getState() + DELIMITER;

        // TaxRate
        orderAsText += anOrder.getTaxRate() + DELIMITER;

        // ProductType
        orderAsText += anOrder.getProductType() + DELIMITER;

        // Area
        orderAsText += anOrder.getArea() + DELIMITER;

        // CostPerSquareFoot
        orderAsText += anOrder.getCostPerSquareFoot() + DELIMITER;

        // LaborCostPerSquareFoot
        orderAsText += anOrder.getLaborCostPerSquareFoot() + DELIMITER;

        // MaterialCost
        orderAsText += anOrder.getMaterialCost() + DELIMITER;

        // LaborCost
        orderAsText += anOrder.getLaborCost() + DELIMITER;

        // Tax
        orderAsText += anOrder.getTax() + DELIMITER;

        // Total - don't forget to skip the DELIMITER here.
        orderAsText += anOrder.getTotal();

        // We have now turned an order to text
        return orderAsText;
    }

    private Order unmarshallOrder(String orderAsText) {

        // split  line on DELIMITER - ::
        // Leaving an array of Strings, stored in itemTokens.
        // 
        // ____________________________
        // |        |     |           |                  
        // |item    |price|inventory  |
        // | name   |     |           |                  
        // ---------------------------
        //  [0]       [1]    [2]         
        String[] orderTokens = orderAsText.split(DELIMITER);

        // Given the pattern above, the item name is in index 0 of the array.
        int orderNumber = Integer.parseInt((orderTokens[0]));

        // Which we can then use to create a new order object 
        Order orderFromFile = new Order(orderNumber);

        // Index 1 - customerName
        String customerName = orderTokens[1];
        orderFromFile.setCustomerName(customerName);

        // Index 2 - State
        String state = orderTokens[2];
        orderFromFile.setState(state);

        //Index 3 - TaxRate
        BigDecimal taxRate = new BigDecimal(orderTokens[3]);
        orderFromFile.setTaxRate(taxRate);

        //Index 4 - Product Type
        String productType = orderTokens[4];
        orderFromFile.setProductType(productType);

        //Index 5 area
        BigDecimal area = new BigDecimal(orderTokens[5]);
        orderFromFile.setArea(area);

        //Index 6 CostPerSquareFoot
        BigDecimal costPerSquareFoot = new BigDecimal(orderTokens[6]);
        orderFromFile.setCostPerSquareFoot(costPerSquareFoot);

        //Index 7 LaborCostPerSquareFoot
        BigDecimal laborCostPerSquareFoot = new BigDecimal(orderTokens[7]);
        orderFromFile.setLaborCostPerSquareFoot(laborCostPerSquareFoot);

        //Index 8 Material Cost
        BigDecimal materialCost = new BigDecimal(orderTokens[8]);
        orderFromFile.setMaterialCost(materialCost);

        //Index 9 labor cost
        BigDecimal laborCost = new BigDecimal(orderTokens[9]);
        orderFromFile.setLaborCost(laborCost);

        //Index 10 tax
        BigDecimal tax = new BigDecimal(orderTokens[10]);
        orderFromFile.setTax(tax);

        //Index 11 total
        BigDecimal total = new BigDecimal(orderTokens[11]);
        orderFromFile.setTotal(total);

        // We have now created an order
        return orderFromFile;
    }

    private void loadOrders(String date) throws FlooringOrderPersistenceException {
        Scanner myScanner;
        try {
            //create scanner for reading the file
            myScanner = new Scanner(new BufferedReader(new FileReader("Orders_" + date + ".txt")));//pull file with order date
        } catch (FileNotFoundException e) {
            throw new FlooringOrderPersistenceException("-_- Could not load file data into memory.", e);
        }
        //currentLine holds the most recent line read from the file
        String currentLine;
        //currentProduct holds the most recent order unmarshalled
        Order currentOrder;
        //go through ORDERFILE line by line, decoding each line into an 
        //Order object by calling the unmarshallOrder method
        //process while we have more lines in the file
        //have to skip the first header line
        myScanner.hasNextLine();
        while (myScanner.hasNextLine()) {
            //get the next line in the file
            currentLine = myScanner.nextLine();
            //unmarshall the line into an Order 
            currentOrder = unmarshallOrder(currentLine);

            // Put currentOrder into the map using order number as the key
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        //close scanner
        myScanner.close();
    }

    /**
     * Writes all orders to an order file.
     *
     * @throws FlooringOrderPersistenceException if an error occurs writing to
     * the file
     */
    private void writeOrderFile(String date) throws FlooringOrderPersistenceException {
        // We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.

        //readDate format from user date = "MM/dd/yyyy"
        //format LocalDate into a String:
        //String formatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
  
        //Path path = Path.of("orders/currentOrderTextFile"); BUT WILL THIS WORK--currentOrderTextFile is not a String!!
        //boolean exists = Files.exists(path);
        //if exists != false, append order
        //if exists == false, create order file in orders folder
        //create file for order:
        //File currentOrderTextFile = newFile("Orders_" + formatted + ".txt");
        //if order is edited grab list orders and re-write file
        
        //if order is deleted will have to pull that order file and grab all orders from that date to 
        //re-write to the file
        //also have to write out the header file at the beginning of each new file
        //System.out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        //currently have a date field in the Order object--
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
        } catch (IOException e) {
            throw new FlooringOrderPersistenceException(
                    "Could not save order data.", e);

        }
        // Write out the Order objects to the file.

        String orderAsText;
        
        Set<Integer> keys = orders.keySet();
        for (int k : keys){
            Order currentOrder = orders.get(k);
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }
//        LocalDate date = order.getDate();
//        List<Order> orderList = this.displayOrders(date);
//        for (Student currentStudent : studentList) {
//            // turn a Student into a String
//            studentAsText = marshallStudent(currentStudent);
//            // write the Student object to the file
//            out.println(studentAsText);
//            // force PrintWriter to write line to the file
//            out.flush();
//        }
//        // Clean up
//        out.close();
//    }
//     private int unmarshallOrderNumber(String orderNumberAsText){
//      
//        String orderNumberToken = orderNumberAsText;
//        int orderNumber = Integer.parseInt(orderNumberToken);
//        
//        return orderNumber;
//    }

//    private int loadOrderNumber() throws FlooringOrderPersistenceException {
//        Scanner myScanner;
//        try {
//            //create scanner for reading the file
//            myScanner = new Scanner(new BufferedReader(new FileReader(ORDER_NUMBER)));
//        } catch (FileNotFoundException e) {
//            throw new FlooringOrderPersistenceException("-_- Could not load file data into memory.", e);
//        }
//        //currentLine holds the most recent line read from the file
//        String currentLine;
//
//        myScanner.hasNextLine();
//
//        //get the next line in the file
//        currentLine = myScanner.nextLine();
//        //parse line to Integer 
//        int orderNumber = Integer.parseInt(currentLine);
//        //close scanner
//        myScanner.close();
//        return orderNumber;
//    }
//    private void writeOrderNumber(int orderNum) throws FlooringOrderPersistenceException {
//        // We are not handling the IOException - but
//        // we are translating it to an application specific exception and 
//        // then simple throwing it (i.e. 'reporting' it) to the code that
//        // called us.  It is the responsibility of the calling code to 
//        // handle any errors that occur.
//        PrintWriter out;
//        try {
//            out = new PrintWriter(new FileWriter(ORDER_NUMBER));
//        } catch (IOException e) {
//            throw new FlooringOrderPersistenceException(
//                    "Could not save order data.", e);
//        }
//        out.println(Integer.toString(orderNum));
//        // force PrintWriter to write line to the file
//        out.flush();
//        // Clean up
//        out.close();
//    }
//    @Override
//    public int getOrderNumber() throws FlooringOrderPersistenceException {
//        int orderNumber = loadOrderNumber();
//        orderNumber += 1;
//        return orderNumber;
//    }

    @Override
    public ArrayList<String> getAllTaxRatesStateAbbreviations() throws FlooringOrderPersistenceException {
        loadTax();
        return new ArrayList<>(taxRates.keySet());
    }

    @Override
    public ArrayList<String> getAllProductNames() throws FlooringOrderPersistenceException {
        loadProduct();
        return new ArrayList<>(products.keySet());
    }

    @Override
    public StateTaxRate getStateTaxRate(String stateAbbreviation) throws FlooringOrderPersistenceException {
        loadTax();
        return taxRates.get(stateAbbreviation);
    }

    @Override
    public Product getProduct(String productType) throws FlooringOrderPersistenceException {
        loadProduct();
        return products.get(productType);
    }

}
