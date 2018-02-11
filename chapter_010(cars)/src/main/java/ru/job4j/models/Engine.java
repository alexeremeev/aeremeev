package ru.job4j.models;

import java.util.Objects;
/**
 * Engine model.
 * @author aeremeev.
 * @version 1
 * @since 01.02.2018
 */
public class Engine {

    private int id;

    private int name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
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
        Engine engine = (Engine) o;
        return getId() == engine.getId() && getName() == engine.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Engine{");
        sb.append("id=").append(id);
        sb.append(", force=").append(name);
        sb.append('}');
        return sb.toString();
    }
}
