/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsf;

import static com.mc.unittestingstringsf.StringsExerciseF.longestWord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class StringsExerciseFTest {
    
    public StringsExerciseFTest() {
    }

    @Test
    public void testLongestWord() {
        String aPhrase = "Invention my dear friends is 93% perspiration 6% electricity 4% evaporation and 2% butterscotch ripple";
        String longestWord = longestWord(aPhrase); //act
        String expected = "perspiration"; //assert
        assertEquals(expected, longestWord, "Expected perspiration");
    }
    
    @Test
    public void testNextLongestWord() {
        String aPhrase = "All well-established principles should be periodically challenged";
        String longestWord = longestWord(aPhrase); //act
        String expected = "well-established"; //assert
        assertEquals(expected, longestWord, "well-established");
    }
    
    @Test
    public void testLastLongestWord() {
        String aPhrase = "Never argue with the data";
        String longestWord = longestWord(aPhrase); //act
        String expected = "Never"; //assert
        assertEquals(expected, longestWord, "Never");
    }
}
