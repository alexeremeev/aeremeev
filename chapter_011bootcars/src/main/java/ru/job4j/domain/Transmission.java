package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Transmission model.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
@Entity(name = "transmission")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "drive")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transmission that = (Transmission) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Transmission{");
        sb.append("id=").append(id);
        sb.append(", drive='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
