/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;
import java.util.Random;
/**
 *
 * @author Shawn
 */
public class CoinFlipper {
    public static void main(String[] args) {
        Random coinFlipper = new Random();
        
        boolean flipResult = coinFlipper.nextBoolean();
        System.out.println("Ready, Set, Flip.....!");
        
        if (flipResult == true){
            System.out.println("You got HEADS!");
        }else if (flipResult == false){
            System.out.println("You got TAILS!");
        }
    }
}
