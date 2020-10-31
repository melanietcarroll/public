/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.addressbook.ui;

import com.mc.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class AddressBookView {

    private UserIO io;

    public AddressBookView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Initial Menu:");
        io.print("1. Add Address");
        io.print("2. Delete Address");
        io.print("3. Find Address");
        io.print("4. List Address Count");
        io.print("5. List All Addresses");
        io.print("6. Exit");

        return io.readInt("Please select the operation you wish to perform:", 1, 6);
    }

    public Address getAddressInfo() {
        String firstName = io.readString("Please Enter First Name:");
        String lastName = io.readString("Please Enter Last Name:");
        String streetAddress = io.readString("Please Enter Street Address:");
        String city = io.readString("Please Enter City:");
        String state = io.readString("Please Enter State:");
        String zip = io.readString("Please Enter Zip Code:");
        Address currentAddress = new Address(lastName);
        currentAddress.setFirstName(firstName);
        currentAddress.setLastName(lastName);
        currentAddress.setStreetAddress(streetAddress);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZip(zip);
        return currentAddress;
    }

    public void displayCreateAddressBanner() {
        io.print("Add Address Menu:");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Address added.  Press 1 to go to Main Menu.");
    }

    public void displayAddressList(List<Address> addressList) {
        for (Address currentAddress : addressList) {
            String addressInfo = String.format("%s %s: \n %s  \n %s, %s, %s",
                    currentAddress.getFirstName(),
                    currentAddress.getLastName(),
                    currentAddress.getStreetAddress(),
                    currentAddress.getCity(),
                    currentAddress.getState(),
                    currentAddress.getZip());

            io.print(addressInfo);
        }
        io.readString("Press 1 to go to Main Menu.");
    }

    public void displayDisplayAllBanner() {
        io.print("List Addresses Menu:");
    }
    public void displayAddressCountBanner(){
    io.print("List Address Count Menu:");
    }
    public void displayAddressCount(int sum) {
        io.print("There are " + sum);
    }
    public void displayFindAddressBanner() {
        io.print("Find Address Menu:");
    }

    public String getAddressByLastName() {
        return io.readString("Please enter last name of address to find:");
    }

    public void displayAddress(Address address) {

        if (address != null) {
//            io.print(address.getLastName());
            io.print(address.getFirstName() + " " + address.getLastName());
            io.print(address.getStreetAddress());
            io.print(address.getCity()+ ", " + address.getState()+ ", " + address.getZip());
            io.print("");
        } else {
            io.print("No such address entry.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveAddressBanner() {
        io.print("Delete Address Menu:");
    }

    public void displayRemoveResult(Address address) {
        if (address != null) {
            io.print("Really Delete?");
        } else {
            io.print("No such address.");
        }
        io.readString("Address Deleted.  Press 1 to go to Main Menu.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
