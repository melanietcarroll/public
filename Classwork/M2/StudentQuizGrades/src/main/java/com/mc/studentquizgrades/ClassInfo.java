/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.studentquizgrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Shawn
 */
public class ClassInfo {
    List<String> studentRoster = new ArrayList<>();
    
    Map<String, ArrayList<Integer>> quizScores = new HashMap<>();

    public List<String> getStudentRoster() {
        return studentRoster;
    }

    public Map<String, ArrayList<Integer>> getQuizScores() {
        return quizScores;
    }

    public void setStudentRoster(List<String> studentRoster) {
        this.studentRoster = studentRoster;
    }
    
//    public int calcStudentAvgQuizScore (){
//        
//        
//    }

    public void setQuizScores(Map<String, ArrayList<Integer>> quizScores) {
        this.quizScores = quizScores;
    }
    
    
}
