/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;
import java.util.Random;
/**
 *
 * @author Shawn
 */
public class MaybeItLovesMe {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
       int randomPetal = randomizer.nextInt(89 - 13) + (13);
        System.out.println(randomPetal);
       
       while (randomPetal > 0){
           if (randomPetal > 0){
            System.out.println("It loves me NOT!");
            randomPetal --;
               System.out.println(randomPetal);
            
            if (randomPetal > 0) {
                System.out.println("It loves me!");
                   
            randomPetal --;
               System.out.println(randomPetal);
    }
       }
       }
       
       if (randomPetal % 2 == 0){
           System.out.println("Oh, wow! It really LOVES me!");
       }else{
           System.out.println("AWWWW bummer");
       }
    }
}

