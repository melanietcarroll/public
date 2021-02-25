/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.dto.Order;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Melanie Carroll
 */
public class FlooringOrderServiceLayerImplTest {

    private FlooringOrderServiceLayer service;

    public FlooringOrderServiceLayerImplTest() {
//        FlooringOrderDao dao = new FlooringOrderDaoStubImpl();
//        FlooringOrderAuditDao auditDao = new FlooringOrderAuditDaoStubImpl();
//
//        service = new FlooringOrderServiceLayerImpl(dao, auditDao);
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringOrderServiceLayer.class);
    }

    @Test
    public void testCreateValidOrder() {
        // ARRANGE
        BigDecimal taxRate2 = new BigDecimal("25");
        BigDecimal area2 = new BigDecimal("650");
        BigDecimal costPerSquareFoot2 = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot2 = new BigDecimal("4.75");
        BigDecimal materialCost2 = new BigDecimal("3347.5");
        BigDecimal laborCost2 = new BigDecimal("3087.5");
        BigDecimal tax2 = new BigDecimal("1608.75");
        BigDecimal total2 = new BigDecimal("8043.75");
        String orderDate2 = "12022020";

        // Create our method test inputs
        int orderNumber2 = 2;
        Order newOrder2 = new Order(orderNumber2);
        newOrder2.setCustomerName("TurboTax");
        newOrder2.setState("CA");
        newOrder2.setTaxRate(taxRate2);
        newOrder2.setProductType("Wood");
        newOrder2.setArea(area2);
        newOrder2.setCostPerSquareFoot(costPerSquareFoot2);
        newOrder2.setLaborCostPerSquareFoot(laborCostPerSquareFoot2);
        newOrder2.setMaterialCost(materialCost2);
        newOrder2.setLaborCost(laborCost2);
        newOrder2.setTax(tax2);
        newOrder2.setTotal(total2);
        newOrder2.setOrderDate(orderDate2);
        // ACT
        try {
            service.createOrder(orderNumber2, newOrder2, orderDate2);
        } catch (FlooringOrderPersistenceException e) {
            // ASSERT
            fail("Order was valid. No exception should have been thrown.");
        }
    }

    @Test
    public void testGetAllOrders() throws Exception {
        // ARRANGE
        BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12012020";

        // Create our method test inputs
        int orderNumber = 1;
        Order testClone = new Order(orderNumber);
        testClone.setCustomerName("Acme");
        testClone.setState("TX");
        testClone.setTaxRate(taxRate);
        testClone.setProductType("Wood");
        testClone.setArea(area);
        testClone.setCostPerSquareFoot(costPerSquareFoot);
        testClone.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        testClone.setMaterialCost(materialCost);
        testClone.setLaborCost(laborCost);
        testClone.setTax(tax);
        testClone.setTotal(total);
        testClone.setOrderDate(orderDate);

        // ACT & ASSERT
        assertEquals(1, service.displayOrders(orderDate).size(),
                "Should only have one order.");
        assertTrue(service.displayOrders(orderDate).contains(testClone),
                "The one order should be Acme.");
    }

    @Test
    public void testGetOrder() throws Exception {
        // ARRANGE
        BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12012020";

        // Create our method test inputs
        int orderNumber = 1;
        Order testClone = new Order(orderNumber);
        testClone.setCustomerName("Acme");
        testClone.setState("TX");
        testClone.setTaxRate(taxRate);
        testClone.setProductType("Wood");
        testClone.setArea(area);
        testClone.setCostPerSquareFoot(costPerSquareFoot);
        testClone.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        testClone.setMaterialCost(materialCost);
        testClone.setLaborCost(laborCost);
        testClone.setTax(tax);
        testClone.setTotal(total);
        testClone.setOrderDate(orderDate);

        // ACT & ASSERT
        Order shouldBeAcme = service.getOrder(orderDate, orderNumber);
        assertNotNull(shouldBeAcme, "Getting orderDate and orderNumber should be not null.");
        assertEquals(testClone, shouldBeAcme,
                "Order stored under orderDate and orderNumber should be Acme.");

        try {
            service.getOrder("12312020", 16);
            fail("Expected FlooringOrderNotFound Exception was not thrown.");
        } catch (FlooringOrderPersistenceException e) {
            // ASSERT
            fail("Incorrect exception was thrown.");
        } catch (FlooringOrderNotFoundException e) {
            return;
        }
    }

    @Test
    public void testRemoveOrder() throws Exception {
        // ARRANGE
        BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12012020";

        // Create our method test inputs
        int orderNumber = 1;
        Order testClone = new Order(orderNumber);
        testClone.setCustomerName("Acme");
        testClone.setState("TX");
        testClone.setTaxRate(taxRate);
        testClone.setProductType("Wood");
        testClone.setArea(area);
        testClone.setCostPerSquareFoot(costPerSquareFoot);
        testClone.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        testClone.setMaterialCost(materialCost);
        testClone.setLaborCost(laborCost);
        testClone.setTax(tax);
        testClone.setTotal(total);
        testClone.setOrderDate(orderDate);

        // ACT & ASSERT
        Order shouldBeAcme = service.removeOrder("12012020", 1);
        assertNotNull(shouldBeAcme, "Removing 12012020 & 1 should be not null.");
        assertEquals(testClone, shouldBeAcme, "Order removed from 12012020 & 1 should be Ada.");

        Order shouldBeNull = service.removeOrder("12312020", 14);
        assertNull(shouldBeNull, "Removing 12312020 & 14 should be null.");

    }

    @Test
    public void testEditOrder() throws Exception {
        // ARRANGE
        BigDecimal taxRate = new BigDecimal("4.45");
        BigDecimal area = new BigDecimal("800");
        BigDecimal costPerSquareFoot = new BigDecimal("5.15");
        BigDecimal laborCostPerSquareFoot = new BigDecimal("4.75");
        BigDecimal materialCost = new BigDecimal("4120");
        BigDecimal laborCost = new BigDecimal("3800");
        BigDecimal tax = new BigDecimal("352.44");
        BigDecimal total = new BigDecimal("8272.44");
        String orderDate = "12012020";

        // Create our method test inputs
        int orderNumber = 1;
        Order testClone = new Order(orderNumber);
        testClone.setCustomerName("Acme");
        testClone.setState("TX");
        testClone.setTaxRate(taxRate);
        testClone.setProductType("Wood");
        testClone.setArea(area);
        testClone.setCostPerSquareFoot(costPerSquareFoot);
        testClone.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        testClone.setMaterialCost(materialCost);
        testClone.setLaborCost(laborCost);
        testClone.setTax(tax);
        testClone.setTotal(total);
        testClone.setOrderDate(orderDate);

        // ACT & ASSERT
        Order shouldBeAcme = service.editOrder(orderDate, orderNumber, testClone);
        assertNotNull(shouldBeAcme, "Getting orderDate and orderNumber should be not null.");
        assertEquals(testClone, shouldBeAcme,
                "Order stored under orderDate and orderNumber should be Acme.");

    }

}
