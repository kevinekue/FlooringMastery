/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class UserIOConsoleImpl implements UserIO {
    
    Scanner iRead = new Scanner(System.in);
    
    @Override
    public void print(String message){
        System.out.println(message);
        
    };
    
    @Override
    public double readDouble(String prompt){
        System.out.println(prompt);
        double value = iRead.nextDouble();
        return value;
    };

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt+" "+ min + " and "+ max);
        double value = iRead.nextDouble();
        if(value<min || value>max){
                    System.out.println("Error, please enter values between "+ min+ " and "+ max);
                }
                else{
                    return value;
                }
        return value;
    };

    @Override
    public float readFloat(String prompt){
        System.out.println(prompt);
        float value = iRead.nextFloat();
        return value;
    };

    @Override
    public float readFloat(String prompt, float min, float max){
        System.out.println(prompt+" "+ min + " and "+ max);
        float value = iRead.nextFloat();
                if(value<min || value>max){
                    System.out.println("Error, please enter values between "+ min+ " and "+ max);
                    return value;
                }
                else{
                    return value;
                }
    };

    @Override
    public int readInt(String prompt){
        System.out.println(prompt);
        int value = iRead.nextInt();
        String buffer = iRead.nextLine();
        return value;
    
    };

    @Override
    public int readInt(String prompt, int min, int max){
        System.out.println(prompt+" "+ min + " and "+ max);
        int value = iRead.nextInt();
        String buffer = iRead.nextLine();
        if(value<min || value>max){
                    System.out.println("Error, please enter values between "+ min+ " and "+ max);
                    return value;
                }
                else{
                    return value;
                }
    };

    @Override
    public long readLong(String prompt){
        System.out.println(prompt);
        long value = iRead.nextLong();
        return value;
    };

    @Override
    public long readLong(String prompt, long min, long max){
        System.out.println(prompt+ " "+min+ " and "+ max);
        long value = iRead.nextLong();
        if(value<min || value>max){
                    System.out.println("Error, please enter values between "+ min+ " and "+ max);
                    return value;
                }
                else{
                    return value;
                }
    };

    @Override
    public String readString(String prompt){
        
        System.out.println(prompt);
        
        String value = iRead.nextLine();
        
        return  value;
        
    };
    
}
