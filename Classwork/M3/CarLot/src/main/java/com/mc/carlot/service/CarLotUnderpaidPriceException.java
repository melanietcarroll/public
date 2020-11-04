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
public class CarLotUnderpaidPriceException extends Exception {

    public CarLotUnderpaidPriceException(String message) {
        super(message);
    }

    public CarLotUnderpaidPriceException(String message, Throwable cause) {
        super(message, cause);
    }

}
