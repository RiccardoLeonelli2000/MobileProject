package com.example.myfitnessapp;

public class CardItem {
    private String imageResource;
    private String description;

    public CardItem(String imageResource, String description){
        this.imageResource = imageResource;
        this.description = description;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}
