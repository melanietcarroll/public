/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsd;

import static com.mc.unittestingstringsd.StringsExerciseD.simpleReverse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class StringsExerciseDTest {
    
    public StringsExerciseDTest() {
    }

    @Test
    public void testSimpleReverse() {
        String phrase = "fun times"; //arrange
        String word = simpleReverse(phrase); //act
        String expected = "semit nuf"; //assert
        assertEquals(expected, word, "Expected semit nuf");
    }
    
    @Test
    public void testAnotherSimpleReverse() {
        String phrase = "llama llama duck"; //arrange
        String word = simpleReverse(phrase); //act
        String expected = "kcud amall amall"; //assert
        assertEquals(expected, word, "Expected kcud amall amall");
    }
    
     @Test
    public void testLastSimpleReverse() {
        String phrase = "hannah"; //arrange
        String word = simpleReverse(phrase); //act
        String expected = "hannah"; //assert
        assertEquals(expected, word, "Expected hannah");
    }
}
