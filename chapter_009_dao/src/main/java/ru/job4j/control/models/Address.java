package ru.job4j.control.models;

/**
 * class Address - модель адреса пользователя.
 */
public class Address {
    /**
     * ID.
     */
    private int id;
    /**
     * Адрес.
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

        Address address = (Address) o;

        if (getId() != address.getId()) {
            return false;
        }
        return getName() != null ? getName().equals(address.getName()) : address.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Address{id=%s, name=%s}", this.id, this.name);
    }
}
