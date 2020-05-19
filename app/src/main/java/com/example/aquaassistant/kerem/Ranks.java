package com.example.aquaassistant.kerem;

public class Ranks {
    public static int level;
    public static String Experience = "0";
    public static String RANK = "Beginner";
    public static String INTERMEDIATE = "Intermediate";
    public static String ADVANCED = "Advanced";
    public static String EXPERT = "Expert";

    public Ranks(){
        if(Experience == "50"){
            RANK = INTERMEDIATE;
        }
        if(Experience == "100"){
            RANK = ADVANCED;
        }
        if(Experience == "150"){
            RANK = EXPERT;
        }
    }

}
