package ru.job4j.models;

import java.util.Objects;
/**
 * Transmission model.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class Transmission {

    private int id;

    private String drive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transmission that = (Transmission) o;
        return getId() == that.getId() &&
                Objects.equals(getDrive(), that.getDrive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDrive());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transmission{");
        sb.append("id=").append(id);
        sb.append(", drive='").append(drive).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
