/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class StayPositive {
    public static void main(String[] args) {
   
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter a starting value.");
        int start = Integer.parseInt(myScanner.nextLine());

        
        System.out.println("Counting down...");
        
        if (start < 0){
        System.out.println("You must enter a positive number.");
        }
        
        
        while ( start >= 0 && start <= start){
            
            
                System.out.print(" " + start);
//                if(start % 10 == 0){
//                 System.out.print("\n");
//}         
            
               start--;
        }
        
    }
}


