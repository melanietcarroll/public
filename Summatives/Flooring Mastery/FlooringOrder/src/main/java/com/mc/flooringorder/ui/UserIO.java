/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public interface UserIO {
    void print(String message);

    String readStringInput(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    BigDecimal readBig(String prompt);
    
    LocalDate readDate(String prompt);
    
    LocalDate readDate(String prompt, LocalDate min, LocalDate max);
    
    BigDecimal readArea(String prompt);
    
    String readString(String prompt);
    
    String readString(String prompt, List<String> list);
    
    String readStringEditedStateOrProduct(String prompt, List<String> list, String prevValue);
    
    String readStringEditedOrder(String prompt, String prevValue);
    
    BigDecimal readAreaEditedOrder(String prompt, BigDecimal prevValue);
    
    
}
