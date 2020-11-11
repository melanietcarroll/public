/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.addressbook.dao;

import com.mc.addressbook.dto.Address;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Shawn
 */
public class AddressBookDaoFileImplTest {
    AddressBookDao testDao;
    
    public AddressBookDaoFileImplTest() {
    }
    
   
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testaddress.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new AddressBookDaoFileImpl(testFile);
    }
  
    @Test
    public void testAddGetAddress()throws Exception {
        // Create our method test inputs
    String lastName = "Carey";
    Address address = new Address(lastName);
    address.setFirstName("Ada");
   address.setLastName("Lovelace");
    address.setStreetAddress("Magnolia way");
    address.setCity("Magnolia");
    address.setState("MN");
    address.setZip("2222");

    //  Add the student to the DAO
    testDao.addAddress(lastName, address);
    // Get the student from the DAO
    Address retrievedAddress = testDao.getAddress(lastName);

    // Check the data is equal
    assertEquals(address.getLastName(),
                retrievedAddress.getLastName(),
                "Checking student id.");
    assertEquals(address.getFirstName(),
               retrievedAddress.getFirstName(),
                "Checking student first name.");
    assertEquals(address.getStreetAddress(), 
                retrievedAddress.getStreetAddress(),
                "Checking student last name.");
    assertEquals(address.getCity(), 
                retrievedAddress.getCity(),
                "Checking student cohort.");
    assertEquals(address.getState(), 
                retrievedAddress.getState(),
                "Checking student cohort.");
    assertEquals(address.getZip(), 
                retrievedAddress.getZip(),
                "Checking student cohort.");
}
    @Test
public void testAddGetAllAddresses() throws Exception {
    // Create our first student
     String lastName = "Carey";
    Address address = new Address(lastName);
    address.setFirstName("Ada");
   address.setLastName("Lovelace");
    address.setStreetAddress("Magnolia way");
    address.setCity("Magnolia");
    address.setState("MN");
    address.setZip("2222");

    // Create our second student
    String lastName2 = "Carey";
    Address address2 = new Address(lastName2);
    address2.setFirstName("Lou");
   address2.setLastName("Lovelace");
    address2.setStreetAddress("Magnolia way");
    address2.setCity("Magnolia");
    address2.setState("MN");
    address2.setZip("2222");

    // Add both our students to the DAO
    testDao.addAddress(lastName, address);
    testDao.addAddress(lastName2, address2);

    // Retrieve the list of all students within the DAO
    List<Address> allAddresses = testDao.getAllAddresses();

    // First check the general contents of the list
    assertNotNull(allAddresses, "The list of students must not null");
    assertEquals(2, allAddresses.size(),"List of students should have 2 students.");

    // Then the specifics
    assertTrue(testDao.getAllAddresses().contains(address),
                "The list of students should include Ada.");
    assertTrue(testDao.getAllAddresses().contains(address2),
            "The list of students should include Charles.");

}
@Test
public void testRemoveAddress() throws Exception {
   // Create our first student
     String lastName = "Carey";
    Address address = new Address(lastName);
    address.setFirstName("Ada");
   address.setLastName("Lovelace");
    address.setStreetAddress("Magnolia way");
    address.setCity("Magnolia");
    address.setState("MN");
    address.setZip("2222");

    // Create our second student
    String lastName2 = "Carey";
    Address address2 = new Address(lastName2);
    address2.setFirstName("Lou");
   address2.setLastName("Lovelace");
    address2.setStreetAddress("Magnolia way");
    address2.setCity("Magnolia");
    address2.setState("MN");
    address2.setZip("2222");


   // Add both our students to the DAO
    testDao.addAddress(lastName, address);
    testDao.addAddress(lastName2, address2);

    // remove the first student - Ada
    Address removedAddress = testDao.removeAddress(lastName);

    // Check that the correct object was removed.
    assertEquals(removedAddress, address, "The removed student should be Ada.");

    // Get all the students
    List<Address> allAddresses = testDao.getAllAddresses();

    // First check the general contents of the list
    assertNotNull( allAddresses, "All students list should be not null.");
    assertEquals( 1, allAddresses.size(), "All students should only have 1 student.");

    // Then the specifics
    assertFalse( allAddresses.contains(address), "All students should NOT include Ada.");
    assertTrue( allAddresses.contains(address2), "All students should include Lou.");    

    // Remove the second student
    removedAddress = testDao.removeAddress(lastName2);
    // Check that the correct object was removed.
    assertEquals( removedAddress, address2, "The removed student should be Lou.");

    // retrieve all of the students again, and check the list.
    allAddresses = testDao.getAllAddresses();

    // Check the contents of the list - it should be empty
    assertTrue( allAddresses.isEmpty(), "The retrieved list of students should be empty.");

    // Try to 'get' both students by their old id - they should be null!
    Address retrievedAddress = testDao.getAddress(lastName);
    assertNull(retrievedAddress, "Ada was removed, should be null.");

    retrievedAddress = testDao.getAddress(lastName2);
    assertNull(retrievedAddress, "Charles was removed, should be null.");

}
    }
    

