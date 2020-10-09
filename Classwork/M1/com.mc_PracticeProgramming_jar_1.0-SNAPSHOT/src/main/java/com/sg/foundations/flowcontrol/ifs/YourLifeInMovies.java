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
public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String name;
        int born;
        
        System.out.println("Hey, let's play a game! What's your name?");
        name = myScanner.nextLine();
        
        System.out.println("Ok, " + name + ", when were you born?");
        born = myScanner.nextInt();
        
        System.out.println("Well " + name + ", " + "did you know that: ");
        
        if (born < 2005){
            System.out.println("Pixar's 'Up' came out over a decade ago.");
        }
        if (born < 1995){
            System.out.println("The first Harry Potter movie came out over 15 years ago.");
        }
        if (born < 1985){
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }
        if (born < 1975){
            System.out.println("The original Jurassic Park release is closer to the first lunar landing than it is to today.");
        }
        if (born < 1965){
            System.out.println("The MASH TV series has been around for almost half a century!");
        }
    }
    
}
