/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
/**
 *
 * @author Shawn
 */
public class InterestCalculator {
    public static void main(String[] args) {
        interestCalculator();
    }
    public static void interestCalculator(){
       double total = 0;
        double earned;
             
        
       
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("How much do you want to invest?");
        double principal = Double.parseDouble(myScanner.nextLine());
        double currentBalance = principal;
        
        System.out.println("How many years are you investing for?");
        double years = Double.parseDouble(myScanner.nextLine());
        
        System.out.println("What is the annual interest rate % growth?");
        double interest = Double.parseDouble(myScanner.nextLine());
        
        
        System.out.println("Calculating....");
        
        
        for (int i = 1; i <= years; i++){
            earned = total - currentBalance;
          currentBalance = principal + earned; 
           
                total = currentBalance * (1 + ((interest/4) / 100));
                earned = total - currentBalance;
                System.out.println("Year " + i);
                System.out.println("Began with " + currentBalance);
                System.out.println("Earned " + earned);
                System.out.println("Ended with " + (currentBalance + total));
            
//            if (i == 1){
//                earned = initialPrincipal * (interestRate / 100);
//                total = initialPrincipal + earned;
//                
//                
//                System.out.println("Year " + i);
//                System.out.println("Earned " + earned);
//                System.out.println("Ended with " + total);
//                System.out.println(" ");
//            }else{
//                nextEarned = (initialPrincipal * (interestRate/100) + initialPrincipal) * (interestRate / 100);
//                nextTotal = nextEarned + (initialPrincipal * (interestRate/100) + initialPrincipal);
//                
//                System.out.println("Year " + i);
//                System.out.println("Began with " + (initialPrincipal * (interestRate/100) + initialPrincipal));
//                System.out.println("Earned " + nextEarned);
//                System.out.println("Ended with " + nextTotal);
//                System.out.println(" ");
//            }
        }
                
        
               

}
}

