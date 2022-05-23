package com.example.myfitnessapp.Item;

public class ExerciseItem {
    private String title;
    private String sets;
    private String weights;
    private String rest;

    public ExerciseItem(String title, String sets, String weights, String rest){
        this.title = title;
        this.sets = sets;
        this.weights = weights;
        this.rest = rest;
    }


    public String getTitle() {
        return title;
    }

    public String getSets() {
        return sets;
    }

    public String getWeights() {
        return weights;
    }

    public String getRest() {
        return rest;
    }
}
