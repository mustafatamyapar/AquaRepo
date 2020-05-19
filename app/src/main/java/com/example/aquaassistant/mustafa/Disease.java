package com.example.aquaassistant.mustafa;
/**
 *
 * @author
 * @version
 */
public class Disease {
    private String name;
    private String description;
    private String symptoms;
    private int image;

    public Disease() {
    }

    public Disease(String name, String description, String symptoms, int image) {
        this.name = name;
        this.description = description;
        this.symptoms = symptoms;
        this.image = image;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public String getSymptoms() {

        return symptoms;
    }

    public int getImage() {

        return image;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setSymptoms(String symptoms) {

        this.symptoms = symptoms;
    }

    public void setImage(int image) {

        this.image = image;
    }

}
