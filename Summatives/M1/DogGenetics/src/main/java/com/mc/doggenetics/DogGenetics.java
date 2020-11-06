
package com.mc.doggenetics;
/**
 * created 10/13/20
 * @author Melanie Carroll
 */
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DogGenetics {
    public static void main(String[] args) {
//        Scanner myScanner = new Scanner(System.in);
//        Random randomInt = new Random();
//        
//        String dog1 = "Pug";
//        String dog2 = "Chihuahua";
//        String dog3 = "Dachshund";
//        String dog4 = "Poodle";
//        String dog5 = "Shar Pei";
//        
//        int num1;
//        int num2;
//        int num3;
//        int num4;
//        int num5;
//        
//        System.out.println("What is your dog's name?");
//        String dogName = myScanner.nextLine();
//        System.out.println(dogName + " is: ");
//        System.out.println(" ");
//        
//        num1 = randomInt.nextInt(100) + 1;
//        num2 = randomInt.nextInt(100 - num1) + 1;
//        num3 = randomInt.nextInt(100 - num1 - num2) + 1;
//        num4 = randomInt.nextInt(100 - num1 - num2 - num3) + 1;
//        num5 = (100 - num1 - num2 - num3 - num4);
//        
//        System.out.println(dog1 + " " + num1);
//        System.out.println(dog2 + " " + num2);
//        System.out.println(dog3 + " " + num3);
//        System.out.println(dog4 + " " + num4);
//        System.out.println(dog5 + " " +num5);
//                
//        System.out.println(" ");
//        System.out.println("WOW! That's quite the dog!");
//
//
//    }

//String releaseDate = "2000";
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
//        LocalDate date = LocalDate.parse(releaseDate, formatter);
//        System.out.print(date);
List<String> dvdList = new Arrays.asList("ab", "cd", "ef");
        List<String> sortedByDate = dvdList.stream()
    	.filter((d) -> d.getReleaseDate() == date)
    	.collect(Collectors.toList());
        return sortedByDate;
 
       
}
