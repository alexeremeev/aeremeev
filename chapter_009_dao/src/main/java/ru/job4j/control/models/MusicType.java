package ru.job4j.control.models;

/**
 * class MusicType - модель музыкальных предпочтений пользователя.
 */
public class MusicType {
    /**
     * ID.
     */
    private int id;
    /**
     * Жанр.
     */
    private String name;

    /**
     * Setters and Getters.
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

        MusicType musicType = (MusicType) o;

        if (getId() != musicType.getId()) {
            return false;
        }
        return getName() != null ? getName().equals(musicType.getName()) : musicType.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("MusicType{id=%s, name=%s}", this.id, this.name);
    }
}
