/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarraysb;

import static com.mc.unittestingarraysb.ArrayExerciseB.multiplyAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class ArrayExerciseBTest {
    
    public ArrayExerciseBTest() {
    }

    @Test
    public void testMultiplyAll() {
        int multiplier = 5;
        int [] numbers = {1,2,3,4,5};
        int[] multiply = multiplyAll(multiplier, numbers);
        int[]expected = {5, 10, 15, 20, 25};
        assertEquals(multiply, expected, "Expected 5,10,15,20,25");
    }
    
}
