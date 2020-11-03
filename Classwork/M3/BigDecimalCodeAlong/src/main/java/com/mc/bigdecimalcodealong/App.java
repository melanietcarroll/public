/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.bigdecimalcodealong;

import static com.mc.bigdecimalcodealong.MathOperator.DIVIDE;
import static com.mc.bigdecimalcodealong.MathOperator.MINUS;
import static com.mc.bigdecimalcodealong.MathOperator.MULTIPLY;
import static com.mc.bigdecimalcodealong.MathOperator.PLUS;
import java.math.BigDecimal;

/**
 *
 * @author Shawn
 */
public class App {
    public static void main(String[] args) {
        BigDecimalMath myMath = new BigDecimalMath();
        
        BigDecimal op1 = new BigDecimal("10");
        BigDecimal op2 = new BigDecimal("3");
        System.out.println(myMath.calculate(PLUS, op1, op2));
        System.out.println(myMath.calculate(MINUS, op1, op2));
        System.out.println(myMath.calculate(MULTIPLY, op1, op2));
        System.out.println(myMath.calculate(DIVIDE, op1, op2));
    }
}
