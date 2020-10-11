/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class FourTimes {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("Which times table shall I recite?");
        int number = Integer.parseInt((myScanner.nextLine()));
        
        for (int i = 1; i <=15; i++){
            int value = i * number;
            System.out.println(i + " * " + number + " = " + value);
            
        }
        
    }
}
