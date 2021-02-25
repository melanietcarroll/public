/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.dao;

/**
 * created 11/21/20
 * @author Melanie Carroll
 */
public interface FlooringOrderAuditDao {
    public void writeAuditEntry(String entry) throws FlooringOrderPersistenceException;
}
