/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.fors;

/**
 *
 * @author Shawn
 */
public class SpringForwardFallBack {
    public static void main(String[] args) {

        System.out.println("It's Spring...!");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ", ");
        }

        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) {
            System.out.print(i + ", ");
        }
    }
    //What are the start/stop ranges of output for both loops?
    //for the spring loop the start is 0 and the stop is 9
    //for the fall loop the start is 10 and the stop is 1
    //chance the stoping point on the spring loop to i <=10
}
