/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.luckysevens;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Shawn
 */
public class LuckySevens {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int startingBet;
        int gameMoney;
        int rolls = 0;
        int maxRolls = 0;
        
        System.out.println("How many dollars do you have?");
        startingBet = Integer.parseInt(myScanner.nextLine());
        gameMoney = startingBet;
        int maxMoney = gameMoney;
                
        
        Random diceRoller = new Random();
        
        while (gameMoney > 0){
            rolls++;
            int rollResult1 = diceRoller.nextInt(6) + 1;
            int rollResult2 = diceRoller.nextInt(6) + 1;
            int diceTotal = rollResult1 + rollResult2;
            
            if (diceTotal == 7){
                gameMoney = gameMoney + 4;
                
               
            }else{
                gameMoney = gameMoney - 1;
                
            }
           
            if (gameMoney > maxMoney){  //maxMoney and MaxRolls
            maxMoney = gameMoney;
            maxRolls = rolls;
         }
     
              
            }
        
                System.out.println("You should have quit after " + maxRolls + " rolls when you had $" + maxMoney);
               
   
   
               System.out.println("You are broke after " + rolls + " rolls.");
        }
        
        
    }

