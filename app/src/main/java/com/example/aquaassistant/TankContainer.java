package com.example.aquaassistant;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class TankContainer implements Serializable
{
    //properties
    final ArrayList<Tank> tanks = new  ArrayList <Tank>();
    //CreatureContainer creatureContainer;
    //constructor
    public TankContainer(){
       // creatureContainer = new CreatureContainer();
    }
    //methods
    public void addTank( Tank newTank){
        tanks.add(newTank);
    }
    public void removeTank ( Tank tank){
        tanks.remove(tank);
    }
    public ArrayList<Tank> getTanks(){
        return tanks;
    }
    /*
    public void addCreatures( CreatureContainer creatureContainer){
        for ( int i = 0 ; i < tanks.size() ; i++){
            creatureContainer
        }
    }**/
}