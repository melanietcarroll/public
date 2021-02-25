/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dto;

import java.math.BigDecimal;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class Product {
    
    private String productType;
    private BigDecimal productCostPerSquareFoot;
    private BigDecimal productLaborCostPerSquareFoot;
    
    
   public Product(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductCostPerSquareFoot() {
        return productCostPerSquareFoot;
    }

    public void setProductCostPerSquareFoot(BigDecimal productCostPerSquareFoot) {
        this.productCostPerSquareFoot = productCostPerSquareFoot;
    }

    public BigDecimal getProductLaborCostPerSquareFoot() {
        return productLaborCostPerSquareFoot;
    }

    public void setProductLaborCostPerSquareFoot(BigDecimal productLaborCostPerSquareFoot) {
        this.productLaborCostPerSquareFoot = productLaborCostPerSquareFoot;
    }
    
    
}
