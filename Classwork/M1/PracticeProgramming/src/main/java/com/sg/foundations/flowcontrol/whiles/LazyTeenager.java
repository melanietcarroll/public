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
public class LazyTeenager {
    public static void main(String[] args) {
        Random random = new Random();
        int chance = random.nextInt(10) + 1;
        int chancePercent = chance * 10;
        int count = 0;
        boolean roomClean = false;
        
        
        while (roomClean == false && chancePercent < 100){
            System.out.println("Clean your room!");
            chancePercent = chancePercent + 10;
            count ++;
            
         
            if (count == 7){
                System.out.println("You're grounded and I'm taking your XBOX");
                break;
            }
            }
        if (chancePercent == 100){
            System.out.println("Fine but I'm not eating my peas!");
        }
        }
        
        
    
}
