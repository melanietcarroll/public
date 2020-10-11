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
public class ForTimesFor {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int points = 0;
        
        System.out.println("Which times table shall I recite?");
        int number = Integer.parseInt((myScanner.nextLine()));
        
        for (int i = 1; i <=15; i++){
            System.out.println(i + " * " + number + " = ");
            int result = Integer.parseInt((myScanner.nextLine()));
            
            int value = i * number;
            
            if (result == value){
            System.out.println("Correct!");
            points ++;
            }else{
                System.out.println("The correct answer was " + value);
            }
            
        }
        System.out.println("You got " + points + " points.");
        if (points < 8){
            System.out.println("You should study more!");
        }else if (points > 13){
            System.out.println("Congratulations!!");
        }
    }
}
