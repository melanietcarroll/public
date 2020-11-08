/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Shawn
 */
public class Item {
    private String itemName;
    private BigDecimal price;
    private int inventoryOfItem;
    
     public Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInventoryOfItem() {
        return inventoryOfItem;
    }

    public void setInventoryOfItem(int inventoryOfItem) {
        this.inventoryOfItem = inventoryOfItem;
    }
}
