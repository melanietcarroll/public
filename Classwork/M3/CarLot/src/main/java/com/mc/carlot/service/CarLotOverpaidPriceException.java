/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.carlot.service;

/**
 *
 * @author Shawn
 */
public class CarLotOverpaidPriceException extends Exception {

    public CarLotOverpaidPriceException(String message) {
        super(message);
    }

    public CarLotOverpaidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

}
