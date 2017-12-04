package ru.job4j.control.models;

/**
 * class Role - модель роли пользователя.
 */
public class Role {
    /**
     * ID.
     */
    private int id;
    /**
     * Название роли.
     */
    private String name;

    /**
     * Setters and getters.
     */

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

        Role role = (Role) o;

        if (getId() != role.getId()) {
            return false;
        }
        return getName() != null ? getName().equals(role.getName()) : role.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Role{id=%s, name=%s}", this.id, this.name);
    }
}
