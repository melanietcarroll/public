/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.dvdlibrary.dao;

import com.mc.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * created 10/25/20
 *
 * @author Melanie Carroll
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public final String LIBRARY_FILE;
    public static final String DELIMITER = "::";

    public DVDLibraryDaoFileImpl() {
        LIBRARY_FILE = "library.txt";
    }

    public DVDLibraryDaoFileImpl(String libraryTextFile) {
        LIBRARY_FILE = libraryTextFile;
    }

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeDVDLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadDVDLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public DVD searchDVDByTitle(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD removedDVD = dvds.remove(title);
        writeDVDLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadDVDLibrary();
        DVD editedDVD = dvds.put(title, dvd);
        writeDVDLibrary();
        return editedDVD;

    }

    private DVD unmarshallDVDLibrary(String dvdAsText) {
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]    [2]         [3]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the dvd Id is in index 0 of the array.
        String dvdId = dvdTokens[0];

        // Which we can then use to create a new DVD object to satisfy
        // the requirements of the DVD constructor.
        DVD dvdFromFile = new DVD(dvdId);

        // However, there are 5 remaining tokens that need to be set into the
        // new dvd object. Do this manually by using the appropriate setters.
        // Index 1 - ReleaseDate
        dvdFromFile.setReleaseDate(Year.parse(dvdTokens[1]));

        // Index 2 - MpaaRating
        dvdFromFile.setMpaaRating(dvdTokens[2]);

        // Index 3 - DirectorName
        dvdFromFile.setDirectorName(dvdTokens[3]);
        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);
        // Index 5 - UserRating
        dvdFromFile.setUserRating(dvdTokens[5]);

        // We have now created a dvd! Return it!
        return dvdFromFile;
    }

    private void loadDVDLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent dvd unmarshalled
        DVD currentDVD;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // DVD object by calling the unmarshallDVDLibrary method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentDVD = unmarshallDVDLibrary(currentLine);

            // We are going to use the title as the map key for our dvd object.
            // Put currentDVD into the map using dvd id as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDVDLibrary(DVD aDVD) {
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Cars::2010::PG::Uknown::Pixar::5 stars

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 
        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:
        // Release Date
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;

        // MPAA Rating
        dvdAsText += aDVD.getMpaaRating() + DELIMITER;

        // Director
        dvdAsText += aDVD.getDirectorName() + DELIMITER;

        // Studio
        dvdAsText += aDVD.getStudio() + DELIMITER;

        // User Rating
        dvdAsText += aDVD.getUserRating();

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all dvds in the library out to a LIBRARY_FILE. See loadDVDLibrary
     * for file format.
     *
     * @throws DVDLibraryDaoException if an error occurs writing to the file
     */
    private void writeDVDLibrary() throws DVDLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);

        }
        // Write out the DVD objects to the library file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of DVDs and iterate over them but we've
        // already created a method that gets a List of DVDs so
        // we'll reuse it.
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDVDLibrary(currentDVD);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public List<DVD> sortByRelease(Year date) throws DVDLibraryDaoException{
        loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        List<DVD> sortedByDate = dvdList.stream()
    	.filter((d) -> d.getReleaseDate().equals(date))
    	.collect(Collectors.toList());
        return sortedByDate;
    }

    @Override
    public List<DVD> sortByMPAARating(String mpaaRating) throws DVDLibraryDaoException {
        loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        List<DVD> sortedByMPAA = dvdList.stream()
    	.filter((d) -> d.getMpaaRating().equals(mpaaRating))
    	.collect(Collectors.toList());
        return sortedByMPAA;
    }

    @Override
    public Map<String, List<DVD>> sortByDirector(String director)throws DVDLibraryDaoException  {
        loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        List<DVD> sortedByDirector = dvdList.stream()
    	.filter((d) -> d.getDirectorName().equals(director))
    	.collect(Collectors.toList());
        
        Map<String, List<DVD>> map = sortedByDirector.stream()
        .collect(Collectors.groupingBy((p) -> p.getMpaaRating()));
                
        return map;
    }

    @Override
    public List<DVD> sortByStudio(String studio) throws DVDLibraryDaoException {
       loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        List<DVD> sortedByStudio = dvdList.stream()
    	.filter((d) -> d.getStudio().equals(studio))
    	.collect(Collectors.toList());
        return sortedByStudio;
    }

    @Override
    public int findAverageMovieAge() throws DVDLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DVD> findNewestDVD() throws DVDLibraryDaoException {
        loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        List<Year> year = dvdList.stream()
    	.map((d) -> d.getReleaseDate())
        
    	.collect(Collectors.toList());
        
        // sort list in natural order 
        Collections.sort(year); 
  
        // first element in the sorted list 
        // would be minimum 
        Year newest = year.get(year.size() - 1); 
        
        List<DVD> newestDVD = dvdList.stream()
                .filter((d) -> d.getReleaseDate().equals(newest))
                .collect(Collectors.toList());
        return newestDVD;
        
        
    }

    @Override
    public List <DVD> findOldestDVD()throws DVDLibraryDaoException  {
       loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        List<Year> year = dvdList.stream()
    	.map((d) -> d.getReleaseDate())
        
    	.collect(Collectors.toList());
        
        // sort list in natural order 
        Collections.sort(year); 
  
        // first element in the sorted list 
        // would be minimum 
        Year oldest = year.get(0); 
        
        List<DVD> oldestDVD = dvdList.stream()
                .filter((d) -> d.getReleaseDate().equals(oldest))
                .collect(Collectors.toList());
        return oldestDVD;
    }

    @Override
    public long findAvgNotes() throws DVDLibraryDaoException {
        loadDVDLibrary();
        List<DVD> dvdList = new ArrayList(dvds.values());
        long count = dvdList.stream()
                .count();
        List<String> notes = dvdList.stream()
    	.map((d) -> d.getUserRating())
        .collect(Collectors.toList());
        
        long notesAvg = notes.stream()
                .count();
        long findAvg = notesAvg / count * 100;
        return findAvg;
    
}
}
