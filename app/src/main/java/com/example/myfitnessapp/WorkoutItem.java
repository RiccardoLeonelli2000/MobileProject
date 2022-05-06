package com.example.myfitnessapp;

public class WorkoutItem {
    private String title;
    private String imageResource;
    private String description;

    public WorkoutItem(String title, String imageResource, String description){
        this.title = title;
        this.imageResource = imageResource;
        this.description = description;
    }

    public String getImageResource() {
        return imageResource;
    }

    public  String getTitle() { return title; }

    public String getDescription() {
        return description;
    }

}
