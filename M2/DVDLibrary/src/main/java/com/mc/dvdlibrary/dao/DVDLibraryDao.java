/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dao;

import com.mc.dvdlibrary.dto.DVD;
import java.util.HashMap;
import java.util.List;

/**
 * created 10/25/20
 * @author Melanie Carroll
 */
public interface DVDLibraryDao {
     /**
     * Adds the given DVD to the library and associates it with the given
     * title it will return that DVD object, otherwise it will return
     * null.
     *
     * @param title with which DVD is to be associated
     * @param DVD dvd to be added to the library
     * @return the DVD object previously associated with the given title
     * id if it exists, null otherwise
     * @throws DVDLibraryDaoException
     */
    DVD addDVD(String title, DVD dvd)
            throws DVDLibraryDaoException;

    /**
     * Returns a List of all DVDs in the library.
     *
     * @return DVD List containing all DVDs in the library.
     * @throws DVDLibraryDaoException
     */
    List<DVD> getAllDVDs()
            throws DVDLibraryDaoException;

    /**
     * Returns the DVD object associated with the given title. Returns
     * null if no such DVD exists
     *
     * @param title of the DVD to retrieve
     * @return the DVD object associated with the given title, null if
     * no such title exists
     * @throws DVDLibraryDaoException
     */
    DVD searchDVDByTitle(String title)
            throws DVDLibraryDaoException;

    /**
     * Removes from the library the DVD associated with the given title. Returns
     * the DVD object that is being removed or null if there is no DVD
     * associated with the given title
     *
     * @param title  of DVD to be removed
     * @return DVD object that was removed or null if no DVD was
     * associated with the given title
     * @throws DVDLibraryDaoException
     */
    DVD removeDVD(String title)
            throws DVDLibraryDaoException;
    
    /**
     * Allows user to edit a DVD in the library.
     *
     * @return updated info for DVD in the library.or null if there is no DVD
     * associated with the given title
     * @throws DVDLibraryDaoException
     */
    
    DVD editDVD(String title, DVD dvd)
            throws DVDLibraryDaoException;
    /**
     * Allows user to view info for a DVD in the library.
     *
     * @return info for DVD in the library or null if there is no DVD
     * associated with the given title
     * @throws DVDLibraryDaoException
     */
    
}
