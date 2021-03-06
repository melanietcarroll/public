/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *  created 11/7/20
 * 
 * @author Melanie Carroll
 */
public class UserIOConsoleImpl implements UserIO{
    
      Scanner myScanner = new Scanner(System.in);

    @Override
    public void print(String message) {

        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String read = myScanner.nextLine();
        return read;
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
              
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(prompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Incorrect value. Enter a valid number.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            num = readInt(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public double readDouble(String prompt) {
        
    while (true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Incorrect value. Enter a valid number.");
            }
        }
    }
    

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            num = readInt(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Incorrect value. Enter a valid number.");
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            num = readInt(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public long readLong(String prompt) {
         while (true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Incorrect value. Enter a valid number.");
            }
        }

    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            num = readInt(prompt);
        } while (num < min || num > max);

        return num;
    } 

    @Override
    public BigDecimal readBig(String prompt) {
        BigDecimal max = new BigDecimal("9.99");
        BigDecimal min = new BigDecimal("0.01");
        boolean invalidInput = true;
        BigDecimal num = new BigDecimal("0");
        while (invalidInput) {
            try {
              
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(prompt);
                // Get the input line, and try and parse
              num = new BigDecimal(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
              
                
                if (num.compareTo(min)<0){
                    invalidInput = true;
                }
                if (num.compareTo(max)>0){
                    invalidInput = true;
                }
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Incorrect value. Enter a valid number.");
            }
           
        }
        return num;
    
}
}
