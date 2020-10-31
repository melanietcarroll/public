/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarraysa;

import static com.mc.unittestingarraysa.ArrayExerciseA.maxOfArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class ArrayExerciseATest {
    
    public ArrayExerciseATest() {
    }

    @Test
    public void testMaxOne() {
        int[] numbers = {1}; //arrange
        int max = maxOfArray(numbers); //act
        int expected = 1; //assert
        assertEquals(expected, max, "Expected 1");
    }
    @Test
    public void testMaxThree() {
        int[] numbers = {3, 4, 5}; //arrange
        int max = maxOfArray(numbers); //act
        int expected = 5; //assert
        assertEquals(expected, max, "Expected 5");
    }
    @Test
    public void testMaxNegatives() {
        int[] numbers = {-9000, -700, -50, -3}; //arrange
        int max = maxOfArray(numbers); //act
        int expected = -3; //assert
        assertEquals(expected, max, "Expected -3");
    }
}
