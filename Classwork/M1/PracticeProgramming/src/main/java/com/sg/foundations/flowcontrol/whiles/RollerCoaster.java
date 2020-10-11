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
public class RollerCoaster {
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("We're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        String keepRiding = "y";
        int loopsLooped = 0;
        //while (keepRiding.equals("y")) {
//            System.out.println("WHEEEEEEEEEEEEEeEeEEEEeEeeee.....!!!");
//            System.out.println("Want to keep going? (y/n) :");
//            keepRiding = myScanner.nextLine();
//            loopsLooped++;
//        }

        while (keepRiding.equals("n")){
            System.out.println("Well this is pretty boring....");
            System.out.println("Want to ride again? (y/n)");
            keepRiding = myScanner.nextLine();
            loopsLooped++;
        }
        
        System.out.println("Wow, that was FUN!");
        System.out.println("We looped that loop " + loopsLooped + " times!!");
    }
    //if you enter a letter other than y or n it kicks you out of the loop
    //the condition in the while loop is at the beginning so if it evaluates to false
    //the loop never runs
}
