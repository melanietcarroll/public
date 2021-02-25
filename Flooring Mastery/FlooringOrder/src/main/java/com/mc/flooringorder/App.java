/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder;

import com.mc.flooringorder.controller.FlooringOrderController;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;
import com.mc.flooringorder.service.FlooringOrderNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class App {

    public static void main(String[] args) throws FlooringOrderPersistenceException, FlooringOrderNotFoundException {
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringOrderView myView = new FlooringOrderView(myIo);
//        FlooringOrderDao myDao = new FlooringOrderDaoFileImpl();
//        FlooringOrderAuditDao myAuditDao = new FlooringOrderAuditDaoFileImpl();
//        FlooringOrderServiceLayer myService = new FlooringOrderServiceLayerImpl(myDao, myAuditDao);
//        FlooringOrderController controller = new FlooringOrderController(myService, myView);
//        controller.run();
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringOrderController controller
                = ctx.getBean("controller", FlooringOrderController.class);
        controller.run();
    }
}
