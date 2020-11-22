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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderDaoFileImpl implements FlooringOrderDao {

    public static final String PRODUCTS_FILE = "products.txt";
    public static final String TAX_FILE = "taxfile.txt";
    public static final String ORDER_NUMBER = "odernumberfile.txt";
    public static final String DELIMITER = "::";

    private Map<Integer, Order> orders = new HashMap<>();
    private Map<String, StateTaxRate> taxRates = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    @Override
    public List<Order> displayOrders(LocalDateTime date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(LocalDateTime orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order removeOrder(LocalDateTime orderDate, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(int orderNumber, Order order) throws FlooringOrderPersistenceException {
        loadProduct();
        loadTax();
        orderNumber = loadOrderNumber();
        orderNumber += 1;
        Order prevOrder = orders.put(orderNumber, order);
        writeOrderNumber(orderNumber); //only if user saves order
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

    /**
     * Writes all orders to an order file.
     *
     * @throws FlooringOrderPersistenceException if an error occurs writing to
     * the file
     */
    private void writeOrderFile() throws FlooringOrderPersistenceException {
        // We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new FlooringOrderPersistenceException(
                    "Could not save order data.", e);

        }
        // Write out the Order objects to the file.
        // We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {
            // turn a Student into a String
            studentAsText = marshallStudent(currentStudent);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
//     private int unmarshallOrderNumber(String orderNumberAsText){
//      
//        String orderNumberToken = orderNumberAsText;
//        int orderNumber = Integer.parseInt(orderNumberToken);
//        
//        return orderNumber;
//    }

    private int loadOrderNumber() throws FlooringOrderPersistenceException {
        Scanner myScanner;
        try {
            //create scanner for reading the file
            myScanner = new Scanner(new BufferedReader(new FileReader(ORDER_NUMBER)));
        } catch (FileNotFoundException e) {
            throw new FlooringOrderPersistenceException("-_- Could not load file data into memory.", e);
        }
        //currentLine holds the most recent line read from the file
        String currentLine;

        myScanner.hasNextLine();

        //get the next line in the file
        currentLine = myScanner.nextLine();
        //parse line to Integer 
        int orderNumber = Integer.parseInt(currentLine);
        //close scanner
        myScanner.close();
        return orderNumber;
    }

    private void writeOrderNumber(int orderNum) throws FlooringOrderPersistenceException {
        // We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDER_NUMBER));
        } catch (IOException e) {
            throw new FlooringOrderPersistenceException(
                    "Could not save order data.", e);
        }
        out.println(Integer.toString(orderNum));
        // force PrintWriter to write line to the file
        out.flush();
        // Clean up
        out.close();
    }
}
