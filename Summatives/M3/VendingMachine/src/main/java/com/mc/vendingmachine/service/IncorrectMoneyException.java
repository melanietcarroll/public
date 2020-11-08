/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

/**
 *
 * @author Shawn
 */
public class IncorrectMoneyException extends Exception {
    public IncorrectMoneyException(String message) {
        super(message);
    }

    public IncorrectMoneyException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
