/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestingstringsf;

/**
 *
 * @author Shawn
 */
public class StringsExerciseF {
  /**
     * Take in a phrase that has many words separated by spaces. 
     * Return the longest word that you can find within the phrase - 
     * don't forget, hyphenated words are allowed!
     *
     * If there is a tie, return the first instance of a word of that size.
     *
     * Ex:
     * longestWord( "Invention my dear friends is 93% perspiration 6% electricity 4% evaporation and 2% butterscotch ripple" ) ->  "perspiration"
     * longestWord( "All well-established principles should be periodically challenged" ) ->  "well-established"
     * longestWord( "Never argue with the data" ) ->  "Never"
     * 
     * @param aPhrase
     * @return String word
     */
    public static String longestWord(String aPhrase){
       String [] word = aPhrase.split(" ");
       String maxWord = "";
       for (int i = 0; i < word.length; i++){
           if (word[i].length() > maxWord.length()){
               maxWord = word[i];
           }
          
           }
       return maxWord;
    }  
}
