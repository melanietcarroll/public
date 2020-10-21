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
class Rectangle extends Shape{
    protected float area;
    protected float perimeter;
@Override
    public float getArea() {
        float length = 10;
        float width = 5;
        float area = length * width;
        return area;
    }
@Override
    public float getPerimeter() {
        float length = 10;
        float width = 5; 
        float perimeter = (length * width) * 2;
        return perimeter;
    }

    
}
