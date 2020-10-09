/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.basics;
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class MiniMadLibs {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
        String noun1;
        String adjective1;
        String noun2;
        double number;
        String adjective2;
        String pluralNoun1;
        String pluralNoun2;
        String pluralNoun3;
        String presentVerb;
        String pastVerb;
        
        
        System.out.println("Enter a noun.");
        noun1 = myScanner.nextLine();
        System.out.println("Enter an adjective.");
        adjective1 = myScanner.nextLine();
        System.out.println("Enter another noun.");
        noun2 = myScanner.nextLine();
        System.out.println("Enter a number.");
        number = Double.parseDouble(myScanner.nextLine());
        System.out.println("Enter another adjective.");
        adjective2 = myScanner.nextLine();
        System.out.println("Enter a plural noun.");
        pluralNoun1 = myScanner.nextLine();
        System.out.println("Enter another plural noun.");
        pluralNoun2 = myScanner.nextLine();
        System.out.println("Enter another plural noun.");
        pluralNoun3 = myScanner.nextLine();
        System.out.println("Enter a present tense verb.");
        presentVerb = myScanner.nextLine();
        System.out.println("Enter the SAME verb but in PAST tense.");
        pastVerb = myScanner.nextLine();
        
        
        System.out.println(noun1 + ": the " + adjective1 + " frontier. These are the voyages of the starship " + noun2 +". Its " + number + "-year mission: to explore strange "+ adjective2 + " " + pluralNoun1 + ", to seek out " + adjective2 + " " + pluralNoun2 + " and " + adjective2 + " " + pluralNoun3 + ", to boldly " + presentVerb + " where no one has " + pastVerb + " before.");
    }
}
