/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.simplecalculator;

/**
 *
 * @author Shawn
 */
public class SimpleCalculator {
    private int operand1;
    private int operand2;

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public int getOperand2() {
        return operand2;
    }
    
    public int add(int operand1, int operand2){
        return operand1 + operand2;
    }
    public int subtract(int operand1, int operand2){
        return operand1 - operand2;
    }
    public int multiply(int operand1, int operand2){
        return operand1 * operand2;
    }
    public int divide(int operand1, int operand2){
        return operand1 / operand2;
    }
}
