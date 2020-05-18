package com.example.aquaassistant.kerem;

public class Ranks {
    public static int level;
    public static String RANK = "Begginer";
    public static String INTERMEDIATE = "Intermediate";
    public static String ADVANCED = "Advanced";
    public static String EXPERT = "Expert";

    public Ranks(){
        if(level == 2){
            RANK = INTERMEDIATE;
        }
        if(level == 3){
            RANK = ADVANCED;
        }
        if(level == 4){
            RANK = EXPERT;
        }
    }
    public void changeRank(){

    }

}
