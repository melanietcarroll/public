/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsc;

import static com.mc.unittestingstringsc.StringsExerciseC.removeTheVowels;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class StringsExerciseCTest {
    
    public StringsExerciseCTest() {
    }

    @Test
    public void testRemoveTheVowels() {
        String word = "truncate"; //arrange
        String resultString = removeTheVowels(word); //act
        String expected = "trnct"; //assert
        assertEquals(expected, resultString, "Expected trnct");
    }
    
    @Test
    public void testAnotherTheVowels() {
        String word = "ice"; //arrange
        String resultString = removeTheVowels(word); //act
        String expected = "c"; //assert
        assertEquals(expected, resultString, "Expected c");
    }
    @Test
    public void testAnotherOne() {
        String word = "Ahead"; //arrange
        String resultString = removeTheVowels(word); //act
        String expected = "hd"; //assert
        assertEquals(expected, resultString, "Expected hd");
    }
    @Test
    public void testLastOne() {
        String word = "Earn"; //arrange
        String resultString = removeTheVowels(word); //act
        String expected = "rn"; //assert
        assertEquals(expected, resultString, "Expected rn");
    }
}
