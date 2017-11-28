package ru.job4j.servlet.model;

/**
 * Модель адреса пользователя.
 */
public class Address {
    /**
     * ID страны.
     */
    private int countryId;
    /**
     * Страна
     */
    private String country;
    /**
     * ID городоа,
     */
    private int cityId;
    /**
     * Город.
     */
    private String city;

    /**
     * Setters and getters.
     *
     * @return values.
     */
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

        if (getCountryId() != address.getCountryId()) {
            return false;
        }
        if (getCityId() != address.getCityId()) {
            return false;
        }
        if (getCountry() != null ? !getCountry().equals(address.getCountry()) : address.getCountry() != null) {
            return false;
        }
        return getCity() != null ? getCity().equals(address.getCity()) : address.getCity() == null;
    }

    @Override
    public int hashCode() {
        int result = getCountryId();
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + getCityId();
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }
}
