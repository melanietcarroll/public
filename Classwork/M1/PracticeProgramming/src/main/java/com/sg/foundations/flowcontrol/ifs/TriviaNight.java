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
public class TriviaNight {
    public static void main(String[] args) {
        int score = 0;
        
        int answer1;
        
        int answer2;
        
        int answer3;
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.println("1) Source Code		2) Assembly Language");
        System.out.println("3) C#                       4) Machine Code");
        answer1 = Integer.parseInt(myScanner.nextLine());
        
        if (answer1 == 4){
            score ++;
            System.out.println("Correct! Your current score is: " + score + ". Only 2 questions to go!");
        }else {
            score = score + 0;
            System.out.println("Sorry! Wrong answer! Your current score is: " + score + ". Only 2 questions to go!");
        }
        System.out.println("Your answer: " + answer1);
        
        System.out.println("SECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper		2) Alan Turing");
        System.out.println("3) Charles Babbage		4) Larry Page");
        answer2 = Integer.parseInt(myScanner.nextLine());
        
        if (answer2 == 2){
            score ++;
            System.out.println("Correct! Your current score is: " + score + ". Only 1 question to go!");
        }else{
            score = score + 0;
            System.out.println("Sorry! Wrong answer! Your current score is: " + score + ". Only 1 question to go!");
        }
        System.out.println("Your answer: " + answer2);
        
        System.out.println("LAST QUESTION!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity			2) The Battlestar Galactica");
        System.out.println("3) The USS Enterprise	4) The Millennium Falcon");
        answer3 = Integer.parseInt(myScanner.nextLine());
        
        if (answer3 == 3){
            score ++;
            System.out.println("Correct! Your current score is: " + score + ".");
        }else{
            score = score + 0;
            System.out.println("Sorry! Wrong answer! Your current score is: " + score + ".");
        }
        System.out.println("Your answer: " + answer3);
        
        if (score == 3){
            System.out.println("Nice job - you got " + score + " correct!");
        }else if (score ==2){
            System.out.println("Pretty good - you got " + score + " correct!");
        }else{
            System.out.println("Try again to improve your score.");
            System.out.println("You only got " + score + " correct.");
        }
        
                }        
}
