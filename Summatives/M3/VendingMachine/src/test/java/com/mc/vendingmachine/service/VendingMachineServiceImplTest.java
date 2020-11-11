/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

import com.mc.vendingmachine.dao.VendingMachineAuditDao;
import com.mc.vendingmachine.dao.VendingMachineDao;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class VendingMachineServiceImplTest {
    
    private VendingMachineService service;

public VendingMachineServiceImplTest() {
    VendingMachineDao dao = new VendingMachineDaoStubImpl();
    VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

    service = new VendingMachineServiceImpl(dao, auditDao);
}
   
    
    @Test
public void testCreateValidItem() {
    // ARRANGE
    Item item = new Item("DORITOS");
    BigDecimal price = new BigDecimal("1.75");
    item.setPrice(price);
    item.setInventoryOfItem(6);
    
    // ACT
    try {
        service.addItem(item.getItemName(), item);
    } catch (VendingMachinePersistenceException
            | VendingMachineDataValidationException
            | VendingMachineDuplicateItemNameException e) {
    // ASSERT
        fail("Item was valid. No exception should have been thrown.");
    }
}
    @Test
public void testCreateDuplicateItem() {
    // ARRANGE
   Item item = new Item("PEPSI");
        BigDecimal price = new BigDecimal("1.25");
        item.setPrice(price);
        item.setInventoryOfItem(10);

    // ACT
    try {
        service.addItem(item.getItemName(), item);
        fail("Expected DupeId Exception was not thrown.");
    } catch (VendingMachineDataValidationException
            | VendingMachinePersistenceException e) {
    // ASSERT
        fail("Incorrect exception was thrown.");
    } catch (VendingMachineDuplicateItemNameException e){
        return;
    }
}
@Test
public void testCreateItemInvalidData() throws Exception {
    // ARRANGE
    Item item = new Item("");
    BigDecimal price = new BigDecimal("2.25");
    item.setPrice(price);
    item.setInventoryOfItem(6);

    // ACT
    try {
        service.addItem(item.getItemName(), item);
        fail("Expected ValidationException was not thrown.");
    } catch (VendingMachineDuplicateItemNameException
            | VendingMachinePersistenceException e) {
    // ASSERT
        fail("Incorrect exception was thrown.");
    } catch (VendingMachineDataValidationException e){
        return;
    }  
}
@Test
public void testGetAllItems() throws Exception {
    // ARRANGE
    Item testClone = new Item("PEPSI");
         BigDecimal price = new BigDecimal("1.25");
        testClone.setPrice(price);
        testClone.setInventoryOfItem(10);

    // ACT & ASSERT
    assertEquals( 1, service.getAllItems().size(), 
                                   "Should only have one item.");
    assertTrue( service.getAllItems().contains(testClone),
                              "The one item should be PEPSI.");
}
@Test
public void testGetItem() throws Exception {
    // ARRANGE
    Item testClone = new Item("PEPSI");
         BigDecimal price = new BigDecimal("1.25");
        testClone.setPrice(price);
        testClone.setInventoryOfItem(10);

    // ACT & ASSERT
    Item shouldBePepsi = service.getItem("PEPSI");
    assertNotNull(shouldBePepsi, "Getting PEPSI should be not null.");
    assertEquals(testClone, shouldBePepsi,
                                   "item stored under PEPSI should be PEPSI.");

    Item shouldBeNull = service.getItem("COKE");    
    assertNull( shouldBeNull, "Getting COKE should be null.");

}
@Test
public void testRemoveItem() throws Exception {
    // ARRANGE
   Item testClone = new Item("PEPSI");
         BigDecimal price = new BigDecimal("1.25");
        testClone.setPrice(price);
        testClone.setInventoryOfItem(10);

    // ACT & ASSERT
    Item shouldBePepsi = service.deleteItem("PEPSI");
    assertNotNull( shouldBePepsi, "Removing PEPSI should be not null.");
    assertEquals( testClone, shouldBePepsi, "item removed from PEPSI should be PEPSI.");

    Item shouldBeNull = service.deleteItem("COKE");    
    assertNull( shouldBeNull, "Removing COKE should be null.");

}
}