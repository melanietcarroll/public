/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.enummathoperators;

import com.mc.enummathoperators.MathOperators;
import static com.mc.enummathoperators.MathOperators.DIVIDE;
import static com.mc.enummathoperators.MathOperators.MINUS;
import static com.mc.enummathoperators.MathOperators.MULTIPLY;
import static com.mc.enummathoperators.MathOperators.PLUS;

import java.util.Scanner;

/**
 *
 * @author Shawn
 */
public class App {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        
            
        System.out.println("Enter an operand.");
        int operand1 = myScanner.nextInt();
        
        System.out.println("Enter a second operand.");
        int operand2 = myScanner.nextInt();
        
        int add = calculate(PLUS, operand1, operand2);
        System.out.println("Addition: " + add);
        
        int subtract = calculate(MINUS, operand1, operand2);
        System.out.println("Subtraction: " + subtract);
        
        int multiply = calculate(MULTIPLY, operand1, operand2);
        System.out.println("Multiplication: " + multiply);
        
        int divide = calculate(DIVIDE, operand1, operand2);
        System.out.println("Division: " + divide);
    }

    private static int calculate(MathOperators operator, int operand1, int operand2) {
        switch(operator) {
                case PLUS:
                      return operand1 + operand2;
                case MINUS:
                      return operand1 - operand2;
                case MULTIPLY:
                      return operand1 * operand2;
                case DIVIDE:
                      return operand1 / operand2;
                default:
                      throw new UnsupportedOperationException();
         }
   }
    }

