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
public class ForByFor {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.print("|"); //column

            for (int j = 0; j < 1; j++) {  //row
                for (int k = 0; k < 3; k++) {
                    System.out.print("*");
                }
                System.out.print("|");
                    for (int l = 0; l < 1; l++){
                        for (int m = 0; m < 3; m++){
                        System.out.print("$");
                    }
                    }
                System.out.print("|");
                for (int n = 0; n < 1; n++){
                        for (int p = 0; p < 3; p++){
                        System.out.print("*");
                    }
                        System.out.print("|");
            }
            System.out.println("");
        }
        
    }
        for (int i = 0; i < 1; i++) {
            System.out.print("|"); //column

            for (int j = 0; j < 1; j++) {  //row
                for (int k = 0; k < 3; k++) {
                    System.out.print("@");
                }
                System.out.print("|");
                    for (int l = 0; l < 1; l++){
                        for (int m = 0; m < 3; m++){
                        System.out.print("#");
                    }
                    }
                System.out.print("|");
                for (int n = 0; n < 1; n++){
                        for (int p = 0; p < 3; p++){
                        System.out.print("@");
                    }
                        System.out.print("|");
            }
            System.out.println("");
        }
        
    }
        for (int i = 0; i < 1; i++) {
            System.out.print("|"); //column

            for (int j = 0; j < 1; j++) {  //row
                for (int k = 0; k < 3; k++) {
                    System.out.print("*");
                }
                System.out.print("|");
                    for (int l = 0; l < 1; l++){
                        for (int m = 0; m < 3; m++){
                        System.out.print("$");
                    }
                    }
                System.out.print("|");
                for (int n = 0; n < 1; n++){
                        for (int p = 0; p < 3; p++){
                        System.out.print("*");
                    }
                        System.out.print("|");
            }
            System.out.println("");
        }
        
    }
}
}
