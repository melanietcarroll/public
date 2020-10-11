/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

/**
 *
 * @author Shawn
 */
public class LovesMe {
    public static void main(String[] args) {
         int petal = 34;
    
        System.out.println("Well here goes nothing...");
    
    while (petal > 0){
             if (petal > 0){
            System.out.println("It loves me NOT!");
            petal --;
               System.out.println(petal);
            
            if (petal > 0) {
                System.out.println("It loves me!");
                   
            petal --;
               System.out.println(petal);
    }
       }
    }
    
        System.out.println("I knew it! It LOVES ME!");
    //I used a while loop because it seemed logical to be able to add the nested if statements to decrement the petals
}
}
