/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.addressbook.dto;

import java.util.Objects;

/**
 *
 * @author Shawn
 */
public class Address {
    private String firstName;
    private String lastName;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.firstName);
        hash = 61 * hash + Objects.hashCode(this.lastName);
        hash = 61 * hash + Objects.hashCode(this.streetAddress);
        hash = 61 * hash + Objects.hashCode(this.city);
        hash = 61 * hash + Objects.hashCode(this.state);
        hash = 61 * hash + Objects.hashCode(this.zip);
        return hash;
    }

    @Override
    public String toString() {
        return "Address{" + "firstName=" + firstName + ", lastName=" + lastName + ", streetAddress=" + streetAddress + ", city=" + city + ", state=" + state + ", zip=" + zip + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        return true;
    }
    //unit number and street name
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    
    public Address(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
