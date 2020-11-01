/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.unittestinglogica;

/**
 *
 * @author Shawn
 */
public class LogicExerciseA {
    /**
     * This is a shy method. 
     * 
     * It only likes to greet friends by name with a hearty hello, 
     * but strangers just get a simple quiet 'hi'. Given the name of who's visiting, 
     * and a boolean of whether or not they're a friend, return the proper greeting.
     * Keep in mind, you greet named visitors, but not the nameless!
     * 
     * friendlyGreeting( "Goofus" , false ) ->   "hi"
     * friendlyGreeting( "Gallant" , true ) ->   "Hello, Gallant!"
     * friendlyGreeting( null , false ) ->   "..."
     * 
     * @param visitorName
     * @param isFriend
     * @return String greeting
     */
    public static String friendlyGreeting(String visitorName, boolean isFriend) {
        if (isFriend != false){
            String friendGreeting = ("Hello, " + visitorName + "!");
            return friendGreeting;
        }
        if (visitorName == null){
            String friendGreeting = "...";
            return friendGreeting;
        }
        String friendGreeting = "hi";
        
        return friendGreeting;
    }
    
}
