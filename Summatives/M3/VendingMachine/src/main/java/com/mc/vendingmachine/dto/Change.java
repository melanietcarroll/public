/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *  created 11/7/20
 * @author Melanie Carroll
 */
public class Change {
   //add method to service interface to calculate change
    
    //use the enums to make switch statement?? should this go in the method impl of service layer?-probably
    private int changeOwedInPennies;
    private int numQuarters;
    private int numDimes;
    private int numNickels;
    private int numPennies;
    
     public Change(int changeOwedInPennies) {
        this.changeOwedInPennies = changeOwedInPennies;
    }

    public void setNumQuarters(int numQuarters) {
        this.numQuarters = numQuarters;
    }

    public void setNumDimes(int numDimes) {
        this.numDimes = numDimes;
    }

    public void setNumNickels(int numNickels) {
        this.numNickels = numNickels;
    }

    public void setNumPennies(int numPennies) {
        this.numPennies = numPennies;
    }


    public void setChangeOwedInPennies(int changeOwedInPennies) {
        this.changeOwedInPennies = changeOwedInPennies;
    }

    public int getChangeOwedInPennies() {
        return changeOwedInPennies;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public int getNumPennies() {
        return numPennies;
    }
    
}
