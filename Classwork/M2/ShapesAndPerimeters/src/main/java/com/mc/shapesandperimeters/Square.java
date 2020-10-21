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
class Square extends Shape{
    protected float area;
    protected float perimeter;

@Override    
    public float getArea() {
        float length = 10;
        float area = length * length;
        return area;
    }
@Override
    public float getPerimeter() {
        float length = 10;
         float perimeter = length * 4;
        return perimeter;
    }
    
    
        
    }
   
}
