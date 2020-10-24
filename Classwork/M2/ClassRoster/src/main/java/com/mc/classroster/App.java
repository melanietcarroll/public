/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classroster;

import com.mc.classroster.controller.ClassRosterController;
import com.mc.classroster.dao.ClassRosterDao;
import com.mc.classroster.dao.ClassRosterDaoFileImpl;
import com.mc.classroster.ui.ClassRosterView;
import com.mc.classroster.ui.UserIO;
import com.mc.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author Shawn
 */
public class App {
 
    public static void main(String[] args) {
    UserIO myIo = new UserIOConsoleImpl();
    ClassRosterView myView = new ClassRosterView(myIo);
    ClassRosterDao myDao = new ClassRosterDaoFileImpl();
    ClassRosterController controller =
            new ClassRosterController(myDao, myView);
    controller.run();
}
}
