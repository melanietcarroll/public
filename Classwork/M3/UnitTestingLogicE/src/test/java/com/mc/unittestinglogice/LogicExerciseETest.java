/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestinglogice;

import static com.mc.unittestinglogice.LogicExerciseE.whatColor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class LogicExerciseETest {
    
    public LogicExerciseETest() {
    }

    @Test
    public void testYellow() {
        int waveLengthNM = 575;
        int frequencyTHZ = 510;
        double photonicEnergyEV = 2.15;
        
        String color = whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        String expected = "yellow";
        assertEquals(expected, color, "Expected yellow");
    }
    @Test
    public void testViolet() {
        int waveLengthNM = 449;
        int frequencyTHZ = 670;
        double photonicEnergyEV = 3.00;
        
        String color = whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        String expected = "violet";
        assertEquals(expected, color, "Expected violet");
    }
    
    @Test
    public void testUknown() {
        int waveLengthNM = 621;
        int frequencyTHZ = 475;
        double photonicEnergyEV = 16.5;
        
        String color = whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        String expected = "Unknown";
        assertEquals(expected, color, "Expected Unknown");
    }
    
    @Test
    public void testOrange() {
        int waveLengthNM = 590;
        int frequencyTHZ = 508;
        double photonicEnergyEV = 2.05;
        
        String color = whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        String expected = "orange";
        assertEquals(expected, color, "Expected orange");
    }
    @Test
    public void testBlend() {
        int waveLengthNM = 570;
        int frequencyTHZ = 526;
        double photonicEnergyEV = 2.17;
        
        String color = whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        String expected = "yellow-green";
        assertEquals(expected, color, "Expected yellow-green");
    }
}
