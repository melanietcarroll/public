/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

/**
 *
 * @author Shawn
 */
public class FruitSalad {
   public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
        int apple = 0;
        int orange = 0;
        int berry = 0;
        int otherFruit = 0;
        int totalFruit;
        
        
        totalFruit = orange + apple + otherFruit;
        String[] fruitSalad = {"Gala Apple", "Granny Smith Apple", "Braeburn Apple", "Blood Orange", "Navel Orange", "Kiwi Fruit", "Gooseberry", "Strawberry", "Blueberry", "Raspberry", "Blackberry", "Snozzberry"};

        // Code Recipe for fruit salad should go here!
        for (int i = 0; i < fruit.length; i++){
            if (fruit[i] == "Gala Apple"){
                apple++;
                
            }else if (fruit[i] == "Granny Smith Apple"){
                apple++;
                
            }else if (fruit[i] == "Braeburn Apple"){
                apple++;
                
            }else if (fruit[i] == "Blood Orange"){
                orange++;
               
            }else if (fruit[i] == "Navel Orange"){
                orange++;
                
            }else if (fruit[i] == "Kiwi Fruit"){
                otherFruit++;
                
            }else if (fruit[i] == "Gooseberry"){
                otherFruit++;
                
            }else if (fruit[i] == "Blueberry"){
                otherFruit++;
             
            }else if (fruit[i] == "Strawberry"){
                otherFruit++;
               
            }else if (fruit[i] == "Raspberry"){
                otherFruit++;
                
            }else if (fruit[i] == "Blackberry"){
                otherFruit++;
                
            }else if (fruit[i] == "Snozzberry"){
                otherFruit++;
               
            }
            
        }
        
        
        System.out.println(otherFruit);
            System.out.println("Orange: " + orange);
            System.out.println("Apple: " + apple);
            System.out.println("Other Fruit: " + otherFruit);
            System.out.println("Total fruit: " + (apple + orange + otherFruit));
            
          
            
    }
}