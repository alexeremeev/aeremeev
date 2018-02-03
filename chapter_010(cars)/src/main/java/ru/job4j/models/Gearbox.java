package ru.job4j.models;

import java.util.Objects;
/**
 * Gearbox model.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class Gearbox {

    private int id;

    private String shift;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gearbox gearbox = (Gearbox) o;
        return getId() == gearbox.getId() &&
                Objects.equals(getShift(), gearbox.getShift());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getShift());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gearbox{");
        sb.append("id=").append(id);
        sb.append(", shift='").append(shift).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
