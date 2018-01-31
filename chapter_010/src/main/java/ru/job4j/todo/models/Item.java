package ru.job4j.todo.models;

import java.sql.Timestamp;
import java.util.Objects;
/**
 * Item model.
 * @author aeremeev.
 * @version 1
 * @since 28.01.2018
 */

public class Item {
    /**
     * ID.
     */
    private int id;
    /**
     * Description.
     */
    private String description;
    /**
     * Date of creation.
     */
    private Timestamp created;
    /**
     * Done flag.
     */
    private boolean done;

    /**
     * Setters and Getters.
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return getId() == item.getId()
               && getDone() == item.getDone()
               && Objects.equals(getDescription(), item.getDescription())
               && Objects.equals(getCreated(), item.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCreated(), getDone());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", created=").append(created);
        sb.append(", done=").append(done);
        sb.append('}');
        return sb.toString();
    }
}
