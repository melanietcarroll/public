/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingarrayse;

/**
 *
 * @author Shawn
 */
public class ArrayExerciseE {
    /**
     * Given an array of words turn it into a single camelCased phrase.
     * Lower case the first word, capitalize the first letter (but only the first) of the rest.
     *
     * camelCaseIt( {"llama", "llama", "duck"}  ) ->  "llamaLlamaDuck"
     * camelCaseIt( {"lambs", "eat", "oats", "and", "does", "eat", "oats"}  ) ->  "lambsEatOatsAndDoesEatOats"
     * camelCaseIt( {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"}  ) ->  "doOrDoNotThereIsNoTry"
     * @param words
     * @return String camelCased phrase
     */

    public static String camelCaseIt(String[] words){
        String removeSpace = "";

    for (int i = 0; i < words.length; i++) {
        if (i == 0){
        removeSpace = removeSpace + words[i].toLowerCase();
        i++;
    }
        removeSpace = removeSpace + words[i].substring(0,1).toUpperCase() + words[i].substring(1).toLowerCase();

    }
        return removeSpace;
    
    }
}
