package ru.job4j.models;

import java.util.Objects;
/**
 * Car model.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class Car {

    private int id;

    private String name;

    private Transmission transmission;

    private Gearbox gearbox;

    private Engine engine;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getId() == car.getId() &&
                Objects.equals(getName(), car.getName()) &&
                Objects.equals(getTransmission(), car.getTransmission()) &&
                Objects.equals(getGearbox(), car.getGearbox()) &&
                Objects.equals(getEngine(), car.getEngine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTransmission(), getGearbox(), getEngine());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", transmission=").append(transmission);
        sb.append(", gearbox=").append(gearbox);
        sb.append(", engine=").append(engine);
        sb.append('}');
        return sb.toString();
    }
}
