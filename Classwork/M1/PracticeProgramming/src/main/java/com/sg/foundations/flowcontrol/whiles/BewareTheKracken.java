/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Shawn
 */
public class BewareTheKracken {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
                  
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");
        
        int depthDivedInFt = 0;
        while(depthDivedInFt < 36200){
            System.out.println("So far, we've swam " + depthDivedInFt + " feet" + " and there goes a " + chooseFish());
          
            
            if(depthDivedInFt >=1000){
                System.out.println("Do you want to come up for air? (y/n)");
                String exit = myScanner.nextLine();
                if (exit.equals("y")){
                    System.out.println("Good idea!");
                    break;
                }
            }

            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }
        depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    
    }
    public static String chooseFish(){
        Random random = new Random();
 
        String[] fish = {"tuna", "shark", "clown fish"};
        
        return fish[random.nextInt(fish.length)];
        
    }
    //if we change the while loop to true, the loop would still execute the same until the condition turned false
    //then the loop would exit
}
