package com.example.aquaassistant.zulal;

/**
 *This class lists the specific information about the other creatures
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (April 26, 2020) - completed
 */
public class OtherCreatures extends Animal implements Selectable {
    //properties
    private String nameOfCreature;
    private boolean selected ;
    private String type;
    private int id;

//constructer
public OtherCreatures(String nameOfCreature, int id)
{
    this.nameOfCreature = nameOfCreature;
    selected = false;
    type = "other";
    this.id = id;
}
//methods
@Override
public String getName() {
    return nameOfCreature;
}

@Override
public void setName(String name) {
this.nameOfCreature = name;
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
