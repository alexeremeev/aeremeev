package ru.job4j.springmvc.models;

import java.util.Arrays;
import java.util.Objects;

/**
 * Image model.
 * @author aeremeev.
 * @version 1
 * @since 04.02.2018
 */
public class Image {

    private int id;

    private byte[] data;

    private Order order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return getId() == image.getId() && Arrays.equals(getData(), image.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getData());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Image{");
        sb.append("id=").append(id);
        sb.append(", data=").append(Arrays.toString(data));
        sb.append('}');
        return sb.toString();
    }
}
