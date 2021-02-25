/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.service;

import com.mc.flooringorder.dao.FlooringOrderAuditDao;
import com.mc.flooringorder.dao.FlooringOrderPersistenceException;

/**
 *
 * @author Melanie Carroll
 */
public class FlooringOrderAuditDaoStubImpl implements FlooringOrderAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws FlooringOrderPersistenceException {
        //do nothing . . .
    }
}
