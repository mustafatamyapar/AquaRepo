package com.example.aquaassistant.zulal;

/*This class creates a fifferent creature in the aquarium
 * Zülal Nur Hıdıroğlu
 * 26.04.2020
 */
public class OtherCreatures extends Animal implements Selectable {
    //properties
    private String nameOfCreature;
    private boolean selected ;
    private String type;

//constructer
public OtherCreatures(String nameOfCreature)
{
    this.nameOfCreature = nameOfCreature;
    selected = false;
    type = "other";
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
public boolean getSelected() {
    return selected;
}

@Override
public void setSelected(boolean selected) {
    this.selected = selected;

}


}
