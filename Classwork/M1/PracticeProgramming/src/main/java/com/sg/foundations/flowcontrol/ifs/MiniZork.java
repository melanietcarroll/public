/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class MiniZork {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("The house has a boarded front door.");
        System.out.println("There is a golf course adjacent to the house");
        System.out.println("Go to the golf course or to the house?");
        
        String action = userInput.nextLine();
        
        if (action.equals("go to the golf course")){
            System.out.println("Perfect timing for a round of golf!");
        
        
        System.out.println("You see something rustling in the trees.");
        System.out.println("You turn and look back at the field.");
        System.out.println("Go back to the field or check the trees? ");
        
        action = userInput.nextLine();
        
        if (action.equals("check the trees")){
            System.out.println("You walk over to the trees.");
            System.out.println("It's really dark out here.");
            System.out.println("Keep going or go back to the field? ");
            action = userInput.nextLine();
            
            if (action.equals("keep going")){
                System.out.println("You peer look through the first trees.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.println("Run away or keep looking? ");
                action = userInput.nextLine();
                
                if (action.equals("keep looking")){
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                }else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you are alive. Possibly a wise choice.");
                }else if (action.equals("go back to the field")){
                    System.out.println("Smart choice!!!");}
            }else if (action.equals("go to the house")) {
                System.out.println("That's a sketchy choice, it's boarded up!");
            }
            
                    
                }
                }
            }
}
