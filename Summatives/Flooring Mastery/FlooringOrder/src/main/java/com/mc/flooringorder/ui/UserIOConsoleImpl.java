/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.flooringorder.ui;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * created 11/21/20
 *
 * @author Melanie Carroll
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner myScanner = new Scanner(System.in);

    @Override
    public void print(String message) {

        System.out.println(message);
    }

    @Override
    public String readStringInput(String prompt) {
        boolean hasErrors = false;
        String read;
        do {
            System.out.println(prompt);
            read = myScanner.nextLine();
            if (Pattern.matches("[\\w,.]", read)) {
                hasErrors = false;
            }
            if (read.trim().isEmpty()) {
                hasErrors = true;
            }
        } while (hasErrors);

        return read;
    }

    @Override
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {

                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(prompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Incorrect value. Enter a valid number.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            num = readInt(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public double readDouble(String prompt) {

        while (true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Incorrect value. Enter a valid number.");
            }
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            num = readDouble(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Incorrect value. Enter a valid number.");
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            num = readFloat(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Incorrect value. Enter a valid number.");
            }
        }

    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            num = readLong(prompt);
        } while (num < min || num > max);

        return num;
    }

    @Override
    public BigDecimal readBig(String prompt) {
        boolean invalidInput = true;
        BigDecimal num = new BigDecimal("0");
        while (invalidInput) {
            try {

                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(prompt);
                // Get the input line, and try and parse
                num = new BigDecimal(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
//                return num;
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Incorrect value. Enter a valid number.");
            }
        }
        return num;

    }

    @Override
    public LocalDate readDate(String prompt) {
        boolean invalidInput = true;

        LocalDate date = LocalDate.now();

        while (invalidInput) {
            try {
                date = LocalDate.parse(prompt, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                invalidInput = false; // or you can use 'break;'
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date!");
            }

        }
        return date;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate max) {
        LocalDate today = LocalDate.now();
        max = today.plusMonths(6);
        LocalDate date;

        do {
            date = readDate(prompt);
        } while (date.isBefore(today) || date.isAfter(max));
        return date;
    }

    @Override
    public BigDecimal readArea(String prompt) {

        BigDecimal min = new BigDecimal("100");
        BigDecimal num;
        do {
            num = readBig(prompt);
        } while (num.compareTo(min) < 0);

        return num;

    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String read = myScanner.nextLine();
        return read;
    }

    @Override
    public String readString(String prompt, List<String> list) {

        boolean hasErrors = false;
        String read;
        do {
            System.out.println(prompt);
            read = myScanner.nextLine();
            for (String s : list) {
                if (s.equalsIgnoreCase(read)) {
                    hasErrors = false;
                    return read;
                }

                if (read.trim().isEmpty()) {
                    hasErrors = true;
                }
            }

        } while (hasErrors);
        return read;

    }
}
