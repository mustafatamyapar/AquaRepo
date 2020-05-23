    package com.example.aquaassistant.zulal;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Iterator;

/**
 *This class creates a tank with fish, plant and other creatures
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (April 26, 2020) - completed
 */
    public class AquariumContainer extends Animal implements Serializable {

        //Properties
        private ArrayList<Animal> oneTank;
        private Iterator<Animal> iterator;
        private String name;
        private int liter;
        private int numOfFish;
        private int numOfOthers;
        private int numOfPlant;
        private int waterCheck;
        private int timeToFeed;
        private int pictureInteger;
        private boolean selected;
        //get the hour
        Calendar calendar = Calendar.getInstance();
        private int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        private int minute = calendar.get(Calendar.MINUTE);
        private static final long serialVersionUID = 1L;

        //Constructer
        public AquariumContainer() {
            this.oneTank = new ArrayList<>();
            iterator = oneTank.iterator();
        }

        public AquariumContainer( String name , int size , int pictureInteger)
        {
            super();
            this.oneTank = new ArrayList<>();
            this.name = name;
            this.pictureInteger = pictureInteger;
            liter = size;
            numOfFish = 0;
            numOfPlant = 0;
            numOfOthers = 0;
            //according to the size of the tank, times to feed and water change
            if (liter <= 120 ) {
                waterCheck = 14;
            } else if (liter <= 200 && liter > 120) {
                waterCheck = 7;
            } else if (liter > 200) {
                waterCheck = 7;
            }
            if (hourOfDay == 23 && minute == 59) {
                waterCheck--;
                timeToFeed--;
            }
            timeToFeed = 1;
            selected = false;
        }

    /**
     * This method sets the size of the tank
     * @param liter capacity of the tank
     */

            public void setSize ( int liter) {
                this.liter = liter;
            }
    /**
     * This method gives the size of the tank
     * @return  liter
     */
    public int getSize () {
        return liter;
    }
            //set tank name
            public void setTankName (String tankName){
                name = tankName;
            }
            //get tank name
            public String getTankName(){
                return name;
            }
            //get the time until water check
            public int getWaterCheck () {
                return waterCheck;
            }
            //get the time until feeding
            public int getTimeToFeed () {
                return timeToFeed;
            }
            //get the picture of the tank id
            public int getPictureInteger () {
                return pictureInteger;
            }
            //get the number of all animals in the tank
            public int getNumOfAllAnimals () {
                return oneTank.size();
            }

            //get and set the animal names
            @Override
            public String getName ()
            {
                return oneTank.toString();
            }
            @Override
            public void setName (String name)
            {
                Selectable temp;
                for (int i = 0; i < oneTank.size(); i++) {
                    temp = (Selectable) oneTank.get(i);
                    if (temp.getSelected() == true) {
                        oneTank.get(i).setName(name);
                    }
                }
            }
            //if the user checks the water
            public void waterChecked ( boolean check){
                if (check == true)
                {
                    //reset the times
                    if (liter <= 120 && liter >= 40) {
                        waterCheck = 14;
                    } else if (liter <= 200 && liter > 120) {
                        waterCheck = 7;
                    } else if (liter > 200) {
                        waterCheck = 7;
                    }
                }
            }
            //if the user feeds the animals
            public void animalsFed ( boolean check){
            //reset the times
                if (check == true) {
                    if (liter <= 120 && liter >= 40) {
                        timeToFeed = 4;
                    } else if (liter <= 200 && liter > 120) {
                        timeToFeed = 3;
                    } else if (liter > 200) {
                        timeToFeed = 2;
                    }
                }
            }
    /**
     * This method adds a new plant to tank
     */
    public void addPlant (Plant plant) {
                oneTank.add(plant);
                numOfPlant++;
            }
    /**
     * This method adds a new fish to tank

     */
    public void addFish (Fish fish){
                oneTank.add(fish);
                numOfFish++;
            }
    /**
     * This method adds an other creature into tank
     */
    public void addOther (OtherCreatures other){
                oneTank.add(other);
                numOfOthers++;
            }

            @Override
            //returns the list of type of creatures in the tank
            public String typeOfCreature () {
                String list = "";
                for (int i = 0; i < oneTank.size(); i++) {
                    list += oneTank.get(i).toString() + " ,";
                }
                return list;
            }

            @Override
            //return the id of the picture of the animals
            public int getId () {
                Selectable temp;
                int id = 0;
                for (int i = 0; i < oneTank.size(); i++) {
                    temp = (Selectable) oneTank.get(i);
                    if (temp.getSelected() == true) {
                        id = oneTank.get(i).getId();
                    }
                }
                return id;
            }

    /**
     * This method removes the selected creatures from the tank
     */
    public void removeAll () {
                Selectable temp;
                for (int i = 0; i < oneTank.size(); i++) {
                    temp = (Selectable) oneTank.get(i);
                    if (temp.getSelected() == true) {
                        oneTank.remove(oneTank.get(i));
                    }
                }
            }
    /**
     * This method returns the number of other fish inthe tank
     * @return numOfFishes
     */
    public int allFishes () {
                int numOfFishes = 0;
                for (int i = 0; i < oneTank.size(); i++) {
                    if (oneTank.get(i).typeOfCreature().equals("fish"))
                        numOfFishes++;
                }
                return numOfFishes;
            }
    /**
     * This method returns the number of plants in the tank
     * @return numOfPlant
     */
    public int allPlants () {
                int numOfPlants = 0;
                for (int i = 0; i < oneTank.size(); i++) {
                    if (oneTank.get(i).typeOfCreature().equals("Plant"))
                        numOfPlants++;
                }
                return numOfPlants;
            }
    /**
     * This method returns the number of other creatures
     * @return numOfOther
     */
    public int allOthers ()
            {
                int numOfOthers = 0;
                for (int i = 0; i < oneTank.size(); i++) {
                    if (oneTank.get(i).typeOfCreature().equals("other"))
                        numOfOthers++;
                }
                return numOfOthers;
            }
        }


