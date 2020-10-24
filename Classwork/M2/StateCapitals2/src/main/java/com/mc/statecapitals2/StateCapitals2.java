/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.statecapitals2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

/**
 *
 * @author Shawn
 */
public class StateCapitals2 {
    public static void main(String[] args) throws Exception {
        
    Map<String, String> capitals = new HashMap<>();
    Scanner myScanner = new Scanner(new BufferedReader(new FileReader("StateCapitals.txt")));
  
    while (myScanner.hasNextLine()){
      String currentLine = myScanner.nextLine();
      
   
      String[] values = currentLine.split("::"); 
      capitals.put(values[0],values[1]);   
     
  }
    int size = capitals.size();
        System.out.println(size + " STATES & CAPITALS ARE LOADED.");
       
        System.out.println("Here are the States: ");
       Set<String> keys = capitals.keySet();
       for (String k: keys){
        
           System.out.println(k);
       }
    
    
   
    for (String k: keys){
        System.out.println(k + " " + capitals.get(k));
    }
}
}