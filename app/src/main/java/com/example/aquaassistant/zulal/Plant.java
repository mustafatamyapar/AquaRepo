package com.example.aquaassistant.zulal;
/*This class creates a plant
 * Zülal Nur Hıdıroğlu
 * 26.04.2020
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
