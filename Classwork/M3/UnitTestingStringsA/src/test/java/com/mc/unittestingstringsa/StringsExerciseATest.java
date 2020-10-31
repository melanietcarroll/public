/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsa;

import static com.mc.unittestingstringsa.StringsExerciseA.yell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class StringsExerciseATest {
    
    public StringsExerciseATest() {
    }

    @Test
    public void testYell() {
        String word = "Hello there."; //arrange
        String loud = yell(word); //act
        String expected = "HELLO THERE."; //assert
        assertEquals(expected, loud, "Expected HELLO THERE.");
    }
    @Test
    public void testNextYell(){
        String word = "Shhhh"; //arrange
        String loud = yell(word); //act
        String expected = "SHHHH"; //assert
        assertEquals(expected, loud, "Expected SHHHH");
    }
    @Test
    public void testLastYell(){
        String word = "AAaahHhH"; //arrange
        String loud = yell(word); //act
        String expected = "AAAAHHHH"; //assert
        assertEquals(expected, loud, "Expected AAAAHHHH");
    }
}
