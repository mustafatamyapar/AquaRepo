package com.example.aquaassistant;

import android.text.Editable;

import java.io.Serializable;
import java.util.Calendar;
public class Tank extends CreatureContainer implements Serializable
{
    //properties
    private String name;
    private int size;
    private int numOfFish;
    private int numOfSnail;
    private int numOfPlant;
    private int waterCheck;
    private int timeToFeed;
    Calendar calendar = Calendar.getInstance();
    private int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute     = calendar.get(Calendar.MINUTE);
    private int pictureInteger;


    //constructor
    public Tank()
    {
        numOfFish = 0;
        numOfSnail = 0;
        numOfPlant = 0;
        waterCheck = 5;
        timeToFeed = 3;
        if ( hourOfDay == 23 && minute == 59 ){
            waterCheck--;
            timeToFeed--;
        }
    }
    public Tank(String name, int size, int pictureInteger)
    {
        this.name = name;
        this.size = size;
        this.pictureInteger = pictureInteger;
        numOfFish = 0;
        numOfSnail = 0;
        numOfPlant = 0;
        waterCheck = 5;
        timeToFeed = 3;
        if ( hourOfDay == 23 && minute == 59 ){
            waterCheck--;
            timeToFeed--;
        }
    }

    //methods
    public String getName(){
        return name;
    }
    public int getSize(){
        return size;
    }
    public int getNumOfFish(){
        return numOfFish;
    }
    public int getNumOfPlant(){
        return numOfPlant;
    }
    public int getNumOfSnail(){
        return numOfSnail;
    }
    public int getWaterCheck(){
        return waterCheck;
    }
    public int getCondCheck(){
        return timeToFeed;
    }
    public void setName( String name){
        this.name = name;
    }
    public void setSize( int size ){
        this.size = size;
    }
    //   public void setNumOfFish( int numOfFish ){
    //      this.numOfFish = numOfFish;
    //   }
    //   public void setNumOfPlant( int numOfPlant ){
    //      this.numOfPlant = numOfPlant;
    //   }
    //   public void setnumOfSnail( int numOfSnail ){
    //      this.numOfSnail = numOfSnail;
    //   }
    public void setWaterCheck( int waterCheck ){
        this.waterCheck = waterCheck;
    }
    public void setCondCheck( int timeToFeed ){
        this.timeToFeed = timeToFeed;
    }
    public void waterChecked ( boolean check ){
        if ( check == true ){
            setWaterCheck(5);
            //dailyProgress+=15;
        }
    }
    public void timeToFeeded ( boolean check){
        if ( check == true ){
            setCondCheck(3);
            //dailyProgress+=15;
        }
    }
    public int getPictureInteger() {
        return pictureInteger;
    }
}

