package com.example.aquaassistant.mustafa;

public class Disease {
    private String name;
    private String description;
    private String symptoms;
    private String imageUrl;

    public Disease() {
    }

    public Disease(String name, String description, String symptoms, String imageUrl) {
        this.name = name;
        this.description = description;
        this.symptoms = symptoms;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
