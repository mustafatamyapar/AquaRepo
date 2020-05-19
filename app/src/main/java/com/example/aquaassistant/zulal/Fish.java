package com.example.aquaassistant.zulal;
/**
 *This class lists the specific information about the creature fish
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (April 26, 2020) - completed
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
    /**
     * This method gves the descrption about fish
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * This method set a description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * This method gves the difficulty
     * @return difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }
    /**
     * This method set difficulty
     * @return numOfOther
     */
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
