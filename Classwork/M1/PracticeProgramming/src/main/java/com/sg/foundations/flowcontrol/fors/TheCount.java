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
public class TheCount {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.println("Start at: ");
        int start = Integer.parseInt((myScanner.nextLine()));
        System.out.println("Stop at: ");
        int stop = Integer.parseInt((myScanner.nextLine()));
        System.out.println("Count by: ");
        int count = Integer.parseInt((myScanner.nextLine()));
        

        for (int i = start; i <= stop; i += count){
             System.out.print(" " + i);
             
             if(i % 3 == 0){
            System.out.print(" Ah ah ah!" + "\n");
         
        }
    }
}
}