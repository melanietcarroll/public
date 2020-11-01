/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarrayse;

import static com.mc.unittestingarrayse.ArrayExerciseE.camelCaseIt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class ArrayExerciseETest {
    
    public ArrayExerciseETest() {
    }

    @Test
    public void testCamelCase() {
        String [] words = {"llama", "llama", "duck"}; //arrange
        String allPutTogether = camelCaseIt(words); //act
        String expected = "llamaLlamaDuck"; //assert
        assertEquals(expected, allPutTogether, "Expected llamaLlamaDuck");
    }
    
    @Test
    public void testLambsCamelCase() {
        String [] words = {"lambs", "eat", "oats", "and", "does", "eat", "oats"}; //arrange
        String allPutTogether = camelCaseIt(words); //act
        String expected = "lambsEatOatsAndDoesEatOats"; //assert
        assertEquals(expected, allPutTogether, "Expected lambsEatOatsAndDoesEatOats");
    }
    @Test
    public void testCapsCamelCase() {
        String [] words = {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"}; //arrange
        String allPutTogether = camelCaseIt(words); //act
        String expected = "doOrDoNotThereIsNoTry"; //assert
        assertEquals(expected, allPutTogether, "Expected doOrDoNotThereIsNoTry");
    }
    
}
