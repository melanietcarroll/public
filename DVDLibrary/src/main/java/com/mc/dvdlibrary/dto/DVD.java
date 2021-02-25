/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dto;

/**
 * created 10/25/20
 *
 * @author Melanie Carroll
 */
public class DVD {

    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;

    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getStudio() {
        return studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

}
