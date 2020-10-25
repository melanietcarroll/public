/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.addressbook.dao;
import com.mc.addressbook.dto.Address;
import java.util.HashMap;

import java.util.List;

/**
 *
 * @author Shawn
 */
public interface AddressBookDao {

    /**
     * Adds the given Address to the book and associates it with the given
     * last name. If there is already an address associated with the given
     * last name it will return that address object, otherwise it will return
     * null.
     *
     * @param lastName with which address is to be associated
     * @param address address to be added to the book
     * @return the Address object previously associated with the given last name
     * id if it exists, null otherwise
     * @throws AddressBookDaoException
     */
    Address addAddress(String lastName, Address address)
            throws AddressBookDaoException;

    /**
     * Returns a List of all Addresses in the book.
     *
     * @return Address List containing all addresses in the book.
     * @throws AddressBookDaoException
     */
    List<Address> getAllAddresses()
            throws AddressBookDaoException;

    /**
     * Returns the address object associated with the given last name. Returns
     * null if no such address exists
     *
     * @param last name of the address to retrieve
     * @return the Address object associated with the given last name, null if
     * no such address exists
     * @throws AddressBookDaoException
     */
    Address getAddress(String lastName)
            throws AddressBookDaoException;

    /**
     * Removes from the book the address associated with the given last name. Returns
     * the address object that is being removed or null if there is no address
     * associated with the given last name
     *
     * @param lastname  of address to be removed
     * @return Address object that was removed or null if no address was
     * associated with the given last name
     * @throws AddressBookDaoException
     */
    Address removeAddress(String lastName)
            throws AddressBookDaoException;
    
    /**
     * Returns a count of all Addresses in the book.
     *
     * @return int number of total addresses in the book.
     * @throws AddressBookDaoException
     */
    
    int countAddress(HashMap <String, String> addresses)
            throws AddressBookDaoException;
}
        

