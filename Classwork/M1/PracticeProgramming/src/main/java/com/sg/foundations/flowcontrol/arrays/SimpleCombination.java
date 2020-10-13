/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;
import java.util.Arrays;
/**
 *
 * @author Shawn
 */
public class SimpleCombination {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 35, 45, 47, 49}; // 12 numbers
        int[] secondHalf = {51, 54, 68, 71, 75, 78, 82, 84, 85, 89, 91, 100}; // also 12!
        int count=0;

        int[] wholeNumbers = new int[24];

        // Combining code should go here!
        int[] newArray = new int[firstHalf.length + secondHalf.length];
        for (int i = 0; i < firstHalf.length; i++){
            newArray[i] = firstHalf[i];
            count++;
            
        }
        
      for (int j = 0; j < secondHalf.length; j++){
          newArray[count++] = secondHalf[j];
      
    }
       
        // Printing code should go here
for(int i=0;i < newArray.length;i++)
        System.out.print(newArray[i]+" ");
    }
}
