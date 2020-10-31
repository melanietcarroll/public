/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classroster;

import com.mc.classroster.controller.ClassRosterController;
import com.mc.classroster.dao.ClassRosterAuditDao;
import com.mc.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.mc.classroster.dao.ClassRosterDao;
import com.mc.classroster.dao.ClassRosterDaoFileImpl;
import com.mc.classroster.service.ClassRosterServiceLayer;
import com.mc.classroster.service.ClassRosterServiceLayerImpl;
import com.mc.classroster.ui.ClassRosterView;
import com.mc.classroster.ui.UserIO;
import com.mc.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author Shawn
 */
public class App {
 
   public static void main(String[] args) {
    // Instantiate the UserIO implementation
    UserIO myIo = new UserIOConsoleImpl();
    // Instantiate the View and wire the UserIO implementation into it
    ClassRosterView myView = new ClassRosterView(myIo);
    // Instantiate the DAO
    ClassRosterDao myDao = new ClassRosterDaoFileImpl();
    // Instantiate the Audit DAO
    ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
    // Instantiate the Service Layer and wire the DAO and Audit DAO into it
    ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
    // Instantiate the Controller and wire the Service Layer into it
    ClassRosterController controller = new ClassRosterController(myService, myView);
    // Kick off the Controller
    controller.run();
}
}
