package com.larionov.epam.repository;

public class NameHotels {
    private String[] namesOfHotels;
    private int count;

    public NameHotels() {
        this.namesOfHotels = new String[]{"Ostroff", "Gelvetcia", "Pushkin",
                "Roma", "Paris", "Rosario", "Velvet", "Monica", "Roshe", "Green", "Leaf", "Gold Leo", "Dragon", "Secret", "London"};
        this.count = 0;
    }

    public String[] getNamesOfHotels() {
        return namesOfHotels;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
