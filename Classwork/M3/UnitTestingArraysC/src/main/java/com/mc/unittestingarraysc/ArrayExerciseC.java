/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarraysc;

/**
 *
 * @author Shawn
 */
 
public class ArrayExerciseC {
    /**
     * Given an array of integers, return them all as a single continuous text value.
     * 
     * stringThemTogether( { 1, 3, 3, 7 }  ) ->  "1337"
     * stringThemTogether( { 1, 33, 555, 7777, 99999 } ) ->  "133555777799999"
     * stringThemTogether( { }  ) ->  ""
     * 
     * @param nums
     * @return String
     */
    public static String stringThemTogether(int[] nums){
      
    String numsToString = "";

    for (int i = 0; i < nums.length; i++) {
        numsToString = numsToString + nums[i];
    }

    return numsToString; 
}
}