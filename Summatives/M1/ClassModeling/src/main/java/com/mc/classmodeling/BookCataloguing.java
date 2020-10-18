/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.classmodeling;

/**
 *
 * @author Shawn
 */
public class BookCataloguing {

    private String authorOfBook;
    private String titleOfBook;
    private int numberOfBooks;
    private String bookCategory;
    private int shelfNumber;

    public void setAuthorOfBook(String authorOfBook) {
        this.authorOfBook = authorOfBook;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAuthorOfBook() {
        return authorOfBook;
    }

    public String getTitleOfBook() {
        return titleOfBook;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public int getIndex() {
        return index;
    }
    private int index;

    public void searchForBook() {

    }

    public void addBook() {

    }

    public void checkOutBook() {

    }
}
