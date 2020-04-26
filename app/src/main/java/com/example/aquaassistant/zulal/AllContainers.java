package com.example.aquaassistant.zulal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class AllContainers extends AquariumContainer implements Serializable {

    //properties
    private ArrayList<AquariumContainer> allTanks;
    //constructer
    public AllContainers(){
        allTanks =  new ArrayList<AquariumContainer>();
    }
    //methods
    public void removeTank(){
        Selectable temp;
        for ( int i = 0; i < allTanks.size(); i++){
            temp = (Selectable) allTanks.get(i);
            if(temp.getSelected())
            {
                allTanks.remove(allTanks.get(i));
            }
        }
    }

    public void addTank( AquariumContainer container){
        allTanks.add(container);
    }
    public int numberOfAllCreatures(){
        int allCreatures = 0;
        for ( int i = 0; i< allTanks.size(); i++) {
            allCreatures += allTanks.get(i).allFishes()+ allTanks.get(i).allOthers() + allTanks.get(i).allPlants();
        }
        return allCreatures;
    }
    public int numOfTanks(){
        return allTanks.size();
    }

    @Override
    public int getId() {
        int ids = 0;
        for (int i = 0; i< allTanks.size(); i++)
        {
            ids+= allTanks.get(i).getId();
        }
        return ids;
    }
    //returns the arraylist
    public ArrayList<AquariumContainer> getAllTanks(){
        return allTanks;
    }
    }
