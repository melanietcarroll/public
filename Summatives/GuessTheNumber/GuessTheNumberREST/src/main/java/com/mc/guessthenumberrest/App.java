/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.guessthenumberrest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Melanie Carroll
 */
//@SpringBootApplication
//public class App {
//
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//    }

public class App {

    public static void main(String[] args) {

//        Random gen = new Random();
        String target = createAnswer();
//        while (hasDupes(target = (gen.nextInt(9000) + 1000)));
//        String targetStr = target + "";
        boolean guessed = false;
        Scanner myScanner = new Scanner(System.in);
        int guesses = 0;
        do {
            int bulls = 0;
            int cows = 0;

            System.out.println("Please enter a 4-digit number with no repeating digits: ");
            int guess;
            try {
                guess = myScanner.nextInt();
                if (hasDuplicateDigits(guess) || guess < 1000) {
                    continue;
                }
            } catch (InputMismatchException e) {
                continue;
            }
            guesses++;
            String guessString = Integer.toString(guess);
            for (int i = 0; i < 4; i++) {
                if (guessString.charAt(i) == target.charAt(i)) {
                    bulls++;
                } else if (target.contains(guessString.charAt(i) + "")) {
                    cows++;
                }
            }
            if (bulls == 4) {
                guessed = true;
            } else {
                System.out.println(cows + " Cows and " + bulls + " Bulls.");
            }
        } while (!guessed);
        System.out.println("You won after " + guesses + " guesses!");
    }

    public static boolean hasDuplicateDigits(int num) {
        boolean[] numbers = new boolean[10];
        while (num > 0) {
            if (numbers[num % 10]) {
                return true;
            }
            numbers[num % 10] = true;
            num /= 10;
        }
        return false;
    }

    public static String createAnswer() {
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numberList.add(i);
        }

        Collections.shuffle(numberList);

        String result = "";
        for (int i = 0; i < 4; i++) {
            result += numberList.get(i).toString();
        }
        return result;
    }
}
