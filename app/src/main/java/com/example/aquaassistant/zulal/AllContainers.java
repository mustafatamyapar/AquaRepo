package com.example.aquaassistant.zulal;

import java.util.ArrayList;

public class AllContainers extends AquariumContainer implements Iterable<AquariumContainer> {

    //properties
    private ArrayList<AquariumContainer> allTanks;
    private ArrayList<AquariumContainer> iteratorAll;
//constructer
public AllContainers(){
    allTanks = new ArrayList<>();
    iteratorAll=  allTanks.iterator();
}
//methods
public void removeTank(){
    Selectable temp;
    for ( int i = 0; i < allTanks.size(); i++){
        temp = (Selectable) allTanks.get(i);
        if( temp.getSelected() == true)
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
        allCreatures += allTanks.get(i).size();
    }
    return allCreatures;
}
public int numOfTanks(){
    return allTanks.size();
}

}
