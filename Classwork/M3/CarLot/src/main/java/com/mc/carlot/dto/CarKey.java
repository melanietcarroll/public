/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.dto;

/**
 *
 * @author Shawn
 */
public class CarKey {
    private String VIN;
    private boolean laserCut;

     public CarKey(String VIN) {
        this.VIN = VIN;
    }
    
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public boolean isLaserCut() {
        return laserCut;
    }

    public void setLaserCut(boolean laserCut) {
        this.laserCut = laserCut;
    }
    
}
