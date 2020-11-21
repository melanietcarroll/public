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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class FlooringOrderDaoFileImpl implements FlooringOrderDao {
    
    public static final String PRODUCTS_FILE = "products.txt";
    public static final String TAX_FILE = "taxfile.txt";
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
        Order prevOrder = orders.put(orderNumber, order);
        return prevOrder;
    }
    private Product unmarshallProduct(String productAsText){
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
    
    private void loadProduct() throws FlooringOrderPersistenceException{
        Scanner myScanner;
        try{
            //create scanner for reading the file
            myScanner = new Scanner (new BufferedReader(new FileReader(PRODUCTS_FILE)));
        }catch (FileNotFoundException e){
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
                
        while(myScanner.hasNextLine()){
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
     private StateTaxRate unmarshallTaxFile(String taxFileAsText){
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
    private void loadTax() throws FlooringOrderPersistenceException{
        Scanner myScanner;
        try{
            //create scanner for reading the file
            myScanner = new Scanner (new BufferedReader(new FileReader(TAX_FILE)));
        }catch (FileNotFoundException e){
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
        while(myScanner.hasNextLine()){
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
}
