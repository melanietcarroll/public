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
class Circle extends Shape {

    protected float area;
    protected float perimeter;

    public float getArea() {
        float radius = 10;
        float area = (3.14f) * (radius * radius);
        return area;
    }

    public float getPerimeter() {
        float radius = 10;
        float perimeter = 2 * (3.14f) * radius;
        return perimeter;
    }

}
