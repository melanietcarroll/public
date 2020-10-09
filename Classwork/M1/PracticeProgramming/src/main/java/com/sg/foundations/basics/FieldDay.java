/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.basics;
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class FieldDay {
    public static void main(String[] args) {
        String userName;
        int baggins;
        int dresden;
        int howl;
        int potter;
        int vimes;
        
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("What's your last name?");
        userName = myScanner.nextLine();
        
        baggins = userName.compareToIgnoreCase("Baggins");
        dresden = userName.compareToIgnoreCase("Dresden");
        howl = userName.compareToIgnoreCase("Howl");
        potter = userName.compareToIgnoreCase("Potter");
        vimes = userName.compareToIgnoreCase("Vimes");
        
        if (baggins <= 0){
            System.out.println("You will be on the 'Red Dragons' team!");
        }
        if ((baggins > 0) && (howl <= 0)){
            System.out.println("You will be on the 'Moving Castles' team!");
    }
        if ((howl > 0) && (potter <= 0)){
            System.out.println("You will be on the 'Golden Snitches' team!");
        }
        if ((potter > 0) && (vimes < 0)){
            System.out.println("You will be on the 'Night Guards' team!");
        }
        if ((vimes >= 0)){
            System.out.println("You will be on the 'Black Holes' team!");
        }
    }
}
