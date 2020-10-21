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
class Triangle extends Shape {
    protected float area;
    protected float perimeter;

    @Override
    public float getArea() {
        float height = 10;
        float base = 3;
        float area = (height * base ) / 2;
        return area;
    }

    @Override
    public float getPerimeter() {
        float a = 3;
        float b = 5; 
        float c = 8;
        float perimeter = a + b + c;
        return perimeter;
    }

    
    
}
