/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classroster.service;

import com.mc.classroster.dao.ClassRosterAuditDao;
import com.mc.classroster.dao.ClassRosterDao;
import com.mc.classroster.dao.ClassRosterPersistenceException;
import com.mc.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Shawn
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    private ClassRosterAuditDao auditDao;
    ClassRosterDao dao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    
    @Override
    public void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {

        // First check to see if there is alreay a student 
        // associated with the given student's id
        // If so, we're all done here - 
        // throw a ClassRosterDuplicateIdException
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student.  Student Id "
                    + student.getStudentId()
                    + " already exists");
        }

        // Now validate all the fields on the given Student object.  
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateStudentData(student);

        // We passed all our business rules checks so go ahead 
        // and persist the Student object
        dao.addStudent(student.getStudentId(), student);
        // The student was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "Student " + student.getStudentId() + " CREATED.");

    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        // Write to audit log
        auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;
    }
    //Here, we are checking each field in the given object to see, first, that it is not null and, second, that it is not either empty or just whitespace. This is necessary because a null string and an empty string are not the same things in Java. If any of the fields is either null or empty/whitespace only, this method throws a ClassRosterDataValidationException.

    private void validateStudentData(Student student) throws
            ClassRosterDataValidationException {

        if (student.getFirstName() == null
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {

            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
}
