/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dao;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public class FlooringOrderPersistenceException extends Exception{
    
    public FlooringOrderPersistenceException(String message) {
        super(message);
    }
    
    public FlooringOrderPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
