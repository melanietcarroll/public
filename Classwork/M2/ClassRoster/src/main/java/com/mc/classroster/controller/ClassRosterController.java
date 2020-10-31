/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classroster.controller;

import com.mc.classroster.dao.ClassRosterDao;
import com.mc.classroster.dao.ClassRosterPersistenceException;
import com.mc.classroster.dto.Student;
import com.mc.classroster.service.ClassRosterDataValidationException;
import com.mc.classroster.service.ClassRosterDuplicateIdException;
import com.mc.classroster.service.ClassRosterServiceLayer;
import com.mc.classroster.ui.ClassRosterView;
import com.mc.classroster.ui.UserIO;
import com.mc.classroster.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class ClassRosterController {

private ClassRosterServiceLayer service;
private ClassRosterView view;
    
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
    this.service = service;
    this.view = view;
}

    public void run() {
    boolean keepGoing = true;
    int menuSelection = 0;
    try {
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    listStudents();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    viewStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    } catch (ClassRosterPersistenceException e) {
        view.displayErrorMessage(e.getMessage());
    }
}

    private int getMenuSelection() {
    return view.printMenuAndGetSelection();
}

private void createStudent() throws ClassRosterPersistenceException {
    view.displayCreateStudentBanner();
    boolean hasErrors = false;
    do {
        Student currentStudent = view.getNewStudentInfo();
        try {
            service.createStudent(currentStudent);
            view.displayCreateSuccessBanner();
            hasErrors = false;
        } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
    } while (hasErrors);
}

private void listStudents() throws ClassRosterPersistenceException {
    List<Student> studentList = service.getAllStudents();
     
    view.displayStudentList(studentList);
}

private void viewStudent() throws ClassRosterPersistenceException {
     String studentId = view.getStudentIdChoice();
     Student student = service.getStudent(studentId) ;
     view.displayStudent(student);
}

private void removeStudent() throws ClassRosterPersistenceException {
    view.displayRemoveStudentBanner();
    String studentId = view.getStudentIdChoice();
    service.removeStudent(studentId);
    view.displayRemoveSuccessBanner();
}

private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}
}
