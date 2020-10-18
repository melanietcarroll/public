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
public class Factorizer {
   
        
        public void factorizer(){
        Scanner myScanner = new Scanner(System.in);
        int factors = 0;
        int sum = 0;
        
        System.out.println("What number would you like to factor?");
        int number = Integer.parseInt(myScanner.nextLine());
        System.out.println("The factors of " + number + " are:");
        
       for (int i = 1; i <= number; i++){
           
                   if (number % i == 0){
                       sum = sum + i;
                       factors++;
                       System.out.print(i + " ");
                      
                   }
       }
                   
        System.out.println("\n" + number + " has " + factors + " factors.");
        
        if ((sum - number) == number){
                       System.out.println("\n" + number + " is a perfect number.");
                   }else{
                       System.out.println("\n" + number + " is not a perfect number.");
                   }
       
        
       boolean isPrime = false;
        for(int k = 2; k <= number/2; ++k)
        {
            
            if(number % k == 0)
            {
                isPrime = true;
                break;
            }
        }

        if (!isPrime)
            System.out.println("\n" + number + " is a prime number.");
        else
            System.out.println( "\n" + number + " is not a prime number.");
    }
}    
    
        

