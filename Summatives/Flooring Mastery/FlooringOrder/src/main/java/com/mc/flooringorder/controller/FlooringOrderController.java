/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.controller;

import com.mc.flooringorder.dao.FlooringOrderDao;
import com.mc.flooringorder.dao.FlooringOrderDaoFileImpl;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import com.mc.flooringorder.dto.Product;
import com.mc.flooringorder.dto.StateTaxRate;
import com.mc.flooringorder.service.FlooringOrderServiceLayer;
import com.mc.flooringorder.service.FlooringOrderServiceLayerImpl;
import com.mc.flooringorder.ui.FlooringOrderView;
import com.mc.flooringorder.ui.UserIO;
import com.mc.flooringorder.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class FlooringOrderController {

    private FlooringOrderView view = new FlooringOrderView();
    private FlooringOrderServiceLayer service = new FlooringOrderServiceLayerImpl();
    private FlooringOrderDao dao = new FlooringOrderDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();

    public void run() throws FlooringOrderPersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    io.print("Display Orders");
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    io.print("Edit an Order");
                    break;
                case 4:
                    io.print("Remove an Order");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }

        }
        io.print("Good Bye");
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addOrder() throws FlooringOrderPersistenceException {
        view.displayCreateOrderBanner();
//       
        BigDecimal area = view.getOrderArea();
        String date = view.getOrderDate();//use date to create order file or append to existing order file
        String name = view.getOrderName();
//        String product = view.getOrderProduct();//read in product objects into a list and check for match then use costpersquarefoot and laborcostpersquarefoot fields
//        String state = view.getOrderState();//read in state objects into a list and check for match then use statename and taxrate fields

        List<String> stateAbbreviations = dao.getAllTaxRatesStateAbbreviations();
        String state = view.getOrderState(stateAbbreviations);//read in state objects into a list and check for match then use statename and taxrate fields

        List<String> productNames = dao.getAllProductNames();
        String product = view.getOrderProduct(productNames);//read in product objects into a list and check for match then use costpersquarefoot and laborcostpersquarefoot fields
        Order newOrder = new Order(service.getOrderNumber(date));

//        service.getOrderNumber(date);
        newOrder.setCustomerName(name);
        newOrder.setArea(area);
        newOrder.setOrderDate(date);
        newOrder.setState(state);
        newOrder.setProductType(product);

        Product chosenProduct = service.getProduct(product);
        newOrder.setCostPerSquareFoot(chosenProduct.getProductCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot(chosenProduct.getProductLaborCostPerSquareFoot());

        StateTaxRate taxRate = service.getStateTaxRate(state);
        newOrder.setTaxRate(taxRate.getStateTaxRate());
        
//        BigDecimal costPerSquareFoot = new BigDecimal(newOrder.getCostPerSquareFoot().toString()).setScale(2, RoundingMode.HALF_UP);
//        BigDecimal projectArea = new BigDecimal(newOrder.getArea().toString()).setScale(2, RoundingMode.HALF_UP);
////         newOrder.setOrderNumber(orderNumber);
//        //then do calculations
//        BigDecimal materialCost = projectArea.multiply(costPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
//        newOrder.setMaterialCost(materialCost);
//
//        BigDecimal laborCostPerSquareFoot = new BigDecimal(newOrder.getLaborCostPerSquareFoot().toString()).setScale(2, RoundingMode.HALF_UP);
//        BigDecimal laborCost = area.multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
//        newOrder.setLaborCost(laborCost);
//
//        BigDecimal taxRateValue = new BigDecimal(newOrder.getTaxRate().toString()).setScale(2, RoundingMode.HALF_UP);
//        BigDecimal percentage = new BigDecimal("100");
//        BigDecimal taxRateCalc = taxRateValue.divide(percentage, 2, RoundingMode.HALF_UP);
//        BigDecimal tax = materialCost.add(laborCost).multiply(taxRateCalc).setScale(2, RoundingMode.HALF_UP); //tax rates are stored as whole numbers
//        newOrder.setTax(tax);
//
//        BigDecimal total = materialCost.add(laborCost).add(tax).setScale(2, RoundingMode.HALF_UP);
//        newOrder.setTotal(total);

        service.createOrder(service.getOrderNumber(date), newOrder, date);
        view.displayCreateSuccessBanner();

    }

}
