/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.controller;

import com.mc.dvdlibrary.dao.DVDLibraryDao;
import com.mc.dvdlibrary.dao.DVDLibraryDaoException;
import com.mc.dvdlibrary.dto.DVD;
import com.mc.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 * created 10/25/20
 * @author Melanie Carroll
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        searchDVDByTitle();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        getAllDVDs();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void getAllDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void searchDVDByTitle() throws DVDLibraryDaoException {
        String dvdId = view.getDVDChoice();
        DVD dvd = dao.searchDVDByTitle(dvdId);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDChoice();
        DVD removedDVD = dao.removeDVD(dvdId);
        view.displayRemoveResult(removedDVD);
    }

    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
//        String dvdId = view.getDVDChoice();
        DVD editedDVD = view.getDVDInfo();
        dao.editDVD(editedDVD.getTitle(), editedDVD);
        view.displayCreateSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
