
package com.mc.doggenetics;
/**
 * created 10/13/20
 * @author Melanie Carroll
 */
import java.math.BigDecimal;

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
BigDecimal x = new BigDecimal("100");
BigDecimal percentDiscount = new BigDecimal("0.15");

BigDecimal newCarPrice = x.multiply(percentDiscount);
BigDecimal newCarTotal = x.subtract(newCarPrice);
        System.out.println(newCarTotal);

    }



 
       
}
