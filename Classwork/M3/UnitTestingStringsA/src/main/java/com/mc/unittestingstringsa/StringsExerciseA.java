/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsa;

/**
 *
 * @author Shawn
 */
public class StringsExerciseA {
 /**
     * Take a word, and change it so that it is a shout. 
     * If there are any quiet letters, make them LOUD!
     *
     * Ex:
     * yell( "Hello there." ) ->  "HELLO THERE."
     * yell( "shhhhhhhhhhhh" ) ->  SHHHHHHHHHHHH
     * yell( "AAaAAAaAAAaaAAHHHH" ) ->  "AAAAAAAAAAAAAAHHHH"
     * 
     * @param word
     * @return String yell
     */
    public static String yell(String word){
        return word.toUpperCase();
    }   
}
