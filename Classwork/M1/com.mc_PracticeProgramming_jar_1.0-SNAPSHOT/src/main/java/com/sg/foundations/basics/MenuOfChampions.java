/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.basics;

/**
 *
 * @author Shawn
 */
public class MenuOfChampions {
    public static void main(String[] args) {
        String peperoni= "Peperoni Calzone";
        String stuffedCrust = "Stuffed Crust Cheese Pizza";
        String bacon = "Bacon Calzone";
        float peperoniPrice = 7.99f;
        float stuffedCrustPrice = 10.99f;
        float baconPrice = 8.99f;
        
        System.out.println(" \n" +
".-\"-.     .-\"-.     .-\"-.     .-\"-.     .-\"-.     .-\"-.\n" +
"     \"-.-\"     \"-.-\"     \"-.-\"     \"-.-\"     \"-.-\"     \"-.-\"\n" +
"\n");
        System.out.println("Welcome to Crunch Calzones!");
        System.out.println("Today's menu is...");
        System.out.println(" \n" +
".-\"-.     .-\"-.     .-\"-.     .-\"-.     .-\"-.     .-\"-.\n" +
"     \"-.-\"     \"-.-\"     \"-.-\"     \"-.-\"     \"-.-\"     \"-.-\"\n" +
"\n");
        System.out.println(peperoni + "  $" + peperoniPrice);
        System.out.println(stuffedCrust + "  $" + stuffedCrustPrice);
        System.out.println(bacon +"  $" + baconPrice);
        
    }
}
