/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.addressbook;

import com.mc.addressbook.controller.AddressBookController;
import com.mc.addressbook.dao.AddressBookDao;
import com.mc.addressbook.dao.AddressBookDaoFileImpl;
import com.mc.addressbook.ui.AddressBookView;
import com.mc.addressbook.ui.UserIO;
import com.mc.addressbook.ui.UserIOAddressConsoleImpl;

/**
 *
 * @author Shawn
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOAddressConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoFileImpl();
        AddressBookController controller
                = new AddressBookController(myDao, myView);
        controller.run();
    }
}
