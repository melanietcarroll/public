/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shawn
 */
public enum CoinValues {
    PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
      public final int value;

      
    CoinValues(int value) {
        this.value = value;
    }
    //Map<CoinValues, Integer> coinCount = new HashMap<>();
}
