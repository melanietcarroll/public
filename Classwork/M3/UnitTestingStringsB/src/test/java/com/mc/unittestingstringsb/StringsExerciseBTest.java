/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class StringsExerciseBTest {
    
    public StringsExerciseBTest() {
    }

    @Test
    public void testTripleIt() {
        String theString = "Llama"; //arrange
        String tripleLow = theString.toLowerCase();
        String tripleUpper = theString.toUpperCase();
        String result = (tripleLow + tripleUpper + tripleLow); //assert
        String expected = "llamaLLAMAllama";
        assertEquals(expected, result, "Expected llamaLLAMAllama");
    }
    @Test
    public void testAnotherTripleIt() {
        String theString = "ha"; //arrange
        String tripleLow = theString.toLowerCase();
        String tripleUpper = theString.toUpperCase();
        String result = (tripleLow + tripleUpper + tripleLow); //assert
        String expected = "haHAha";
        assertEquals(expected, result, "Expected haHAha");
    }
    @Test
    public void testLastTripleIt() {
        String theString = "Beetlejuice"; //arrange
        String tripleLow = theString.toLowerCase();
        String tripleUpper = theString.toUpperCase();
        String result = (tripleLow + tripleUpper + tripleLow); //assert
        String expected = "beetlejuiceBEETLEJUICEbeetlejuice";
        assertEquals(expected, result, "Expected beetlejuiceBEETLEJUICEbeetlejuice");
    }
}
