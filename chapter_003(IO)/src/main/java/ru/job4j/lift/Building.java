package ru.job4j.lift;

/**
 * class Building - модель здания.
 */
public final class Building {
    /**
     * Количество этажей.
     */
    private int floorsCount;
    /**
     * Высота перекрытий.
     */
    private int floorHeight;

    /**
     * Конструктор. Количество этажей от 5 до 20, иначе создается здание с 20 этажами.
     * @param floorsCount количество этажей.
     * @param floorHeight высота перекрытий.
     */
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

    /**
     * Проверяет, есть ли этаж с таким номером в здании.
     * @param floor номер этажа.
     * @return true, если есть.
     */
    public boolean validateFloor(int floor) {
        return floor >= 1 && floor <= this.floorsCount;
    }
}
