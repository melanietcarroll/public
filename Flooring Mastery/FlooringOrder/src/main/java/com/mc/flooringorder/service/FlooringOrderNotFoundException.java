/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

/**
 *
 * @author Shawn
 */
public class FlooringOrderNotFoundException extends Exception{
    public FlooringOrderNotFoundException(String message) {
        super(message);
    }
    
    public FlooringOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
