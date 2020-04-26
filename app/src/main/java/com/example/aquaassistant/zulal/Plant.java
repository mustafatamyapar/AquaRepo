package com.example.aquaassistant.zulal;
/*This class creates a plant
 * Zülal Nur Hıdıroğlu
 * 26.04.2020
 */
public class Plant extends Animal implements Selectable {
    //properties
    private String plantName;
    private boolean selected;
    String type;

//constructer
public Plant(String plantName) {
    this.plantName = plantName;
    selected = false;
    type = "Plant";
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
public boolean getSelected() {
    return selected;
}

@Override
public void setSelected(boolean selected) {
    this.selected = selected;

}
}
