/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classroster.service;

import com.mc.classroster.dao.ClassRosterAuditDao;
import com.mc.classroster.dao.ClassRosterPersistenceException;

/**
 *
 * @author Shawn
 */
public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing . . .
    }
}
