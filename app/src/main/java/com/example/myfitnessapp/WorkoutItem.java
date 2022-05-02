package com.example.myfitnessapp;

public class WorkoutItem {
    private String imageResource;
    private String description;

    public WorkoutItem(String imageResource, String description){
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
