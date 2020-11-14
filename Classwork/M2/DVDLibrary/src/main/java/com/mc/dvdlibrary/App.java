
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary;

import com.mc.dvdlibrary.controller.DVDLibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * created 10/25/20
 *
 * @author Melanie Carroll
 */
public class App {
    public static void main(String[] args) {
//    UserIO myIo = new UserIODVDConsoleImpl();
//    DVDLibraryView myView = new DVDLibraryView(myIo);
//    DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
//    DVDLibraryController controller =
//            new DVDLibraryController(myDao, myView);
//    controller.run();

    ApplicationContext ctx
            = new ClassPathXmlApplicationContext("applicationContext.xml");
    DVDLibraryController controller
            = ctx.getBean("controller", DVDLibraryController.class);
controller.run();
 }
}