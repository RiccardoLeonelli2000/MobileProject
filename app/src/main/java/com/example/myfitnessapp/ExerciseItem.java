package com.example.myfitnessapp;

public class ExerciseItem {
    private String title;
    private String sets;
    private String reps;

    public ExerciseItem(String title, String sets, String reps){
        this.title = title;
        this.sets = sets;
        this.reps = reps;
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
}
