package com.example.aquaassistant.zulal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * This class is created to add tank and to get their number of contents
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (April 26, 2020) - completed
 */
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
    /**
     * This method adds a new tank to container
     * @param container
     */
    public void addTank( AquariumContainer container){
        allTanks.add(container);
    }
    /**
     * This method finds the number of creatures in the tank
     */
    public int numberOfAllCreatures(){
        int allCreatures = 0;
        for ( int i = 0; i< allTanks.size(); i++) {
            allCreatures += allTanks.get(i).allFishes()+ allTanks.get(i).allOthers() + allTanks.get(i).allPlants();
        }
        return allCreatures;
    }
    /**
     * This method gives the number of tanks in the container
     */
    public int numOfTanks(){
        return allTanks.size();
    }
    /**
     * This method gets the id of the creature
     * @return ids which is position of the creature in the Arraylist
     */
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
