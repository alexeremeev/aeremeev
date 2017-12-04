package ru.job4j.control.models;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * class User - модель пользователя.
 */
public class User {
    /**
     * ID пользователя.
     */
    private int id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Пароль пользователя.
     */
    private String password;
    /**
     * ID адреса пользователя.
     */
    private int addressId;
    /**
     * ID роли пользователя.
     */
    private int roleId;
    /**
     * Множество музыкальных предпочтений.
     */
    private Set<Integer> musicTypeId = new LinkedHashSet<>();

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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Integer> getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(Set<Integer> musicType) {
        this.musicTypeId.addAll(musicType);
    }

    public void setMusicTypeId(int musicTypeId) {
        this.musicTypeId.add(musicTypeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getId() != user.getId()) {
            return false;
        }
        if (getAddressId() != user.getAddressId()) {
            return false;
        }
        if (getRoleId() != user.getRoleId()) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null) {
            return false;
        }

        return getMusicTypeId() != null ? getMusicTypeId().equals(user.getMusicTypeId()) : user.getMusicTypeId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + getAddressId();
        result = 31 * result + getRoleId();
        result = 31 * result + (getMusicTypeId() != null ? getMusicTypeId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("User{id=%s, name=%s, password=%s, addressId=%s, roleId=%s, musicTypeId=%s}",
                this.id, this.name, this.password, this.addressId, this.roleId, this.musicTypeId);
    }
}
