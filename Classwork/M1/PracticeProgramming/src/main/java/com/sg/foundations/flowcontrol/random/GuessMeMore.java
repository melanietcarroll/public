/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Shawn
 */
public class GuessMeMore {
    public static void main(String[] args) {
        int guess;
        int answer;
        Scanner myScanner = new Scanner(System.in);
        Random random = new Random();
        
answer = random.nextInt(100-(-100)) + (-100);
       
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        guess = myScanner.nextInt();
        System.out.println("Your guess: " + guess);
        if (guess == answer){
            System.out.println("Wow, nice guess! That was it!");
        }else if (guess < answer){
            System.out.println("Ha, nice try - too low! Try again!");
        int guess2 = myScanner.nextInt();
            if (guess2 == answer){
                System.out.println("You got it! The answer was " + answer);
            }else if (guess2 < answer){
                System.out.println("Nice try - too low. The answer was " + answer);
            }else if (guess2 > answer){
                System.out.println("Nice try - too high. The answer was " + answer);
            }
        }else if (guess > answer){
            System.out.println("Too bad, way too high. Try again!");
            int guess3 = myScanner.nextInt();
            if (guess3 == answer){
                System.out.println("You got it! The answer was " + answer);
            }else if (guess3 < answer){
                System.out.println("Nice try - too low. The answer was " + answer);
            }else if (guess3 > answer){
                System.out.println("Nice try - too high. The answer was " + answer);
            }
        }
        
    }
        
}
