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
public class GuessMeFinally {
    public static void main(String[] args) {
        int guess;
        int answer;
        int count = 0;
        Scanner myScanner = new Scanner(System.in);
        Random random = new Random();
        
answer = random.nextInt(100-(-100)) + (-100);
      
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        guess = myScanner.nextInt();
        
        while (guess != answer){
            if (guess < answer){
                System.out.println("Nice try - too low.");
            }else if (guess > answer){
                System.out.println("Nice try - too high.");
            }
            System.out.println("Try again!");
            guess = myScanner.nextInt();
            count ++;
        }
        
        System.out.println("Your guess: " + guess);
        if (guess == answer && count == 0){
            System.out.println("Wow, nice guess! That was it!");
        }else if (guess == answer && count > 1){
            System.out.println("Finally! It's about time you got it!");
        
        }
        
    }
        
}
