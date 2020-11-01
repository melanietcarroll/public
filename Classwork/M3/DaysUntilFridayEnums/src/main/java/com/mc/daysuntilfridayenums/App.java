/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.daysuntilfridayenums;

import java.util.Scanner;
import com.mc.daysuntilfridayenums.DaysUntilFridayCalc;

/**
 *
 * @author Shawn
 */
public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
            
        System.out.println("Enter a day of the week");
        String dayFromUser = myScanner.nextLine().toUpperCase();
        
        int daysUntilFriday = daysUntilFri(DaysUntilFriday.valueOf(dayFromUser));
        System.out.println("There are " + daysUntilFriday + " days until Friday");
    }

    private static int daysUntilFri(DaysUntilFriday day) {
        int daysUntilFri = 0;
        switch(day){
            case MONDAY:
                return daysUntilFri + 4;
            case TUESDAY:  
                return daysUntilFri + 3;
            case WEDNESDAY:
                return daysUntilFri + 2;
            case THURSDAY:
                return daysUntilFri + 1;
            case FRIDAY:
                return daysUntilFri;
            case SATURDAY:
                return daysUntilFri + 6;
            case SUNDAY:
                return daysUntilFri + 5;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
