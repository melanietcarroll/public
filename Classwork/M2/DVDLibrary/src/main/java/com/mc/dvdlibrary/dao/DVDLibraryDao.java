/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dao;

import com.mc.dvdlibrary.dto.DVD;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    //Find all movies released in the last N years
public List<DVD> sortByRelease(Year date)throws DVDLibraryDaoException;

//Find all the movies with a given MPAA rating
public List<DVD> sortByMPAARating(String mpaaRating)throws DVDLibraryDaoException ;

//Find all the movies by a given director
//When searching by director, the movies should be sorted into separate data structures by MPAA rating.
public Map<String, List<DVD>> sortByDirector(String director)throws DVDLibraryDaoException ;

//Find all the movies released by a particular studio
public List<DVD> sortByStudio(String studio)throws DVDLibraryDaoException ;

//Find the average age of the movies in the collection
public int findAverageMovieAge()throws DVDLibraryDaoException ;

//Find the newest movie in your collection
public List <DVD> findNewestDVD()throws DVDLibraryDaoException ;

//Find the oldest movie in your collection
public List <DVD> findOldestDVD()throws DVDLibraryDaoException ;

//Find the average number of notes associated with movies in your collection
public long findAvgNotes()throws DVDLibraryDaoException ;
}
