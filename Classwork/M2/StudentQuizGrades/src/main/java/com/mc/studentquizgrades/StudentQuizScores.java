/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.studentquizgrades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class StudentQuizScores {
    public static void main(String[] args) {
        List<String> students = new ArrayList<>();
        students.add("Beatrice");
        students.add("Kelly");
        students.add("Mimi");
        students.add("Paul");
        
        List<Integer> beatrice = new ArrayList<>();
        beatrice.add(75);
        beatrice.add(80);
        beatrice.add(85);
                
        ClassInfo addStudent = new ClassInfo();
        addStudent.setStudentRoster(students);
        
        System.out.println(addStudent.getStudentRoster());
        
       
    }
}
