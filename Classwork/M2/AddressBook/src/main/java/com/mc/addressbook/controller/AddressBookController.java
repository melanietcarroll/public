/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.addressbook.controller;
import com.mc.addressbook.dao.AddressBookDao;
import com.mc.addressbook.ui.AddressBookView;
import com.mc.addressbook.dao.AddressBookDaoFileImpl;
import com.mc.addressbook.dao.AddressBookDaoException;
import java.util.List;
import com.mc.addressbook.dto.Address;
/**
 *
 * @author Shawn
 */
public class AddressBookController {

 private AddressBookView view;
    private AddressBookDao dao;
    
    public AddressBookController(AddressBookDao dao, AddressBookView view) {
    this.dao = dao;
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
                    addAddress();
                    break;
                case 2:
                    removeAddress();
                    break;
                case 3:
                    getAddress();
                    break;
                case 4:
                    countAddress();
                    break;
                case 5:
                    getAllAddresses();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    } catch (AddressBookDaoException e) {
        view.displayErrorMessage(e.getMessage());
    }
}
    private int getMenuSelection() {
    return view.printMenuAndGetSelection();
}
    private void addAddress() throws AddressBookDaoException {
    view.displayCreateAddressBanner();
    Address newAddress = view.getAddressInfo();
    dao.addAddress(newAddress.getLastName(), newAddress);
    view.displayCreateSuccessBanner();
}

private void getAllAddresses() throws AddressBookDaoException {
    view.displayDisplayAllBanner();
    List<Address> addressList = dao.getAllAddresses();
    view.displayAddressList(addressList);
}

private void getAddress() throws AddressBookDaoException {
    String addressId = view.getAddressByLastName();
    Address address = dao.getAddress(addressId);
    view.displayAddress(address);
}

private void removeAddress() throws AddressBookDaoException {
    view.displayRemoveAddressBanner();
    String addressId = view.getAddressByLastName();
    Address removedAddress = dao.removeAddress(addressId);
    view.displayRemoveResult(removedAddress);
}
private void countAddress() throws AddressBookDaoException {
    view.displayAddressCountBanner();
    List<Address> addressList = dao.getAllAddresses();
    view.displayAddressCount(addressList.size());
    
}

private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

private void exitMessage() {
    view.displayExitBanner();
}
}
