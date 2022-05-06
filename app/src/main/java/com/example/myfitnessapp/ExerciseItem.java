package com.example.myfitnessapp;

public class ExerciseItem {
    private String title;
    private String sets;
    private String reps;
    private String rest;

    public ExerciseItem(String title, String sets, String reps, String rest){
        this.title = title;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
    }


    public String getTitle() {
        return title;
    }

    public String getSets() {
        return sets;
    }

    public String getReps() {
        return reps;
    }

    public String getRest() {
        return rest;
    }
}
