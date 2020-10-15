
package com.mc.healthyhearts;
import java.util.Scanner;
/**
 * created 10/13/20
 * @author Melanie Carroll
 */
public class HealthyHearts {
    public static void main(String[] args) {
        
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("What is your age?");
        
        int age = Integer.parseInt(myScanner.nextLine());
        int max = 220 - age;
        
        double doubleMax = max;
        double doubleTargetHigh = doubleMax * 0.85;
        double doubleTargetLow = doubleMax * 0.5;
        
        int targetLow = (int) doubleTargetLow;
        int targetHigh = (int) doubleTargetHigh;
        
        System.out.println("Your maximum heart rate should be " + max + " beats per minute.");
        System.out.println("Your target HR Zone is " + targetLow + " - " + targetHigh + " beats per minute.");
        
    }
}
