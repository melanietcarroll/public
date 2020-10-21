/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.shapesandperimeters;

/**
 *
 * @author Shawn
 */
abstract class Shape {
    String color;
    
    abstract float getArea();
    abstract float getPerimeter();
}
