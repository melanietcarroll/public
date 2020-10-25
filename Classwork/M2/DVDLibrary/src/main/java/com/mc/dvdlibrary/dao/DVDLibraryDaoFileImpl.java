/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dao;

import com.mc.dvdlibrary.dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * created 10/25/20
 * @author Melanie Carroll
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao{
    
     private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
//        loadDVDLibrary();
        DVD newDVD = dvds.put(title, dvd);
//        writeDVDLibrary(); 
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
//         loadDVDLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public DVD searchDVDByTitle(String title) throws DVDLibraryDaoException {
//        loadDVDLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
//        loadDVDLibrary();
        DVD removedDVD = dvds.remove(title);
//        writeDVDLibrary(); 
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        return dvds.put(title, dvd);
    }
//
//    @Override
//    public DVD displayDVDInfo(String title) throws DVDLibraryDaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
