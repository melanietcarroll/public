/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.birthdaycalculator;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 *
 * @author Shawn
 */
public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        
        System.out.println("Welcome to the Magical BirthDAY Calculator");
        
        System.out.println("What's your birthday (mm/dd/yyyy)");
        String birthday = myScanner.nextLine();
        LocalDate bDay= LocalDate.parse(birthday, DateTimeFormatter.ofPattern ("MM/dd/yyyy"));
        
        System.out.println("That means you were born on a " + bDay.getDayOfWeek()+ "!");
        
        
        int ld =LocalDate.now().getYear();
        int bDayYear = bDay.getYear();
        int yearsDiff = (ld - bDayYear) + 1;
        
        String formatted = today.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        LocalDate birthdayDiff = bDay.plusYears(yearsDiff);
        
        long noOfDaysBetween = ChronoUnit.DAYS.between(today, birthdayDiff);
        System.out.println("Today is " + formatted + " and there are " + noOfDaysBetween + " days until your birthday.");
        
        System.out.println("Your birthday will fall on a " + today.plusDays(noOfDaysBetween).getDayOfWeek());
      
        System.out.println("Bet yer excited to be turning " + yearsDiff);
       
    }
}
