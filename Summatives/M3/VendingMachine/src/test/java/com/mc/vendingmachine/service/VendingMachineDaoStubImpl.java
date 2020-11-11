/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

import com.mc.vendingmachine.dao.VendingMachineDao;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;
import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Item onlyItem;

    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("PEPSI");
        BigDecimal price = new BigDecimal("1.25");
        onlyItem.setPrice(price);
        onlyItem.setInventoryOfItem(10);
        }

    public VendingMachineDaoStubImpl(Item testItem){
         this.onlyItem = testItem;
     }

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {
        if (itemName.equals(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
    }
    }
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
       List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        if (itemName.equals(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
        }       
    }

    @Override
    public Item deleteItem(String itemName) throws VendingMachinePersistenceException {
         if (itemName.equals(onlyItem.getItemName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item editItem(String itemName, Item item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int giveChange(BigDecimal pennies) {
        return pennies.intValue()*100;
    }

    @Override
    public void updateItemInventory(String itemName) throws VendingMachinePersistenceException {
        onlyItem.setInventoryOfItem(onlyItem.getInventoryOfItem()-1);
    }
    
}
