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
public class KnockKnock {
     public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        System.out.print("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();

        if(nameGuess.equals("Marty McFly")){
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future."); // Sorry, had to!
        }else{
            System.out.println("Dude, do I -look- like " + nameGuess);
        }


//use == for reference comparison (memory address comparison)
//use .equals() method for content comparison even if they are not the same instance
//if you change .equals() to == you will be checking if both objects point to the same memory location
//boolean equals (Object and Object) only returns TRUE if the argument is a String Object that represents
//the same sequence of characters as this Object
//if you don't type the right capitalization it will return FALSE!
//you can fix this by using boolean equalsIgnoreCase(String another String)
//returns true if and only if the argument is a string object taht represents the same sequence of
//characters as this object, ignoring the case
     }
}