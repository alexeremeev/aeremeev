package ru.job4j.servlet.model;

/**
 * Модель роли пользователя.
 */
public class Role {
    /**
     * ID.
     */
    private int id;
    /**
     * Имя роли.
     */
    private String name;
    /**
     * Права администратора.
     */
    private boolean admin;

    /**
     * Конструктор.
     * @param name имя роли.
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Геттер id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Геттер имени.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер адиминских прав.
     * @return true, если есть.
     */
    public boolean getAdmin() {
        return admin;
    }

    /**
     * Сеттер ID.
     * @param id ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Сеттер имени.
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Сеттер админских прав.
     * @param admin true, если предоставлены.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
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
        if (getAdmin() != role.getAdmin()) {
            return false;
        }
        return getName() != null ? getName().equals(role.getName()) : role.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAdmin() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Admin: %b", this.id, this.name, this.admin);
    }
}
