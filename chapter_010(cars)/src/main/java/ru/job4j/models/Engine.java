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

    private int force;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return getId() == engine.getId() &&
                getForce() == engine.getForce();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getForce());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Engine{");
        sb.append("id=").append(id);
        sb.append(", force=").append(force);
        sb.append('}');
        return sb.toString();
    }
}
