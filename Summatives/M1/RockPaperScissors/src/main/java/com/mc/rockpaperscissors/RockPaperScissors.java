package com.mc.rockpaperscissors;

import java.util.Scanner;
import java.util.Random;

/**
 * Created 10/15/20
 *
 * @author Melanie Carroll
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        int rounds = choosingRounds();
        if (rounds < 1 || rounds > 10) {
            System.out.println("Incorrect number of rounds!");
        } else {
            play(rounds);
        }

    }

    public static int choosingRounds() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("How many rounds do you want to Play? Please choose between 1-10.");
        int rounds = Integer.parseInt(myScanner.nextLine());
        return rounds;
    }

    public static void play(int rounds) {

        int roundTies = 0;
        int roundUserWins = 0;
        int roundComputerWins = 0;

        int scoreBoardTies = 0;
        int scoreBoardUserWins = 0;
        int scoreBoardComputerWins = 0;

        Scanner myScanner = new Scanner(System.in);
        Random computerInput = new Random();

        for (int i = 1; i <= rounds; i++) {

            System.out.println("Choose 1 for Rock, 2 for Paper, 3 for Scissors");
            int userChoice = Integer.parseInt(myScanner.nextLine());

            int computerChoice = computerInput.nextInt(3) + 1; // add 1 because we don't want zero

            if (userChoice == computerChoice) {
                roundTies++;
                scoreBoardTies = roundTies;

            } else if ((userChoice == 1 && computerChoice == 2) || (userChoice == 2 && computerChoice == 3) || (userChoice == 3 && computerChoice == 1)) { //rock vs paper
                roundComputerWins++;
                scoreBoardComputerWins = roundComputerWins;

            } else {
                roundUserWins++;
                scoreBoardUserWins = roundUserWins;

            }
            System.out.println(" ");  //each round totals
            System.out.println("Round " + i + " ; ");
            System.out.println("# of ties: " + roundTies);
            System.out.println("# of computer wins: " + roundComputerWins);
            System.out.println("# of user wins: " + roundUserWins);
            System.out.println(" ");
        }
        System.out.println("Final results:"); //scoreboard totals
        System.out.println("Total # of ties " + scoreBoardTies);
        System.out.println("Total # of compter wins " + scoreBoardComputerWins);
        System.out.println("Total # of user wins " + scoreBoardUserWins);

        if (roundUserWins > roundComputerWins) {
            System.out.println("Congratulations! You win!");
        }
        if (roundComputerWins > roundUserWins) {
            System.out.println("Sorry! You lost!");
        }
        playAgain();
    }

    public static void playAgain() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Would you like to play again? 1-yes or 2-no");
        int answer = Integer.parseInt(myScanner.nextLine());

        if (answer == 2) {
            System.out.println("Thank you for playing.");
            return;
        }
        int playAgainRounds = choosingRounds();

        if (playAgainRounds < 1 || playAgainRounds > 10) {
            System.out.println("Incorrect number of rounds!");
            return;
        }
        play(playAgainRounds);

    }
}
