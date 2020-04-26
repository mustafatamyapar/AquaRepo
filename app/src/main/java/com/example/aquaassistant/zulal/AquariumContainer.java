    package com.example.aquaassistant.zulal;
    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Iterator;

    /*This class creates a tank with fish, plant and other creatures
     * Zülal Nur Hıdıroğlu
     * 26.04.2020
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
        Calendar calendar = Calendar.getInstance();
        private int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        private int minute = calendar.get(Calendar.MINUTE);
        private int pictureInteger;

        //Constructer
        public AquariumContainer() {
            this.oneTank = new ArrayList<>();
            iterator = oneTank.iterator();

            numOfFish = this.allFishes();
            numOfPlant = this.allPlants();
            numOfOthers = this.allOthers();
            if (liter <= 120 && liter >= 40) {
                timeToFeed = 4;
                waterCheck = 14;
            } else if (liter <= 200 && liter > 120) {
                timeToFeed = 3;
                waterCheck = 7;
            } else if (liter > 200) {
                timeToFeed = 2;
                waterCheck = 7;
            }
            if (hourOfDay == 23 && minute == 59) {
                waterCheck--;
                timeToFeed--;
            }
        }

            //methods
            public void setSize ( int liter){
                this.liter = liter;
            }
            public int getSize () {
                return liter;
            }
            public void setTankName (String tankName){
                name = tankName;
            }
            public int getWaterCheck () {
                return waterCheck;
            }
            public int getTimeToFeed () {
                return timeToFeed;
            }
            public int getPictureInteger () {
                return pictureInteger;
            }

            public void waterChecked ( boolean check){
                if (check == true) {
                    if (liter <= 120 && liter >= 40) {
                        waterCheck = 14;
                    } else if (liter <= 200 && liter > 120) {
                        waterCheck = 7;
                    } else if (liter > 200) {
                        waterCheck = 7;
                    }
                }
            }
            public void animalsFed ( boolean check){
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

            public void addPlant (Plant plant)
            {
                oneTank.add(plant);
            }
            public void addFish (Fish fish){
                oneTank.add(fish);
            }
            public void addOther (OtherCreatures other){
                oneTank.add(other);
            }

            public int size ()
            {
                return oneTank.size();
            }


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

            @Override
            public String typeOfCreature () {
                String list = "";
                for (int i = 0; i < oneTank.size(); i++) {
                    list += oneTank.get(i).toString() + " ,";
                }
                return list;
            }

            @Override
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

            public void removeAll ()
            {
                Selectable temp;
                for (int i = 0; i < oneTank.size(); i++) {
                    temp = (Selectable) oneTank.get(i);
                    if (temp.getSelected() == true) {
                        oneTank.remove(oneTank.get(i));
                    }
                }
            }
            public int allFishes ()
            {
                int numOfFishes = 0;
                for (int i = 0; i < oneTank.size(); i++) {
                    if (oneTank.get(i).typeOfCreature().equals("fish"))
                        numOfFishes++;
                }
                return numOfFishes;
            }
            public int allPlants ()
            {
                int numOfPlants = 0;
                for (int i = 0; i < oneTank.size(); i++) {
                    if (oneTank.get(i).typeOfCreature().equals("Plant"))
                        numOfPlants++;
                }
                return numOfPlants;
            }
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


