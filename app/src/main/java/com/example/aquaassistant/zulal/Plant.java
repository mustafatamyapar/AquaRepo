package com.example.aquaassistant.zulal;
/**
 *This class lists the specific information about plant
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (April 26, 2020) - completed
 */
public class Plant extends Animal implements Selectable {
    //properties
    private String plantName;
    private boolean selected;
    private String type;
    private int id;

//constructer
public Plant(String plantName, int id) {
    this.plantName = plantName;
    selected = false;
    type = "Plant";
    this.id = id;
}
//methods
@Override
public String getName() {
    return plantName;
}

@Override
public void setName(String name) {
    this.plantName = name;

}

@Override
public String typeOfCreature() {
    return type;
}

@Override
public int getId() {
    return id;
}

@Override
public boolean getSelected() {
    return selected;
}

@Override
public void setSelected(boolean selected) {
    this.selected = selected;

}
}
