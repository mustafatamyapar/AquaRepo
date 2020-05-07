package com.example.aquaassistant.zulal;
/*This class creates a fish
* Zülal Nur Hıdıroğlu
* 26.04.2020
*/
public class Fish extends Animal implements Selectable {
    private String nameOfFish;
    private boolean selected;
    private String type;
    private int id;
    private String description;
    private String difficulty;
//constructr
public Fish(String name,String description,String difficulty,int id )
{
    this.nameOfFish = name;
    this.selected = false;
    type = "fish";
    this.id = id;
    this.description = description;
    this.difficulty = difficulty;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
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
