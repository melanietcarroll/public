/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestinglogice;

/**
 *
 * @author Shawn
 */
public class LogicExerciseE {

     /**
     * Given the following chart, return the correct color designation 
     * given measured wavelength, frequency and photonic energy. 
     * If it doesn't fall within correct bands, return "Unknown" instead. 
     * If it falls exactly within a band transition, 
     * return a compound color, with the longer wavelength color first. 
     * 
     * 	Color	Wavelength	Frequency	Photon energy
     * 	Violet	380–450 nm	668–789 THz	2.75–3.26 eV
     * 	Blue	450–495 nm	606–668 THz	2.50–2.75 eV
     * 	Green	495–570 nm	526–606 THz	2.17–2.50 eV
     * 	Yellow	570–590 nm	508–526 THz	2.10–2.17 eV
     * 	Orange	590–620 nm	484–508 THz	2.00–2.10 eV
     * 	Red	620–750 nm	400–484 THz	1.65–2.00 eV
     *
     * Ex:
     * whatColor( 575, 510, 2.15 ) ->  "Yellow"
     * whatColor( 449, 670, 3.00 ) ->  "Violet"
     * whatColor( 621, 475, 16.5 ) ->  "Unknown"
     * whatColor( 590, 508, 2.05 ) ->  "Orange"
     * whatColor( 570, 526, 2.17 ) ->  "Yellow-Green"
     * @param waveLengthNM
     * @param frequencyTHZ
     * @param photonicEnergyEV
     * @return String color
     */
    public static String whatColor(int waveLengthNM, int frequencyTHZ, double photonicEnergyEV) {

        if ((waveLengthNM >= 380 && waveLengthNM <= 450) && (frequencyTHZ >= 668 && frequencyTHZ < 789) && (photonicEnergyEV >= 2.75 && photonicEnergyEV <= 3.26)) {
            String color = "violet";
            System.out.println(color);
        }
        if (waveLengthNM == 450 && frequencyTHZ == 606 && photonicEnergyEV == 2.75) {
            String color = "violet-blue";
            return color;
        }

        if ((waveLengthNM >= 450 && waveLengthNM <= 495) && (frequencyTHZ >= 606 && frequencyTHZ <= 668) && (photonicEnergyEV >= 2.5 && photonicEnergyEV <= 2.75)) {
            String color = "blue";
            return color;
        }
        if ((waveLengthNM >= 495 && waveLengthNM <= 570) && (frequencyTHZ >= 526 && frequencyTHZ <= 606) && (photonicEnergyEV >= 2.17 && photonicEnergyEV <= 2.5)) {
            String color = "green";
            return color;
        }
        if (waveLengthNM == 495 && frequencyTHZ == 606 && photonicEnergyEV == 2.5) {
            String color = "green-blue";
            return color;
        }
        if ((waveLengthNM >= 570 && waveLengthNM <= 590) && (frequencyTHZ >= 508 && frequencyTHZ <= 526) && (photonicEnergyEV >= 2.10 && photonicEnergyEV <= 2.17)) {
            String color = "yellow";
            return color;
        }
        if (waveLengthNM == 570 && frequencyTHZ == 526 && photonicEnergyEV == 2.17) {
            String color = "yellow-green";
            return color;
        }
        if ((waveLengthNM >= 590 && waveLengthNM <= 620) && (frequencyTHZ >= 484 && frequencyTHZ <= 508) && (photonicEnergyEV >= 2.0 && photonicEnergyEV <= 2.10)) {
            String color = "orange";
            return color;
        }
        if (waveLengthNM == 590 && frequencyTHZ == 508 && photonicEnergyEV == 2.10) {
            String color = "orange-yellow";
            return color;
        }
        if ((waveLengthNM >= 620 && waveLengthNM <= 750) && (frequencyTHZ >= 400 && frequencyTHZ <= 484) && (photonicEnergyEV >= 1.65 && photonicEnergyEV <= 2.0)) {
            String color = "red";
            return color;
        }
        if (waveLengthNM == 620 && frequencyTHZ == 484 && photonicEnergyEV == 2.0) {
            String color = "red-orange";
            return color;
        }
        String color = "Unknown";
        return color;

    }
}
