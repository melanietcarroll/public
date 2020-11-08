/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dao;

import com.mc.vendingmachine.dto.Change;
import com.mc.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  created 11/07/20
 * @author Melanie Carroll
 */
public class VendingMachineDaoImpl implements VendingMachineDao{
    
    private Map<String, Item> items = new HashMap<>();
    

    @Override
    public Item addItem(String itemName, Item item) {
        Item prevItem = items.put(itemName, item);
    return prevItem;
    }

    @Override
    public List<Item> getAllItems() {
       return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String itemName) {
        return items.get(itemName);
    }

    @Override
    public Item deleteItem(String itemName) {
        Item removedItem = items.remove(itemName);
    return removedItem;
    }

    @Override
    public Item editItem(String itemName, Item item) {
        Item editedItem = items.put(itemName, item);
return editedItem;
    }

    @Override
    public int giveChange(BigDecimal pennies) {
        int changeDue = pennies.intValue();
        return changeDue;
    }
    
}
