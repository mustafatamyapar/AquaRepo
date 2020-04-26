package com.example.aquaassistant.zulal;
import java.util.ArrayList;
import java.util.Iterator;

/*This class creates a tank with fish, plant and other creatures
 * Zülal Nur Hıdıroğlu
 * 26.04.2020
 */
public class AquariumContainer extends Animal implements Iterable<Animal> {

    //Properties
    private ArrayList<Animal> oneTank;
private Iterator<Animal>  iterator;
//Constructer
public AquariumContainer() {
    this.oneTank = new ArrayList<>();
    iterator = oneTank.iterator();
}
//methods
public void addAnimal( Animal animal)
{
    oneTank.add(animal);
}

@Override
public Iterator<Animal> iterator()
{
    return oneTank.iterator();
}

public int size()
{
    return oneTank.size();
}


@Override
public String getName()
{
    return oneTank.toString();
}

@Override
public void setName(String name)
{
    Selectable temp;
    for ( int i = 0; i < oneTank.size(); i++){
        temp = (Selectable)oneTank.get(i);
        if ( temp.getSelected() == true)
        {
            oneTank.get(i).setName(name);
        }
    }
}

@Override
public String typeOfCreature() {
    String list = "";
    for ( int i = 0; i < oneTank.size(); i++)
    {
       list += oneTank.get(i).toString() + " ,";
    }
    return list;
}

public void removeAll ()
{
    Selectable temp;
    for ( int i = 0; i < oneTank.size(); i++){
        temp = (Selectable) oneTank.get(i);
        if ( temp.getSelected() == true){
            oneTank.remove(oneTank.get(i));
        }
    }
}
public int allFishes()
{
    int numOfFishes = 0;
    for ( int i = 0;  i < oneTank.size(); i++)
    {
        if( oneTank.get(i).typeOfCreature().equals("fish") )
            numOfFishes++;
    }
    return numOfFishes;
}
public int allPlants()
{
    int numOfPlants = 0;
    for ( int i = 0;  i < oneTank.size(); i++)
    {
        if( oneTank.get(i).typeOfCreature().equals("Plant") )
            numOfPlants++;
    }
    return numOfPlants;
}
public int allOthers()
{
    int numOfOthers = 0;
    for ( int i = 0;  i < oneTank.size(); i++)
    {
        if( oneTank.get(i).typeOfCreature().equals("other") )
            numOfOthers++;
    }
    return numOfOthers;
}

}
