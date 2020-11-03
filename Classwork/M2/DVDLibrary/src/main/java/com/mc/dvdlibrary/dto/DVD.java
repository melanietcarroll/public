/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * created 10/25/20
 *
 * @author Melanie Carroll
 */
public class DVD {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.releaseDate);
        hash = 29 * hash + Objects.hashCode(this.mpaaRating);
        hash = 29 * hash + Objects.hashCode(this.directorName);
        hash = 29 * hash + Objects.hashCode(this.studio);
        hash = 29 * hash + Objects.hashCode(this.userRating);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
        return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DVD{" + "title=" + title + ", releaseDate=" + releaseDate + ", mpaaRating=" + mpaaRating + ", directorName=" + directorName + ", studio=" + studio + ", userRating=" + userRating + '}';
    }

    private String title;
//    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;
    LocalDate releaseDate;

    public DVD(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
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

    public void setReleaseDate(LocalDate releaseDate) {
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
