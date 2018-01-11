package ru.job4j.elevator;

public class Building {

    private int floorsCount;

    private int floorHeight;

    public Building(int floorsCount, int floorHeight) {
        if (floorsCount >= 5 && floorsCount <= 20) {
            this.floorsCount = floorsCount;
        } else {
            this.floorsCount = 20;
        }
        this.floorHeight = floorHeight;
    }

    public int getFloorsCount() {
        return this.floorsCount;
    }

    public int getFloorHeight() {
        return this.floorHeight;
    }
}
