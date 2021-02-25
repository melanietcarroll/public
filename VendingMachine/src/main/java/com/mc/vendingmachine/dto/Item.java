/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *  created 11/7/20
 * @author Melanie Carroll
 */
public class Item {
    private String itemName;

    @Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + ", price=" + price + ", inventoryOfItem=" + inventoryOfItem + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.itemName);
        hash = 67 * hash + Objects.hashCode(this.price);
        hash = 67 * hash + this.inventoryOfItem;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return true;
    }
    private BigDecimal price;
    private int inventoryOfItem;
    
     public Item(String itemName) {
        this.itemName = itemName;
    }
     public Item(){
         
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
