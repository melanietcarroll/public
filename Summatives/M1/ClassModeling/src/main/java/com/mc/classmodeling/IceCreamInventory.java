/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classmodeling;

/**
 *
 * @author Shawn
 */
public class IceCreamInventory {

    private int numberOfContainers;
    private String brand;
    private String flavor;
    private String dairyFree;

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public void setDairyFree(String dairyFree) {
        this.dairyFree = dairyFree;
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public String getBrand() {
        return brand;
    }

    public String getFlavor() {
        return flavor;
    }

    public String getDairyFree() {
        return dairyFree;
    }

    public void addContainer() {

    }

    public void removerContainer() {

    }
}
