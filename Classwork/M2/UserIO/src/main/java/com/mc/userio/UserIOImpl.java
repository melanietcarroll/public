/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.userio;

import java.util.Scanner;

/**
 *
 * @author Shawn
 */
public class UserIOImpl implements UserIO {

    Scanner myScanner = new Scanner(System.in);

    @Override
    public void print(String message) {

        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String read = myScanner.nextLine();
        return read;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int num = Integer.parseInt(myScanner.nextLine());
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            System.out.println(prompt);
            num = myScanner.nextInt();
        }while (num < min || num > max);

       return num;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double result = myScanner.nextDouble();
        return result;
    }
@Override
    public double readDouble(String prompt, double min, double max){
       double num;
        do {
            System.out.println(prompt);
            num = myScanner.nextDouble();
        }while (num < min || num > max);

       return num;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float result = myScanner.nextFloat();
        return result;
    }

    @Override
    public float readFloat(String prompt, float min, float max){
         float num;
        do {
            System.out.println(prompt);
            num = myScanner.nextFloat();
        }while (num < min || num > max);

       return num;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long result = myScanner.nextLong();
        return result;

    }
@Override
    public long readLong(String prompt, long min, long max){
         long num;
        do {
            System.out.println(prompt);
            num = myScanner.nextLong();
        }while (num < min || num > max);

       return num;
    }

}
