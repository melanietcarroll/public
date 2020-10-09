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
public class GuessMe {
    public static void main(String[] args) {
        int guess;
        int answer = 23;
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        guess = myScanner.nextInt();
        System.out.println("Your guess: " + guess);
        if (guess == answer){
            System.out.println("Wow, nice guess! That was it!");
        }else if (guess < answer){
            System.out.println("Ha, nice try - too low! I chose " + answer);
        }else if (guess > answer){
            System.out.println("Too bad, way too high. I chose " + answer);
        }
        
        
        
    }
}
