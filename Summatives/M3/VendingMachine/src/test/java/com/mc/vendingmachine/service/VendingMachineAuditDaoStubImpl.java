/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.vendingmachine.service;

import com.mc.vendingmachine.dao.VendingMachineAuditDao;
import com.mc.vendingmachine.dao.VendingMachinePersistenceException;

/**
 *
 * @author Shawn
 */
public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao{
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        //do nothing . . .
    }
}
