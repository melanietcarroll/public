/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestinglogica;

import static com.mc.unittestinglogica.LogicExerciseA.friendlyGreeting;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class LogicExerciseATest {
    
    public LogicExerciseATest() {
    }

    @Test
    public void testShyGreeting() {
        String visitorName = "Goofus"; //assemble
        boolean isFriend = false;
        String greeting = friendlyGreeting(visitorName, isFriend); //act
        String expected = "hi"; //assert
        assertEquals(expected, greeting, "Expected hi");
    }
    @Test
    public void testFriendlyGreeting() {
        String visitorName = "Gallant";
        boolean isFriend = true;
        String greeting = friendlyGreeting(visitorName, isFriend);
        String expected = "Hello, Gallant!";
        assertEquals(expected, greeting, "Expected Hello, Gallant!");
}
    
    @Test
    public void testNullGreeting() {
        String visitorName = null;
        boolean isFriend = false;
        String greeting = friendlyGreeting(visitorName, isFriend);
        String expected = "...";
        assertEquals(expected, greeting, "...");
}
}