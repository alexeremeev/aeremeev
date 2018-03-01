package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Gearbox model.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
@Entity(name = "gearbox")
public class Gearbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "shift")
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
        Gearbox gearbox = (Gearbox) o;
        return getId() == gearbox.getId() && Objects.equals(getName(), gearbox.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gearbox{");
        sb.append("id=").append(id);
        sb.append(", shift='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
