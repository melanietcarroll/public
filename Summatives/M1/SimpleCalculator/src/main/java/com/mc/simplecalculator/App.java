/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.simplecalculator;
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class App {
    public static void main(String[] args) {
        
    Scanner myScanner = new Scanner(System.in);
    int a;
    int b;
    SimpleCalculator myCalc = new SimpleCalculator();
    boolean gamePlay = true;
    
    while (gamePlay == true){
        System.out.println("Choose 1-add, 2-subtract, 3-divide, 4-multiply, 5-exit");
        int choice = myScanner.nextInt();
            
        if (choice==5){
            gamePlay = false;
            System.out.println("Thank you!");
            return;
        }
         System.out.println("Choose the first operand.");
         a = myScanner.nextInt();
         
         System.out.println("Choose the second operand.");
         b = myScanner.nextInt();
         
         if (choice == 1){
            int result = myCalc.add(a, b);
             System.out.println("The sum is " + result);
         }
         if (choice == 2){
            int result = myCalc.subtract(a, b);
             System.out.println("The difference is " + result);
         }
         if (choice == 3){
             int result = myCalc.divide(a, b);
             System.out.println("The product is " + result);
         }
         if (choice == 4){
             int result = myCalc.multiply(a, b);
             System.out.println("The quotient is " + result);
         }
    } 
}
}