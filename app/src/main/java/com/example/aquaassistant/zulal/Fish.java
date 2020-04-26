package com.example.aquaassistant.zulal;
/*This class creates a fish
* Zülal Nur Hıdıroğlu
* 26.04.2020
*/
public class Fish extends Animal implements Selectable {
    private String nameOfFish;
    private boolean selected;
    private String type;
//constructr
public Fish(String name)
{
    this.nameOfFish = name;
    this.selected = false;
    type = "fish";
}
//methods
@Override
public String getName() {
    return nameOfFish;
}
@Override
public void setName(String name) {
    this.nameOfFish =name;

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
