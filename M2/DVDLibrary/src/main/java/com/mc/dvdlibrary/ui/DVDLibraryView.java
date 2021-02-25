/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.ui;

import com.mc.dvdlibrary.dto.DVD;
import java.util.List;

/**
 * created 10/25/20
 * @author Melanie Carroll
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Initial Menu:");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Find DVD");
        io.print("4. Edit DVD");
        io.print("5. List All DVDs");
        io.print("6. Exit");

        return io.readInt("Please select the operation you wish to perform:", 1, 6);
    }

    public DVD getDVDInfo() {
        String title = io.readString("Please Enter A Title:");
        String releaseDate = io.readString("Please Enter A Release Date:");
        String mpaaRating = io.readString("Please Enter An MPAA Rating:");
        String directorName = io.readString("Please Enter Director's Name:");
        String studio = io.readString("Please Enter Studio Name:");
        String userRating = io.readString("Please Enter User Rating or note:");
        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("Add DVD Menu:");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD added.  Please hit enter to continue.");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s: \n Release date: %s \n MPAA Rating: %s \n Director's Name: %s \n Sudio: %s \n User Rating: %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMpaaRating(),
                    currentDVD.getDirectorName(),
                    currentDVD.getStudio(),
                    currentDVD.getUserRating());

            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("--- Display All DVDs ---");
    }

    public String getDVDChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director's Name: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayRemoveDVDBanner () {
    io.print("--- Remove DVD ---");
}
    public void displayRemoveResult(DVD dvdRecord) {
    if(dvdRecord != null){
      io.print("DVD successfully removed.");
    }else{
      io.print("No such DVD.");
    }
    io.readString("Please hit enter to continue.");
}
    public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}
public void displayErrorMessage(String errorMsg) {
    io.print("--- ERROR ---");
    io.print(errorMsg);
}
public void displayExitBanner() {
    io.print("Good Bye!!!");
}
public void displayEditDVDBanner(){
    io.print("--- Edit DVD ---");
}
}
