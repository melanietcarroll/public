/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarraysb;

import java.util.Arrays;

/**
 *
 * @author Shawn
 */
public class ArrayExerciseB {
    /**
     * Given a integer and an array of ints, times each number in the array by the multiplier.
     *
     * Example Results:
     * multiplyAll( 5 , [ 1 , 2 , 3 , 4 , 5 ] ) ->  [ 5 , 10 , 15 , 20 , 25 ]
     * multiplyAll( 0 , [ 1 , 1 , 1 , 1 , 1  , 1 , 1 , 1 , 1 ] ) ->  [ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ]
     * multiplyAll( -1 , [ -2 , 0 , 0 , 1 ] ) ->  [ 2 , 0 , 0 , -1 ]
     * 
     * @param multiplier
     * @param numbers
     * @return int[] numbers multiplied
     */

    public static int[] multiplyAll(int multiplier, int[] numbers){
        int [] multipliedArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++){
            multipliedArray[i] = numbers[i] * multiplier;
        }
        return multipliedArray;
    }
     
}
