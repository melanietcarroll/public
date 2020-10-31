/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarraysc;
import static com.mc.unittestingarraysc.ArrayExerciseC.stringThemTogether;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class ArrayExerciseCTest {
    
    public ArrayExerciseCTest() {
    }

    @Test
    public void testJoin() {
        int [] nums = { 1, 3, 3, 7 };
        String numsAsString = stringThemTogether(nums);
        String expected = "1337";
        assertEquals(expected, numsAsString, "Expected 1337");
    }
    @Test
    public void testNextJoin() {
        int [] nums = { 1, 33, 555, 7777, 99999 };
        String numsAsString = stringThemTogether(nums);
        String expected = "133555777799999";
        assertEquals(expected, numsAsString, "Expected 133555777799999");
    }
    @Test
    public void testEmptyJoin() {
        int [] nums = { };
        String numsAsString = stringThemTogether(nums);
        String expected = "";
        assertEquals(expected, numsAsString, "Expected ");
    }
}
